package repository;

import io.ebean.DB;
import io.ebean.Model;
import io.ebean.Transaction;
import models.Customer;
import org.joda.time.DateTime;
import play.*;
import javax.inject.*;
import javax.swing.text.html.Option;
import java.util.Optional;
import java.util.concurrent.CompletionStage;

import static java.util.concurrent.CompletableFuture.supplyAsync;


public class CustomerRepository {
    private final DatabaseExecutionContext executionContext;

    @Inject
    public CustomerRepository(DatabaseExecutionContext executionContext) {
        this.executionContext = executionContext;
    }
    public CompletionStage<Optional<Customer>> lookup(int id) {
        return supplyAsync(() -> DB.find(Customer.class).setId(id).findOneOrEmpty(), executionContext);
    }
    public CompletionStage<Integer> insert(Customer customer){
        return supplyAsync(()->{
            DB.insert(customer);
            return customer.getId();
        },executionContext);
    }
    public CompletionStage<Optional<Integer>> delete(int id){
        return supplyAsync(()->{
            Optional<Customer> customer = DB.find(Customer.class).setId(id).findOneOrEmpty();
            customer.ifPresent(Model::delete);
            return customer.map(c -> c.getId());
        },executionContext);
    }
    public CompletionStage<Optional<Integer>> update(int id,Customer newCustomer){
        return supplyAsync(()->{
            Transaction tsn = DB.beginTransaction();
            Optional<Integer> result = Optional.empty();
            try{
                Customer saveCustomer = DB.find(Customer.class).setId(id).findOne();
                if(saveCustomer != null){
                    saveCustomer.setCustomerName(newCustomer.getCustomerName());
                    saveCustomer.setBirth(newCustomer.getBirth());
                    saveCustomer.update();
                    tsn.commit();
                    result = Optional.of(id);
                }
            }
            catch (Exception e){
                tsn.rollback();
            }
            finally {
                tsn.end();
            }
            return result;
        },executionContext);
    }
}

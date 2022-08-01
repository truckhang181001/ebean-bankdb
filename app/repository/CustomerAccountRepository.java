package repository;

import io.ebean.DB;
import io.ebean.Model;
import io.ebean.Transaction;
import models.Customer;
import models.CustomerAccount;

import javax.inject.Inject;
import javax.swing.text.html.Option;
import java.util.Optional;
import java.util.concurrent.CompletionStage;

import static java.util.concurrent.CompletableFuture.supplyAsync;

public class CustomerAccountRepository {
    private final DatabaseExecutionContext executionContext;

    @Inject
    public CustomerAccountRepository(DatabaseExecutionContext executionContext) {
        this.executionContext = executionContext;
    }

    public CompletionStage<Optional<CustomerAccount>> lookup(int id){
        return supplyAsync(
                ()-> DB.find(CustomerAccount.class).setId(id).findOneOrEmpty()
                ,executionContext);
    }
    public CompletionStage<Integer> insert(CustomerAccount customerAccount){
        return supplyAsync(()->{
            DB.insert(customerAccount);
            return customerAccount.getId();
        },executionContext);
    }
    public CompletionStage<Optional<Integer>> update(int id, CustomerAccount customerAccount){
        return supplyAsync(()->{
            Transaction tsn = DB.beginTransaction();
            Optional<Integer> result = Optional.empty();
            try{
                CustomerAccount updateItem = DB.find(CustomerAccount.class).setId(id).findOne();
                if(updateItem != null){
                    updateItem.setBankAccount(customerAccount.getBankAccount());
                    updateItem.setCustomer(customerAccount.getCustomer());
                    updateItem.save();
                    tsn.commit();
                    result = Optional.of(updateItem.getId());
                }
            }
            catch (Exception e){
                tsn.rollback();
            }
            finally{
                tsn.end();
            }
            return result;
        },executionContext);
    }
    public CompletionStage<Optional<Integer>> delete(int id){
        return supplyAsync(()->{
            Optional<CustomerAccount> customerAccount = DB.find(CustomerAccount.class).setId(id).findOneOrEmpty();
            customerAccount.ifPresent(Model::delete);
            return customerAccount.map(r -> r.getId());
        },executionContext);
    }
}

package repository;

import io.ebean.DB;
import io.ebean.Model;
import io.ebean.Transaction;
import models.BankAccount;
import models.Customer;
import models.CustomerAccount;
import org.joda.time.DateTime;
import scala.Int;

import javax.inject.Inject;
import java.util.Optional;
import java.util.concurrent.CompletionStage;

import static java.util.concurrent.CompletableFuture.supplyAsync;

public class BankAccountRepository {
    private final DatabaseExecutionContext  databaseExecutionContext;

    @Inject
    public BankAccountRepository(DatabaseExecutionContext databaseExecutionContext){
        this.databaseExecutionContext = databaseExecutionContext;
    }

    public CompletionStage<Optional<Integer>> insert(int customerId,BankAccount bankAccount){
        return supplyAsync(()->{
            Transaction tsn = DB.beginTransaction();
            Optional<Integer> result = Optional.empty();
            try{
                DB.insert(bankAccount);
                CustomerAccount customerAccount = new CustomerAccount();

                Customer customer = new Customer();
                customer.setId(customerId);

                customerAccount.setCustomer(customer);
                customerAccount.setBankAccount(bankAccount);
                DB.insert(customerAccount);

                tsn.commit();
                result = Optional.of(bankAccount.getId());
            }
            catch (Exception e){
                tsn.rollback();
            }
            finally {
                tsn.end();
            }
            return result;
        },databaseExecutionContext);
    }
    public CompletionStage<Optional<BankAccount>> lookup(int id){
        return supplyAsync(()->{
            return DB.find(BankAccount.class).setId(id).findOneOrEmpty();
        },databaseExecutionContext);
    }
    public CompletionStage<Optional<Integer>> update(int id,BankAccount bankAccount){
        return supplyAsync(()->{
            Transaction tsn = DB.beginTransaction();
            Optional<Integer> result = Optional.empty();
            try{
                BankAccount updateItem = DB.find(BankAccount.class).setId(id).findOne();
                if(updateItem != null){
                    updateItem.setAccountNumber(bankAccount.getAccountNumber());
                    updateItem.update();
                    tsn.commit();
                    result = Optional.of(updateItem.getId());
                }
            }
            catch (Exception e){
                tsn.rollback();
            }
            finally {
                tsn.end();
            }
            return result;
        },databaseExecutionContext);
    }
    public CompletionStage<Optional<Integer>> delete(int id){
        return supplyAsync(()->{
            Optional<BankAccount> bankAccount = DB.find(BankAccount.class).setId(id).findOneOrEmpty();
            bankAccount.ifPresent(Model::delete);
            return bankAccount.map(c -> c.getId());
        },databaseExecutionContext);
    }
}

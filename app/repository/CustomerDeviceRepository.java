package repository;

import io.ebean.DB;
import models.BankAccount;
import models.Customer;
import models.CustomerDevice;

import javax.inject.Inject;
import java.util.Optional;
import java.util.concurrent.CompletionStage;

import static java.util.concurrent.CompletableFuture.supplyAsync;

public class CustomerDeviceRepository {
    private final DatabaseExecutionContext executionContext;
    @Inject
    public CustomerDeviceRepository(DatabaseExecutionContext executionContext) {
        this.executionContext = executionContext;
    }
    public CompletionStage<String> insert(CustomerDevice customerDevice){
        return supplyAsync(()->{
            DB.insert(customerDevice);
            return String.valueOf(customerDevice.getId());
        },executionContext);
    }
    public CompletionStage<Optional<CustomerDevice>> lookup(int id){
        return supplyAsync(()->{
            return DB.find(CustomerDevice.class).setId(id).findOneOrEmpty();
        },executionContext);
    }
}

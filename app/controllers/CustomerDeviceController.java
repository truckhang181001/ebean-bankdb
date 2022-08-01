package controllers;

import models.Customer;
import models.CustomerDevice;
import org.joda.time.DateTime;
import play.i18n.MessagesApi;
import play.libs.Json;
import play.libs.concurrent.HttpExecutionContext;
import play.mvc.BodyParser;
import play.mvc.Http;
import play.mvc.Result;
import repository.CustomerDeviceRepository;
import repository.CustomerRepository;
import repository.DatabaseExecutionContext;

import javax.inject.Inject;
import java.time.Instant;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.CompletionStage;

import static java.util.concurrent.CompletableFuture.supplyAsync;
import static play.mvc.Results.badRequest;
import static play.mvc.Results.ok;

public class CustomerDeviceController {
    private CustomerDeviceRepository customerDeviceRepository;
    private DatabaseExecutionContext databaseExecutionContext;
    private HttpExecutionContext httpExecutionContext;
    private MessagesApi messagesApi;

    @Inject
    public CustomerDeviceController(CustomerDeviceRepository customerDeviceRepository, DatabaseExecutionContext databaseExecutionContext, HttpExecutionContext httpExecutionContext, MessagesApi messagesApi) {
        this.customerDeviceRepository = customerDeviceRepository;
        this.databaseExecutionContext = databaseExecutionContext;
        this.httpExecutionContext = httpExecutionContext;
        this.messagesApi = messagesApi;
    }

    public CompletionStage<Result> view(int id){
        return customerDeviceRepository.lookup(id).thenApplyAsync(result->{
            if(result.isPresent())
                return ok(Json.toJson(result.get()));
            else return badRequest("NOT FOUND ITEM");
        }, httpExecutionContext.current());
    }

    @BodyParser.Of(BodyParser.Json.class)
    public CompletionStage<Result> create(int customerId,Http.Request request){
        Optional<CustomerDevice> customerDevice = request.body().parseJson(CustomerDevice.class);
        if(customerDevice.isPresent()){
            CustomerDevice createItem = new CustomerDevice();

            Customer customer = new Customer();
            customer.setId(customerId);

            createItem.setCustomer(customer);
            createItem.setToken(UUID.randomUUID().toString());
            createItem.setPlatform(CustomerDevice.ANDROID);
            createItem.setExpiredAt(Instant.now());
            return customerDeviceRepository.insert(createItem).thenApplyAsync(result ->{
                return ok("CREATED " + result);
            }, httpExecutionContext.current());
        }
        else return supplyAsync(()-> badRequest("FAILED"),httpExecutionContext.current());
    }
}

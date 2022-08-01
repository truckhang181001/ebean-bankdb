package controllers;

import models.BankAccount;
import models.Customer;
import models.CustomerAccount;
import play.i18n.MessagesApi;
import play.libs.Json;
import play.libs.concurrent.HttpExecutionContext;
import play.mvc.Result;
import repository.BankAccountRepository;
import repository.CustomerAccountRepository;
import repository.DatabaseExecutionContext;

import javax.inject.Inject;
import java.util.concurrent.CompletionStage;

import static play.mvc.Results.badRequest;
import static play.mvc.Results.ok;

public class CustomerAccountController {
    private CustomerAccountRepository customerAccountRepository;
    private DatabaseExecutionContext databaseExecutionContext;
    private HttpExecutionContext httpExecutionContext;
    private MessagesApi messagesApi;

    @Inject
    public CustomerAccountController(CustomerAccountRepository customerAccountRepository, DatabaseExecutionContext databaseExecutionContext, HttpExecutionContext httpExecutionContext, MessagesApi messagesApi) {
        this.customerAccountRepository = customerAccountRepository;
        this.databaseExecutionContext = databaseExecutionContext;
        this.httpExecutionContext = httpExecutionContext;
        this.messagesApi = messagesApi;
    }

    public CompletionStage<Result> view(int id){
        return customerAccountRepository.lookup(id).thenApplyAsync(result ->{
            if(result.isPresent()){
                return ok(Json.toJson(result.get().getBankAccount()));
            }
            else return badRequest("NOT FOUND ITEM");
        }, httpExecutionContext.current());
    }

    public CompletionStage<Result> create(int customerId, int bankId){
        CustomerAccount customerAccount = new CustomerAccount();

        Customer customer = new Customer();
        customer.setId(customerId);

        BankAccount bankAccount = new BankAccount();
        bankAccount.setId(bankId);

        customerAccount.setCustomer(customer);
        customerAccount.setBankAccount(bankAccount);

        return customerAccountRepository.insert(customerAccount).thenApplyAsync(result->{
            return ok(Json.toJson(customerAccount));
        }, httpExecutionContext.current());
    }
}

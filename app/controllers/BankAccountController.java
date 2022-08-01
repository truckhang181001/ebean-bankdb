package controllers;

import models.BankAccount;
import org.joda.time.DateTime;
import play.i18n.MessagesApi;
import play.libs.Json;
import play.libs.concurrent.HttpExecutionContext;
import play.mvc.BodyParser;
import play.mvc.Http;
import play.mvc.Result;
import repository.BankAccountRepository;
import repository.CustomerRepository;
import repository.DatabaseExecutionContext;

import javax.annotation.processing.Completion;
import javax.inject.Inject;
import java.time.Instant;
import java.util.Date;
import java.util.Optional;
import java.util.concurrent.CompletionStage;

import static java.util.concurrent.CompletableFuture.supplyAsync;
import static play.mvc.Results.badRequest;
import static play.mvc.Results.ok;

public class BankAccountController {
    private BankAccountRepository bankAccountRepository;
    private DatabaseExecutionContext databaseExecutionContext;
    private HttpExecutionContext httpExecutionContext;
    private MessagesApi messagesApi;

    @Inject
    public BankAccountController(BankAccountRepository bankAccountRepository, DatabaseExecutionContext databaseExecutionContext, HttpExecutionContext httpExecutionContext, MessagesApi messagesApi) {
        this.bankAccountRepository = bankAccountRepository;
        this.databaseExecutionContext = databaseExecutionContext;
        this.httpExecutionContext = httpExecutionContext;
        this.messagesApi = messagesApi;
    }

    public CompletionStage<Result> view(int id){
        return bankAccountRepository.lookup(id).thenApplyAsync(result->{
            if(result.isPresent()){
                return ok(Json.toJson(result.get()));
            }
            else return badRequest("NOT FOUND ITEM");
        }, httpExecutionContext.current());
    }

    @BodyParser.Of(BodyParser.Json.class)
    public CompletionStage<Result> create(int customerId,Http.Request request){
        Optional<BankAccount> bankAccount =  request.body().parseJson(BankAccount.class);
        if(bankAccount.isPresent()){
            BankAccount createItem = bankAccount.get();
            createItem.setExpiredAt(Instant.now());

            return bankAccountRepository.insert(customerId,createItem).thenApplyAsync(result ->{
                return ok("CREATED " + result);
            },httpExecutionContext.current());
        }
        else return supplyAsync(
                ()->badRequest("FAILED"), httpExecutionContext.current());
    }
}

package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import io.ebean.*;
import models.BankAccount;
import models.Customer;
import models.CustomerAccount;
import models.CustomerDevice;
import org.joda.time.DateTime;
import play.api.libs.json.EnvWrites;
import play.i18n.MessagesApi;
import play.libs.Json;
import play.libs.concurrent.HttpExecutionContext;
import play.mvc.BodyParser;
import play.mvc.Http;
import play.mvc.Result;
import repository.CustomerAccountRepository;
import repository.CustomerRepository;
import repository.DatabaseExecutionContext;

import javax.inject.Inject;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.CompletionStage;

import static java.util.concurrent.CompletableFuture.supplyAsync;
import static play.mvc.Results.badRequest;
import static play.mvc.Results.ok;

public class CustomerController {
    private CustomerRepository customerRepository;
    private DatabaseExecutionContext databaseExecutionContext;
    private HttpExecutionContext httpExecutionContext;
    private MessagesApi messagesApi;

    @Inject
    public CustomerController(CustomerRepository customerRepository, DatabaseExecutionContext databaseExecutionContext, HttpExecutionContext httpExecutionContext, MessagesApi messagesApi) {
        this.customerRepository = customerRepository;
        this.databaseExecutionContext = databaseExecutionContext;
        this.httpExecutionContext = httpExecutionContext;
        this.messagesApi = messagesApi;
    }
    public Result index(){
        return ok("Hello World");
    }

    @BodyParser.Of(BodyParser.Json.class)
    public CompletionStage<Result> create(Http.Request request){
        Optional<Customer> customer = request.body().parseJson(Customer.class);
        if(customer.isPresent()){
            Customer insertItem = customer.get();
            insertItem.setToken(UUID.randomUUID().toString());
            return customerRepository.insert(insertItem).thenApplyAsync(item ->{
                return ok(Json.toJson(insertItem));
            }, httpExecutionContext.current());
        }
        else return supplyAsync(()->badRequest("FAIL"), httpExecutionContext.current());
    }

    public CompletionStage<Result> delete(int id){
        return customerRepository.delete(id).thenApplyAsync(item->{
            return ok(item.isPresent()?"DELETED ITEM "+item.get():"NOT FOUND ITEM");
        }, httpExecutionContext.current());
    }

    @BodyParser.Of(BodyParser.Json.class)
    public CompletionStage<Result> update(int id, Http.Request request){
        Optional<Customer> customer = request.body().parseJson(Customer.class);
        if(customer.isPresent()){
            Customer updateItem = customer.get();
            return customerRepository.update(id,updateItem).thenApplyAsync(data->{
                return ok("UPDATED");
            }, httpExecutionContext.current());
        }
        else return supplyAsync(()->badRequest("UPDATE FAILED"), httpExecutionContext.current());
    }

    public CompletionStage<Result> view(int id){
        return customerRepository.lookup(id).thenApplyAsync(result->{
            if(result.isPresent()){
                return ok(Json.toJson(result.get()));
            }
            else return badRequest("NOT FOUND ITEM");
        }, httpExecutionContext.current());
    }

    public Result search(int id){

        /** Group by*/
        /** Total BankAccount of a customer*/
//        List<CustomerAccount> result = DB.find(CustomerAccount.class)
//                .select("customer, count")
//                .having()
//                .findList();

        /** Aggregate function */
        /** Total BankAccount of a customer*/
//        Long result = DB.find(CustomerAccount.class)
//                .select("count(*)")
//                .where()
//                .eq("customer_id",id)
//                .findSingleAttribute();

        /** Filtermany*/
        /** A customer's login device history for 1 week */
//        List<Customer> result = DB.find(Customer.class)
//                .fetch("customerDevices")
//                .where()
//                .eq("t0.id",id)
//                .filterMany("customerDevices")
//                .ge("created_at", "2022-07-29")
//                .findList();

        /** Use Entity Mapping - Get BankAccount, Customer form customer_id*/

//        /** Get Customer item where id = ?  */
//        Customer customers = DB.find(Customer.class)
//                .setId(id)
//                .findOne();
//        /** Get list Bank Account from attribute CustomerAccounts (List<CustomerAccount>)
//         * of Customer object */
//        List<BankAccount> result = new ArrayList<>();
//        for(CustomerAccount item : customers.getCustomerAccounts()){
//            result.add(item.getBankAccount());
//        }

//        List<BankAccount> result = DB.find(BankAccount.class)
//                .fetch("customerAccounts")
//                .where()
//                .eq("t1.customer_id",5)
//                .findList();

        /**-------------------------------------------------------------------------*/


        /** ----------------------Insert----------------------*/
//        String sql = "INSERT INTO CUSTOMER (customer_name,birth,token,created_at,updated_at,deleted)"
//        + "VALUES(?,?,?,?,?,?)";
//
//        DB.sqlUpdate(sql)
//                .setParameter(1,"Nguyen Truc Khang")
//                .setParameter(2,"2001-10-18")
//                .setParameter(3,UUID.randomUUID())
//                .setParameter(4,Instant.now())
//                .setParameter(5,Instant.now())
//                .setParameter(6,0)
//                .execute();
//
//        Customer customer = new Customer();
//        customer.setCustomerName("Nguyen Truc Khang");
//        customer.setBirth(LocalDate.of(2001,10,18));
//        customer.setToken(UUID.randomUUID().toString());
//
//        DB.insert(customer);
        /**-------------------------------------------------------------------------*/


        /** ----------------------Update------------------------*/
//        String sql = "UPDATE CUSTOMER SET customer_name = :name where id = :id";
//
//        DB.sqlUpdate(sql)
//                .setParameter("name","Nguyen Van B")
//                .setParameter("id",21)
//                .execute();

//        Customer customer = DB.find(Customer.class,22);
//        customer.setCustomerName("Nguyen Van C");
//        customer.save();

        /**-------------------------------------------------------------------------*/


        /** ----------------------Delete----------------------*/
//        String sql = "DELETE FROM CUSTOMER WHERE id = :id";
//
//        DB.sqlUpdate(sql)
//                .setParameter("id",22)
//                .execute();

//        Optional<Customer> customer = DB.find(Customer.class)
//                .setId(20)
//                .findOneOrEmpty();
//        customer.ifPresent(Model::delete);

        /**-------------------------------------------------------------------------*/

        /** ----------------------WHERE----------------------*/

//        Customer result = DB.find(Customer.class)
//                .where()
//                .or()
//                    .eq("id",id)
//                    .eq("customerName","Nguyen Truc Khang")
//                .endOr()
//                .findOne();

//        Customer result = DB.find(Customer.class)
//                .where()
//                .eq("id",id)
//                .gt("t0.created_at",Instant.now())
//                .raw("customer_name = ?","Nguyen Truc Khang")
//                .findOne();

        /**-------------------------------------------------------------------------*/


        /** ----------------------SQL QUERY----------------------*/

//        String sql = "select id, customer_name from customer where id = :id";
//
//        SqlRow result = DB.sqlQuery(sql)
//                .setParameter("id", 15)
//                .findOne();
//
//        String name = result.getString("name");
//        Timestamp active = result.getTimestamp("when_active");

        /**-------------------------------------------------------------------------*/

        /** ----------------------FIND NATIVE----------------------*/

//        String sql = "select * from customer where id = :id";
//
//        Customer result = DB.findNative(Customer.class,sql)
//                .setParameter("id", 15)
//                .findOne();

        /**-------------------------------------------------------------------------*/

        int page = 0;
        int pageSize = 10;
        String sortBy="id";
        String order="ASC";
        String filter="";

        PagedList<CustomerDevice> pagedList = DB.find(CustomerDevice.class)
                .orderBy(sortBy + " " + order)
                .setFirstRow(page*pageSize)
                .setMaxRows(pageSize)
                .findPagedList();

        List<CustomerDevice> result = pagedList.getList();

        return ok(Json.toJson(result));

    }

}

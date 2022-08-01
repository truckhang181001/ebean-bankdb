import io.ebean.DB;
import models.Customer;

import java.time.LocalDate;

public class testEbean {
    public static void main(String[] args){
        System.out.println("Hello");

        Customer customer = new Customer();
        customer.setCustomerName("Nguyen Truc Khang");
        customer.setBirth(LocalDate.of(2001,10,18));

        DB.insert(customer);
    }
}

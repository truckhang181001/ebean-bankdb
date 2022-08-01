package models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import io.ebean.annotation.Aggregation;
import org.joda.time.DateTime;
import play.data.validation.Constraints;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class CustomerAccount extends BaseModel{

    @Constraints.Required

    @JsonBackReference(value = "accountToCustomer")
    @ManyToOne(optional = false)
    private Customer customer;
    @JsonBackReference(value = "banktoCustomer")
    @ManyToOne(optional = false)
    //If foreign key not match with format name_id
    // then use @JoinColumn("custom_name")
    private BankAccount bankAccount;
    @Aggregation("count(*)")
    private Long count;

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public BankAccount getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(BankAccount bankAccount) {
        this.bankAccount = bankAccount;
    }

}

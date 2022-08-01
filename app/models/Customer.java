package models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import io.ebean.annotation.WhenCreated;
import io.ebean.annotation.WhenModified;
import org.joda.time.DateTime;
import play.data.format.Formats;
import play.data.validation.Constraints;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.validation.Constraint;
import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Customer extends BaseModel{
    @Constraints.Required
    private String customerName;
    @Formats.DateTime(pattern="yyyy-MM-dd")
    private LocalDate birth;
    private String token;

    @OneToMany(mappedBy = "customer")
    private List<CustomerDevice> customerDevices = new ArrayList<CustomerDevice>();

    @OneToMany(mappedBy = "customer")
    private List<CustomerAccount> customerAccounts = new ArrayList<CustomerAccount>();

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public LocalDate getBirth() {
        return birth;
    }

    public void setBirth(LocalDate birth) {
        this.birth = birth;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public List<CustomerDevice> getCustomerDevices() {
        return customerDevices;
    }

    public void setCustomerDevices(List<CustomerDevice> customerDevices) {
        this.customerDevices = customerDevices;
    }

    public List<CustomerAccount> getCustomerAccounts() {
        return customerAccounts;
    }

    public void setCustomerAccounts(List<CustomerAccount> customerAccounts) {
        this.customerAccounts = customerAccounts;
    }
}

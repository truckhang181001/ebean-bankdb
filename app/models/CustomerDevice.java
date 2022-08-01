package models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import org.joda.time.DateTime;
import play.data.validation.Constraints;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.time.Instant;

@Entity
public class CustomerDevice extends BaseModel {
    public static final byte IOS = 1;
    public static final byte ANDROID = 2;
    public static final byte WEB = 3;
    @Constraints.Required

    @ManyToOne(optional = true)
    @JsonBackReference(value="deviceToCustomer")
    private Customer customer;
    private String token;
    private byte platform;
    private Instant expiredAt;

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public byte getPlatform() {
        return platform;
    }

    public void setPlatform(byte platform) {
        this.platform = platform;
    }

    public Instant getExpiredAt() {
        return expiredAt;
    }

    public void setExpiredAt(Instant expiredAt) {
        this.expiredAt = expiredAt;
    }

}

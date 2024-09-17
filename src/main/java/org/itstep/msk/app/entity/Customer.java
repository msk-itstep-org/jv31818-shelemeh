package org.itstep.msk.app.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.itstep.msk.app.validation.Password;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Objects;
import java.util.Set;

@Data
@Entity
@Table(name = "customer")
@NamedEntityGraph(name = "customer-entity-graph",
attributeNodes = {
        @NamedAttributeNode(value = "id"),
        @NamedAttributeNode(value = "name"),
        @NamedAttributeNode(value = "email"),
        @NamedAttributeNode(value = "password")
})
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Integer id;

    @Column(name = "name", nullable = false)
    @NotBlank
    @Size(min = 2, max = 20,
            message = "A form with name should be between 2 to 10")
    @ApiModelProperty(value = "This is customer`s name", required = true)
    private String name;

    @Column(name = "email",
            nullable = false, unique = true)
    @Email(regexp = "^(.+)@(.+)$", message = "Incorrect email")
    @Size(max = 20)
    @ApiModelProperty(value = "This is customer`s email should be unique", required = true)
    private String email;

    public String getVerifyCode() {
        return verifyCode;
    }

    public void setVerifyCode(String verifyCode) {
        this.verifyCode = verifyCode;
    }

    @Column(name = "code",unique = true)
    @ApiModelProperty(value = "This is verification code",required = true)
    private String verifyCode;

    public Customer() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Customer)) return false;
        Customer customer = (Customer) o;
        return id.equals(customer.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Column(name = "phone")
    @Pattern(regexp = "^((\\+7|7|8)+([0-9]{10})$)", message = "Invalid phone number")
    @Size(max = 20, message = "the digits should be not more than 10")
    @ApiModelProperty(value = "This is number of phone customer ", required = true)
    private String phoneNumber;

    @Column(name = "password", nullable = false)
    @NotNull
    @JsonIgnore
    @Password
    private String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    @Column(name = "reset_password_token", length = 30)
    @JsonIgnore
    private String resetPassword;

    @ManyToMany
    @JoinTable(name = "custom_roles", joinColumns = @JoinColumn(name = "customer_id"), inverseJoinColumns =
    @JoinColumn(name = "roles_id"))
    @JsonIgnore
    private Set<Role> roles;

    public Set<Role> getRole() {
        return this.roles;
    }

    public void setRole(final Set<Role> role) {
        this.roles = role;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Customer(String password, String name,String email) {
        this.name = name;
         this.email = email;
        this.password = password;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}

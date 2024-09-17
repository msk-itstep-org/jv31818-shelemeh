package org.itstep.msk.app.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@Table(name = "product")
@NamedEntityGraph(name = "product-entity-graph", attributeNodes = {
        @NamedAttributeNode("id"),
        @NamedAttributeNode("name"),
        @NamedAttributeNode("totalPrice")
})
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true)
    private Integer id;

    @Column(name = "name_product")
    @ApiModelProperty(value = "This is name of product ", required = true)
    private String name;

    @Column(name = "final_price")
    @ApiModelProperty(value = "This is final cost of product", required = true)
    private double totalPrice;

    @OneToMany(targetEntity = Customer.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "id", insertable = false,
            updatable = true, referencedColumnName = "id", nullable = true)
    @JsonIgnore
    private Set<Customer> customers;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "id", referencedColumnName = "id", insertable = false, updatable = false)
    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    private Category category;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Product product = (Product) o;

        if (!id.equals(product.id)) return false;
        return name.equals(product.name);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + name.hashCode();
        return result;
    }

    public Product() {
    }

    public Set<Customer> getCustomer() {
        return customers;
    }

    public void setCustomer(Set<Customer> customer) {
        this.customers = customer;
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

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", totalPrice=" + totalPrice +
                '}';
    }
}
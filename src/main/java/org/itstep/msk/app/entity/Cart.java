package org.itstep.msk.app.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Override
    public String toString() {
        return "Cart{" +
                "id=" + id +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Cart cart = (Cart) o;

        if (!id.equals(cart.id)) return false;
        return Objects.equals(productList, cart.productList);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + (productList != null ? productList.hashCode() : 0);
        return result;
    }

    @OneToMany(targetEntity = Product.class, cascade= CascadeType.ALL,fetch = FetchType.LAZY)
    @Column(nullable = true,table = "prod_list")
    @ApiModelProperty(value = "It is a cart of product ",required = true)
    private List<Product> productList;
}

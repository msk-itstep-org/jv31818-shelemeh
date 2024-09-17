package org.itstep.msk.app.entity.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.itstep.msk.app.entity.Customer;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {

    @NotNull
    private Integer id;

    @NotBlank
    private String nameProduct;
    @NotNull
    private Double totalPrice;

    private Set<Customer> customers;
}

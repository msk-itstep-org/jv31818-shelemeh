package org.itstep.msk.app.mapper;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.itstep.msk.app.entity.Product;
import org.itstep.msk.app.entity.model.ProductDTO;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@Data
public class ProductMapper {

    public static final ModelMapper MODEL_MAPPER = new ModelMapper();

    public static Product fromDTO(ProductDTO productDTO) {
        Product product = MODEL_MAPPER.map(productDTO,Product.class);
        product.setId(productDTO.getId());
        product.setName(productDTO.getNameProduct());
        product.setTotalPrice(productDTO.getTotalPrice());
        product.setCustomer(productDTO.getCustomers());
        return product;
    }

    public static ProductDTO fromEntity(Product product) {
        ProductDTO productDTO = MODEL_MAPPER.map(product,ProductDTO.class);
        productDTO.setId(product.getId());
        productDTO.setNameProduct(product.getName());
        productDTO.setTotalPrice(product.getTotalPrice());
        productDTO.setCustomers(product.getCustomer());
        return productDTO;
    }
}

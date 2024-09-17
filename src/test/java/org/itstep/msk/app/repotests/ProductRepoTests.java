package org.itstep.msk.app.repotests;

import org.itstep.msk.app.entity.Product;
import org.itstep.msk.app.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest(properties = {"spring.jpa.hibernate.ddl-auto=create-drop", "spring.flyway.enable=false",
        "spring.jpa.database-platform=org.hibernate.dialect.MySQL5InnoDBDialect"})
@Transactional(propagation = Propagation.NOT_SUPPORTED)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ProductRepoTests extends TestContainerBase {

    @Autowired
    private ProductRepository productRepository;

    private Product saveProduct;


    @BeforeEach
    void setUpDb() {
        productRepository.deleteAll();

        Product entity = new Product();
        entity.setName("Plane");
        entity.setTotalPrice(12.33);
        saveProduct = productRepository.save(entity);

        assertEquals(entity, saveProduct);

    }
    @Test
    @DisplayName("get all products test")
    void test_find_all_products() {
        var  productList = productRepository.findAll();
        assertThat(productList.size()).isGreaterThan(0);

    }
    @Test
    @DisplayName("should get a product by item id")
    void test_find_product_by_Id(){
        var actualProduct = productRepository.findById(saveProduct.getId()).get();
        assertThat(actualProduct).isNotNull()
                .isEqualTo(saveProduct);

    }
}

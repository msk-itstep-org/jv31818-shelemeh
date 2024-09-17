package org.itstep.msk.app.repository;

import org.itstep.msk.app.entity.Product;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    /**
     * Find product by item id if product is present
     * @param id
     * @return
     */
    @Query("SELECT p from Product p where p.id =:id")
    @EntityGraph("product-entity-graph")
    Optional<Product> findById(@Param("id") @NotNull Integer id);

    /**
     * Find product by item name id name of product is present
     * @param name
     * @return product
     */
    @Query("SELECT p from Product p where p.name =: name and p.name is not null ")
    @EntityGraph("product-entity-graph")
    Optional<Product> findByName(@Param("name") String name);

    /**
     * Find all products from db
     * @return
     */
    @EntityGraph(value = "product-entity-graph")
    List<Product> findAll();
}


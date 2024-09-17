package org.itstep.msk.app.controller;


import io.swagger.annotations.ApiOperation;
import org.itstep.msk.app.entity.Product;
import org.itstep.msk.app.service.ServiceProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/products", produces = "application/json")
public class ProductController {

    private final ServiceProduct serviceProduct;

    @Autowired
    public ProductController(ServiceProduct serviceProduct) {
        this.serviceProduct = serviceProduct;
    }

    @GetMapping("/all")
    @ApiOperation(value = "Retrieve all products")
    public List<Product> giveAllProduct() {
        return serviceProduct.findAllProduct();
    }

    // Fetch product by item id
    @GetMapping("/{id}")
    public Optional<Product> oneProduct(@PathVariable Integer id) {
        return serviceProduct.findById(id);

    }

    // Update product finds by item  <p>id</p>
    @PutMapping(value = "/{id}", consumes = {MediaType.APPLICATION_JSON_VALUE}
            , produces = {MediaType.APPLICATION_JSON_VALUE})
    public Product updateProduct(@PathVariable Integer id, @Valid @RequestBody Product product) {
        return serviceProduct.updateProduct(id);
    }

    // Delete product from by item id
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> deleteProdById(@PathVariable Integer id, @RequestBody Product product) {
      serviceProduct.removeProdById(id);
      return ResponseEntity.noContent().build();
    }

}

package org.itstep.msk.app.service;

import javassist.NotFoundException;
import lombok.SneakyThrows;
import org.itstep.msk.app.entity.Product;
import org.itstep.msk.app.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ServiceProduct {

    private final ProductRepository productRepository;

    @Autowired
    public ServiceProduct(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @SneakyThrows
    @Cacheable(value = "storeCache", key = "#prodId")
    public Optional<Product> findById(Integer prodId) {
        return Optional.ofNullable(productRepository.findById(prodId).orElseThrow(
                () -> new org.webjars.NotFoundException("not found such product")));
    }

    @Cacheable(value = "storeCache", unless = "#result == null")
    public List<Product> findAllProduct() {
        return productRepository.findAll()
                .stream()
                .filter(product -> product.getName() != null && product.getTotalPrice() > 0)
                .collect(Collectors.toList());
    }

    @SneakyThrows
    @CachePut(value = "products", key = "#productId")
    public Product updateProduct(Integer productId) {
        Optional<Product> productOptional = productRepository.findById(productId);
        if (!productOptional.isPresent()) {
            throw new NotFoundException("not such product " + productOptional.get());
        }
        Product product = new Product();
        product.setTotalPrice(product.getTotalPrice());
        product.setName(product.getName());
        return productRepository.save(product);
    }

    @SneakyThrows
    @CacheEvict(value = "storeCache", key = "#productId")
    public void removeProdById(Integer productId) {
        if (productId== null) {
            throw new NoSuchFieldException("not such field" + productId + "exist in database ");
        }
        productRepository.deleteById(productId);
    }
}
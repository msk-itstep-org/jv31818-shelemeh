package org.itstep.msk.app.service;


import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.itstep.msk.app.entity.Customer;
import org.itstep.msk.app.entity.Product;
import org.itstep.msk.app.repository.CustomerRepository;
import org.itstep.msk.app.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class AdminServiceImp {


    private final ProductRepository productRepository;
    private final CustomerRepository customRepository;

    @Transactional(readOnly = true)
    public Product updateProduct(String name) {
        Optional<Product> prod = Optional.of(new Product());
        if (!prod.isPresent()) {
            throw new IllegalArgumentException("not such " + name);
        }
        log.info("create new prod with credetils name");
        Product product = new Product();
        product.setName(prod.get().getName());
        return productRepository.save(product);


    }

    @SneakyThrows
    public void deleteCustomer(Integer id) {
        Optional<Customer> optionalCustomer = Optional.ofNullable(customRepository.findById(id)
                .orElseThrow(() -> new org.webjars.NotFoundException("customer not found ")));
        optionalCustomer.ifPresent(customRepository::delete);
    }


    public List<Customer> findAllCustomer() {
        List<Customer> customers = customRepository.findAll()
                .stream().filter(customer -> customer.getId() != null)
                .collect(Collectors.toList());
        if (customers.isEmpty()) {
            return Collections.emptyList();
        }
        return customers;
    }
}

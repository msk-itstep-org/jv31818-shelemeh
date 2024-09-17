package org.itstep.msk.app.controller;

import io.swagger.annotations.ApiOperation;
import org.itstep.msk.app.entity.Customer;
import org.itstep.msk.app.service.ServiceCustomer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/*
 * @author shele
 */

@RestController
@RequestMapping(value = "/customers")
public class CustomerController {

    private final ServiceCustomer serviceCustomer;

    @Autowired
    public CustomerController(ServiceCustomer serviceCustomer) {
        this.serviceCustomer = serviceCustomer;
    }


    //Get from Customer credentials as name ,password
    @GetMapping("/{id}")
    @ApiOperation(value = "This method get customer by item id")
    public Optional<Customer> retrieveCustomer(@PathVariable Integer id) {
        return serviceCustomer.findById(id);
    }

    //Get customer by name , email
    @GetMapping("/{name}")
    public Customer findByName(@PathVariable String name) {
        return serviceCustomer.getCustomerByName(name);
    }


    //Update customer should return a new Customer with  replacing name , email
    @PutMapping("/{id}")
    @ApiOperation(value = "This method for changing customer`s email and name")
    public Customer updateCustomer(@RequestBody Customer customer
            , @PathVariable Integer id) {
        return serviceCustomer.changeCustomerEmailAndName(id);

    }

    //Should  delete customer from db  by customer's id
    @DeleteMapping("/{id}")
    @ApiOperation(value = "This method delete customer by item id")
    public void removeCustomer(@PathVariable Integer id) {
        serviceCustomer.deleteCustomerById(id);
    }


}















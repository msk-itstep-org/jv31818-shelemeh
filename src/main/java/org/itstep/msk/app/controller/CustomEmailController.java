package org.itstep.msk.app.controller;

import org.itstep.msk.app.service.ServiceCustomer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomEmailController {

    @Autowired
    private ServiceCustomer serviceCustomer;

    @PostMapping("/customer/check_email")
    public String checkDuplicateEmail(@Param("email") String email) {
        return serviceCustomer.isCustomerEmailUnique(email) ? "ok" : "duplicated";
    }

}


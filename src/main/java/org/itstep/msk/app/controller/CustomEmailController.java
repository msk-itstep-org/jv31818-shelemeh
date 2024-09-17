package org.itstep.msk.app.controller;

import org.itstep.msk.app.service.ServiceCustomer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/email")
public class CustomEmailController {

    @Autowired
    private ServiceCustomer serviceCustomer;

    @PostMapping("/check_email")
    public String checkDuplicateEmail(@Param("email") String email) {
        return serviceCustomer.isCustomerEmailUnique(email) ? "ok" : "duplicated";
    }

}


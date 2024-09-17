package org.itstep.msk.app.controller;


import org.itstep.msk.app.entity.Customer;
import org.itstep.msk.app.entity.Product;
import org.itstep.msk.app.service.AdminServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RestController
@RequestMapping(value = "/admin", produces = "application/json")
public class AdminController {

    private final AdminServiceImp adminServiceImp;

    @Autowired
    public AdminController(AdminServiceImp adminServiceImp) {
        this.adminServiceImp = adminServiceImp;
    }

    @GetMapping("/panel")
    @PreAuthorize("hasRole('ADMIN')")
    public String getAdminBar(@RequestBody Customer customer, @RequestParam String name, @RequestParam String password,
                                   Model model) {
        model.addAttribute("panel", customer);
        return "panel";


    }

    @PutMapping("/admin/update")
    public Product changeProduct(@RequestBody Product prod, @RequestParam String name) {
        if (prod.getId()==0 && !name.isEmpty()) {
            return adminServiceImp.updateProduct(name);
        }
        throw new DataIntegrityViolationException("not such fields exists");
    }


    @DeleteMapping("/admin/delete/{id}")
    public void deleteCustomer(@PathVariable Integer id) {
        adminServiceImp.deleteCustomer(id);

    }


}

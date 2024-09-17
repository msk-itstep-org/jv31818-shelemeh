package org.itstep.msk.app.controller;

import org.itstep.msk.app.entity.Customer;
import org.itstep.msk.app.repository.CustomerRepository;
import org.itstep.msk.app.service.PageServiceCustomer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

/**
 * @author shele
 * Basic user controller which involves incomig
 * request and then show a user's page of registration
 */
@Controller
@RequestMapping("/register")
public class LoginController {

    private final CustomerRepository repo;


    @Autowired
    private PageServiceCustomer pageServiceCustomer;

    @Autowired
    public LoginController(CustomerRepository repo) {
        this.repo = repo;

    }

    @GetMapping("/login")
    public String Login(Model model) {
        model.addAttribute("customer", new Customer());
        return "register";
    }
    @GetMapping("/login-locked")
    public String loginLocked(Model model){
        model.addAttribute("loginLocked",true);
        return "register";
    }

    /**
     * Save in database customer with parameters  name , password.
     * if credentials are valid, return view  with successfully registration
     */
    @PostMapping("/login")
    public String successfully(@Valid Customer customer, BindingResult result, RedirectAttributes attributes) {
        if (result.hasErrors()) {
            return "register";
        }
        repo.save(customer);
        attributes.addFlashAttribute("message", "The registration has been successfully passed ");
        return "registersuccess";
    }

    @GetMapping("/page/{pageNum}")
    public String listPage(@PathVariable(name = "pageNum") int pageNum, Model model, @Param("sortField") String sortField
            , @Param("sortDir") String sortDir) {
        Page<Customer> page = pageServiceCustomer.listByPage(pageNum, sortField, sortDir);
        List<Customer> list = page.getContent();
        long firstcount = (pageNum - 1) * PageServiceCustomer.USER_PER_PAGE + 1;
        long secodcount = firstcount * PageServiceCustomer.USER_PER_PAGE - 1;
        if (secodcount > page.getTotalElements()) {
            secodcount = page.getTotalElements();

        }
        model.addAttribute("currentpage", pageNum);
        model.addAttribute("totalPage", page.getTotalPages());
        model.addAttribute("firstcount", firstcount);
        model.addAttribute("secondcount", secodcount);
        model.addAttribute("totalItem", page.getTotalElements());
        model.addAttribute("customer", list);
        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        return "pagincustomer";
    }

}





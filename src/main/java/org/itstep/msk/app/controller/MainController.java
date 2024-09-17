package org.itstep.msk.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequestMapping(value = "/main")
public class MainController {

    @GetMapping
    public String getIndex(){
        return "index";
    }

    @GetMapping("/denypage")
    public String deny (){
        return "denypage";
    }


}


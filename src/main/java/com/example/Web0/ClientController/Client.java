package com.example.Web0.ClientController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class Client {

    @GetMapping(value = "login")
    public ModelAndView loginPage() {
        return new ModelAndView("login");
    }

    @GetMapping(value = {"/", ""})
    public ModelAndView homePage() {
        return new ModelAndView("home");
    }
}

package jp.co.axa.apidemo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageViewController {
	@RequestMapping("/login")
    public String login() {

        return "login";
    }
	
	@RequestMapping("/employee")
    public String employee() {

        return "employee";
    }
}

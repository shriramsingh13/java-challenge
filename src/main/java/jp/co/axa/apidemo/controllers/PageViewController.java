package jp.co.axa.apidemo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * View Controller to return page view as per the matched route name
 *
 * @author shriram.singh
 */
@Controller
public class PageViewController {

    /**
     * Route to login page
     *
     * @return {@link String} login page name
     */
    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    /**
     * Route to employee page
     *
     * @return {@link String} employee page name
     */
    @RequestMapping("/employee")
    public String employee() {
        return "employee";
    }

    /**
     * Route to view all page
     *
     * @return {@link String} viewall page name
     */
    @RequestMapping("/viewall")
    public String viewall() {
        return "viewall";
    }
}

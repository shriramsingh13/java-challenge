package jp.co.axa.apidemo.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jp.co.axa.apidemo.entities.UserLogin;
import jp.co.axa.apidemo.services.UserLoginService;

/**
 * Rest Controller to listen request related to login and signup
 *
 * @author shriram.singh
 */
@RestController
@RequestMapping("/api/v1")
public class UserLoginController {
    @Autowired
    private UserLoginService userLoginService;

    Logger logger = LoggerFactory.getLogger(UserLoginController.class);
    /**
     * Check if username and password are valid for login
     *
     * @param userLogin : contains username and password send in the request object
     * @return {@link String} success or failure message
     */
    @PostMapping("/login")
    public boolean checkLogin(@RequestBody UserLogin userLogin) {
        try {
            UserLogin us = userLoginService.findByUserNameAndPassword(userLogin.getUsername(),
                    userLogin.getPassword());
            if (us != null) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            logger.warn(e.getMessage());
            return false;
        }
    }

    /**
     * Check if username is new user then update username and password in USERLOGIN table
     *
     * @param userLogin : contains username and password send in the request object
     * @return {@link String} success or failure message
     */
    @PostMapping("/signup")
    public boolean signup(@RequestBody UserLogin userLogin) {
        return userLoginService.saveUserNameAndPassword(userLogin);
    }
}

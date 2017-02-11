package com.crossover.techtrial.java.se.controller;

import com.crossover.techtrial.java.se.dto.LoginRequest;
import com.crossover.techtrial.java.se.model.user.User;
import com.crossover.techtrial.java.se.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by kasun on 1/28/17.
 */

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    MessageSource messageSource;

  /*
 * This method will provide the medium to add a new user.
 */
    @RequestMapping(value = {"/new"}, method = RequestMethod.GET)
    public String newUser(ModelMap model) {
        User user = new User();
        model.addAttribute("user", user);
        model.addAttribute("edit", false);
        return "registration";
    }

    @RequestMapping(value = {"/list"}, method = RequestMethod.GET)
    public String allUsers(ModelMap model) {
        List<User> users = userService.retrieveAllUsers();
        model.addAttribute("users", users);
        model.addAttribute("edit", false);
        return "allusers";
    }

    @RequestMapping(value = { "/login" }, method = RequestMethod.POST)
    @ResponseBody
    public String login(@RequestBody LoginRequest loginRequest) {

        validateLoginRequest(loginRequest);
        return userService.login(loginRequest);
    }

    private void validateLoginRequest(LoginRequest loginRequest) {

    }


    @RequestMapping(value = { "/save" }, method = RequestMethod.POST)
    @ResponseBody
    public String saveUser(@RequestBody User user) {
        return userService.saveUser(user);
    }

    /*
 * This method will be called on form submission, handling POST request for
 * saving user in database. It also validates the user input
 */
    @RequestMapping(value = { "/new" }, method = RequestMethod.POST)
    public String saveEmployee(@Valid User user, BindingResult result,
                               ModelMap model) {

        if (result.hasErrors()) {
            return "registration";
        }

		/*
		 *
		 * Below mentioned peace of code [if block] is to demonstrate that you can fill custom errors outside the validation
		 * framework as well while still using internationalized messages.
		 *
		 */
        if(!userService.isUserNameUnique(user.getUserId(), user.getEmail())){

//            FieldError ssnError =new FieldError("employee","ssn",messageSource.getMessage("non.unique.ssn", new String[]{user.getEmail()}, Locale.getDefault()));
//            result.addError(ssnError);
//            return "registration";
        }

        userService.saveUser(user);

        model.addAttribute("success", "User " + user.getName() + " registered successfully");
        return "success";
    }
}

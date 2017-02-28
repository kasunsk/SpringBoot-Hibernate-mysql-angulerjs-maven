package com.kasun.ailine.service.controller;

import com.kasun.ailine.service.dto.user.LoginRequest;
import com.kasun.ailine.service.dto.user.UserRole;
import com.kasun.ailine.service.common.dto.UserSearchCriteria;
import com.kasun.ailine.service.service.user.UserService;
import com.kasun.ailine.service.model.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static com.kasun.ailine.service.util.ValidationUtil.validate;

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
    @ResponseBody
    public List<User> allUsers() {
        List<User> users = userService.retrieveAllUsers();
        return users;
    }

    @RequestMapping(value = {"/remove/{userId}"}, method = RequestMethod.GET)
    @ResponseBody
    public Boolean removeUser(@PathVariable("userId") String userId) {
        userService.removeUser(userId);
        return Boolean.TRUE;
    }

    @RequestMapping(value = {"/login"}, method = RequestMethod.POST)
    @ResponseBody
    public User login(@RequestBody LoginRequest loginRequest) {

        validateLoginRequest(loginRequest);
        return userService.login(loginRequest);
    }

    @RequestMapping(value = {"/{applicantId}/search"}, method = RequestMethod.POST)
    @ResponseBody
    public List<User> searchUser(@PathVariable("applicantId") String applicantId, @RequestBody UserSearchCriteria searchCriteria) {

        User user = userService.loadUserById(applicantId);

        if (user.getRole() != UserRole.ADMIN) {
            throw new RuntimeException("User not have enough privileges");
        }
        return userService.searchUser(searchCriteria);
    }

    private void validateLoginRequest(LoginRequest loginRequest) {

        validate(loginRequest.getEmail(), "Email is empty");
        validate(loginRequest.getPassword(), "Email is empty");
    }


    @RequestMapping(value = {"/save"}, method = RequestMethod.POST)
    @ResponseBody
    public Boolean saveUser(@RequestBody User user) {
        userService.saveUser(user);
        return Boolean.TRUE;
    }

    @RequestMapping(value = {"/new"}, method = RequestMethod.POST)
    public String saveUser(@Valid User user, BindingResult result,
                           ModelMap model) {

        if (result.hasErrors()) {
            return "registration";
        }

        userService.saveUser(user);

        model.addAttribute("success", "User " + user.getName() + " registered successfully");
        return "success";
    }
}

package com.crossover.techtrial.java.se.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by kasun on 2/5/17.
 */
@Controller
public class WelcomeController {

    /*
* This method will provide the medium to login.
*/
    @RequestMapping(value = {"/"}, method = RequestMethod.GET)
    public String welcome(ModelMap model) {
        return "index";
    }
}

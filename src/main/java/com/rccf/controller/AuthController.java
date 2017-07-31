package com.rccf.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/auth")
public class AuthController {


    @ResponseBody
    @RequestMapping(value = "/login")
    public String login(){


        return null;
    }

    @RequestMapping(value = "check")
    @ResponseBody
    public String checke(){


        return null;
    }

}

package com.rccf.controller.front;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/invite")
public class InviteController {


    @RequestMapping(value = "/index")
    public ModelAndView index() {
        return new ModelAndView("/front/invite/index");
    }

    @RequestMapping(value = "/myinvite")
    public ModelAndView myinvite() {
        return new ModelAndView("/front/invite/myinvite");
    }

}

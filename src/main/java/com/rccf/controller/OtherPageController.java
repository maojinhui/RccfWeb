package com.rccf.controller;

import com.rccf.constants.UrlConstants;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/rccfkg", produces = UrlConstants.PRODUCES)
public class OtherPageController {


    @RequestMapping(value = "/home")
    public ModelAndView first() {
        return new ModelAndView("/other_page/loushu");
    }
}

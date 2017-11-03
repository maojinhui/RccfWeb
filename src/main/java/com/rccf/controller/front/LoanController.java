package com.rccf.controller.front;

import com.rccf.constants.UrlConstants;
import com.rccf.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(value = "/loan", produces = UrlConstants.PRODUCES)
public class LoanController {
    @Autowired
    BaseService baseService;

    @RequestMapping(value = "/default")
    public ModelAndView loanDefault(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/front/loan/loan_default");
        return modelAndView;
    }


}

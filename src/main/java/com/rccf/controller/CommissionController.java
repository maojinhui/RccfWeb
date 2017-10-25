package com.rccf.controller;

import com.rccf.constants.UrlConstants;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping(value = "/commission", produces = UrlConstants.PRODUCES)
public class CommissionController {


    @RequestMapping(value = "/notifytarget")
    public ModelAndView notifyTarget(HttpServletRequest request, HttpServletResponse response) {

        return new ModelAndView();
    }

    @ResponseBody
    @RequestMapping(value = "/getcommission")
    public String getEmployeeCommission(HttpServletRequest request, HttpServletResponse reponse) {
        String sql = "";

        return null;
    }


}

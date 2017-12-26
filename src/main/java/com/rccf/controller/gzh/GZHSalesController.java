package com.rccf.controller.gzh;


import com.rccf.constants.UrlConstants;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/gzh/sales" , produces = UrlConstants.PRODUCES)
public class GZHSalesController {


    @RequestMapping(value = "/index")
    public ModelAndView index(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/gzh/sales/index");
        return modelAndView;
    }

    @RequestMapping(value = "/customer/list")
    public ModelAndView customerListPage(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/gzh/sales/customer_list");
        return modelAndView;
    }



}

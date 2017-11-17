package com.rccf.controller.produce;


import com.rccf.constants.UrlConstants;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(value = "/prod", produces = UrlConstants.PRODUCES)
public class ProduceController {

    @RequestMapping(value = "/listPage")
    public ModelAndView listPage(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/back/product/p_product_list");
        return modelAndView;
    }

    @RequestMapping(value = "/diyaDetail")
    public ModelAndView diyaDetail(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/back/product/p_product_diya");
        return modelAndView;
    }


}

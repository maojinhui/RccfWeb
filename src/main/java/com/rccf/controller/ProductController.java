package com.rccf.controller;

import com.rccf.constants.UrlConstants;
import com.rccf.model.ProductDiya;
import com.rccf.service.ProductService;
import com.rccf.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(value = "/product" ,produces = UrlConstants.PRODUCES)
public class ProductController {

    @Autowired
    ProductService service;

    @RequestMapping(value = "/productDy")
    public ModelAndView findProductDiya(HttpServletRequest request){
        String id = request.getParameter("id");
        int pid = Integer.valueOf(id);
        ProductDiya diya = service.fincProductdiyaByID(pid);
        ModelAndView modelAndView = new ModelAndView("/util/productdy_info");
        modelAndView.addObject("product",diya);
        return modelAndView;
    }


}

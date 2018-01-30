package com.rccf.controller.channel;

import com.rccf.constants.UrlConstants;
import com.rccf.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(value = "/channel/data" , produces = UrlConstants.PRODUCES)
public class ChannelDataController {


    @Autowired
    BaseService baseService;


    @RequestMapping(value = "/")
    public ModelAndView xindaiDetailData(HttpServletRequest request){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("");
        return modelAndView;
    }

}

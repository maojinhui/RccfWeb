package com.rccf.controller.gzh;

import com.rccf.constants.UrlConstants;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(value = "/gzh/top" , produces = UrlConstants.PRODUCES)
public class TopController {


    @RequestMapping(value = "/index")
    public ModelAndView dataPage(HttpServletRequest request){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/gzh/manager/general_manager");
        return modelAndView;
    }

    @RequestMapping(value = "/page/yeji/director")
    public ModelAndView directorYejiPage(HttpServletRequest request){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/gzh/manager/director_yeji");
        return modelAndView;
    }


}

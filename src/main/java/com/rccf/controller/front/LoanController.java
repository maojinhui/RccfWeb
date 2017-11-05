package com.rccf.controller.front;

import com.rccf.constants.UrlConstants;
import com.rccf.service.BaseService;
import com.rccf.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
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


    @RequestMapping(value = "/baodan")
    public ModelAndView loanBaodan(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/front/loan/loan_baodan");
        return modelAndView;
    }


    @RequestMapping(value = "/xinshui")
    public ModelAndView loanXinshui(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/front/loan/loan_xinshui");
        return modelAndView;
    }

    @RequestMapping(value = "/shengyi")
    public ModelAndView loanshengyi(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/front/loan/loan_shengyi");
        return modelAndView;
    }

    @RequestMapping(value = "/chedai")
    public ModelAndView loanchedai(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/front/loan/loan_chedai");
        return modelAndView;
    }

    @RequestMapping(value = "/jingyingdai")
    public ModelAndView loanjingyingdai(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/front/loan/loan_jingyingdai");
        return modelAndView;
    }

    @ResponseBody
    @RequestMapping(value = "/applyloan")
    public String applyLoan(HttpServletRequest request){
        String type = request.getParameter("type");
        return ResponseUtil.success();
    }


}

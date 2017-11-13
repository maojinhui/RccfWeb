package com.rccf.controller.front;

import com.rccf.constants.UrlConstants;
import com.rccf.model.User;
import com.rccf.service.BaseService;
import com.rccf.service.UserService;
import com.rccf.util.ResponseUtil;
import com.rccf.util.weixin.WeixinUtil;
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

    @Autowired
    UserService userService;

    @RequestMapping(value = "/default")
    public ModelAndView loanDefault(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/front/loan/main_products");
        return modelAndView;
    }

    @RequestMapping(value = "/diya")
    public ModelAndView loanDiya(HttpServletRequest request) {
        String openid = WeixinUtil.getOpenid(request);
        User user = userService.findUserByOpenid(openid);
        String phone = user.getPhone();

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/front/loan/loan_diya");
        modelAndView.addObject("phone", phone);

        return modelAndView;
    }


    @RequestMapping(value = "/baodan")
    public ModelAndView loanBaodan(HttpServletRequest request) {
        String openid = WeixinUtil.getOpenid(request);
        User user = userService.findUserByOpenid(openid);
        String phone = user.getPhone();

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/front/loan/loan_baodan");
        modelAndView.addObject("phone", phone);

        return modelAndView;
    }


    @RequestMapping(value = "/xinshui")
    public ModelAndView loanXinshui(HttpServletRequest request) {
        String openid = WeixinUtil.getOpenid(request);
        User user = userService.findUserByOpenid(openid);
        String phone = user.getPhone();

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/front/loan/loan_xinshui");
        modelAndView.addObject("phone", phone);
        return modelAndView;
    }

    @RequestMapping(value = "/shengyi")
    public ModelAndView loanshengyi(HttpServletRequest request) {
        String openid = WeixinUtil.getOpenid(request);
        User user = userService.findUserByOpenid(openid);
        String phone = user.getPhone();

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/front/loan/loan_shengyi");
        modelAndView.addObject("phone", phone);
        return modelAndView;
    }

    @RequestMapping(value = "/chedai")
    public ModelAndView loanchedai(HttpServletRequest request) {
        String openid = WeixinUtil.getOpenid(request);
        User user = userService.findUserByOpenid(openid);
        String phone = user.getPhone();

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/front/loan/loan_chedai");
        modelAndView.addObject("phone", phone);
        return modelAndView;
    }

    @RequestMapping(value = "/jingyingdai")
    public ModelAndView loanjingyingdai(HttpServletRequest request) {
        String openid = WeixinUtil.getOpenid(request);
        User user = userService.findUserByOpenid(openid);
        String phone = user.getPhone();

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/front/loan/loan_jingyingdai");
        modelAndView.addObject("phone", phone);
        return modelAndView;
    }

    @ResponseBody
    @RequestMapping(value = "/applyloan")
    public String applyLoan(HttpServletRequest request){
        String type = request.getParameter("type");
        return ResponseUtil.success();
    }


}

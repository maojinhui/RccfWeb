package com.rccf.controller;

import com.rccf.component.SpyMemcachedManager;
import com.rccf.constants.ResponseConstants;
import com.rccf.model.Loanapply;
import com.rccf.service.BaseService;
import com.rccf.service.LoanApplyService;
import com.rccf.util.DateUtil;
import com.rccf.util.ResponseUtil;
import com.rccf.util.SpringContextUtil;
import com.rccf.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Controller
@RequestMapping(value = "/advert", produces = {"text/html;charset=UTF-8;"})
public class AdvertController {

    @Autowired
    private BaseService baseService;

    @Autowired
    private LoanApplyService loanApplyService;

    @RequestMapping(value = "/weixin01")
    public ModelAndView advertPage(HttpServletRequest request) {
        String from = request.getParameter("from");
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("ad/weixin_advert01");
        if (null != from) {
            modelAndView.addObject("from", from);
        }
        return modelAndView;
    }


    @ResponseBody
    @RequestMapping(value = "/apply")
    public String advertApply(HttpServletRequest request) {
        String phone = request.getParameter("phone");
        String name = request.getParameter("name");
        String code = request.getParameter("code");
        if (Strings.isNullOrEmpty(phone)) {
            return ResponseUtil.fail(0, ResponseConstants.MSG_PHONE_NOT_NULL);
        }
        if (Strings.isNullOrEmpty(code)) {
            return ResponseUtil.fail(0, ResponseConstants.MSG_CODE_NOT_NULL);
        }
        SpyMemcachedManager spyMemcachedManager = (SpyMemcachedManager) SpringContextUtil.getBean("memcachedManager");
        String memCode = (String) spyMemcachedManager.get(phone);
        if (!code.equals(memCode)) {
            return ResponseUtil.fail(0, ResponseConstants.MSG_CODE_ERROE);
        }
        Loanapply loanapply=new Loanapply();
        loanapply.setCreateTime(DateUtil.date2Timestamp(new Date()));
        loanapply.setPhone(phone);
        loanapply.setStat(0);
        if (!Strings.isNullOrEmpty(name)){
            loanapply.setRealName(name);
        }
        boolean state = loanApplyService.save(loanapply);
        if (state){
            return ResponseUtil.success();
        }else{
            return ResponseUtil.fail();
        }
    }


}

package com.rccf.controller;

import com.rccf.component.SpyMemcachedManager;
import com.rccf.constants.ResponseConstants;
import com.rccf.service.BaseService;
import com.rccf.util.ResponseUtil;
import com.rccf.util.SpringContextUtil;
import com.rccf.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(value = "/advert", produces = {"text/html;charset=UTF-8;"})
public class AdvertController {

    @Autowired
    private BaseService baseService;

    @RequestMapping(value = "/weixin01")
    public String advertPage() {
        return "ad/weixin_advert02";
    }


    @ResponseBody
    @RequestMapping(value = "/apply")
    public String advertApply(HttpServletRequest request) {
        String phone = request.getParameter("phone");
        String name = request.getParameter("name");
        String code = request.getParameter("code");
        if (Strings.isNullOrEmpty(phone)) {
            return ResponseUtil.fail("0", ResponseConstants.MSG_PHONE_NOT_NULL);
        }

        if (Strings.isNullOrEmpty(code)) {
            return ResponseUtil.fail("0", ResponseConstants.MSG_CODE_NOT_NULL);
        }
        SpyMemcachedManager spyMemcachedManager = (SpyMemcachedManager) SpringContextUtil.getBean("memcachedManager");
        String memCode = (String) spyMemcachedManager.get(phone);
        if (code.equals(memCode)) {
            return ResponseUtil.fail("0", "验证码正确");
        } else {
            return ResponseUtil.fail("0", "验证码错误");
        }


    }


}

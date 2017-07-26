package com.rccf.controller;

import com.rccf.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/advert",produces = {"text/html;charset=UTF-8;"})
public class AdvertController {

    @Autowired
    private BaseService baseService;

    @RequestMapping(value = "/weixin01")
    public String advertPage(){
        return "ad/weixin_advert01";
    }

}

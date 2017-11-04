package com.rccf.controller;

import com.rccf.constants.UrlConstants;
import com.rccf.model.AccessCount;
import com.rccf.service.BaseService;
import com.rccf.util.DateUtil;
import com.rccf.util.NetUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Controller
@RequestMapping(value = "/rccfkg", produces = UrlConstants.PRODUCES)
public class OtherPageController {


    @Autowired
    BaseService baseService;

    @RequestMapping(value = "/home")
    public ModelAndView first(HttpServletRequest request) {
        AccessCount accessCount = new AccessCount();
        accessCount.setHost(NetUtil.getIpAddr(request));
        accessCount.setUrl("/rccfkg/home");
        accessCount.setTime(DateUtil.date2Timestamp(new Date()));
        baseService.save(accessCount);
        return new ModelAndView("/other_page/loushu");
    }

}

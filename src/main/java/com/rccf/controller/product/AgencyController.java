package com.rccf.controller.product;


import com.rccf.constants.UrlConstants;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(value = "/agency", produces = UrlConstants.PRODUCES)
public class AgencyController {


    /**
     * 渠道列表
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/list")
    public ModelAndView agencyList(HttpServletRequest request) {


        return null;
    }


    /**
     * 渠道详情页面
     *
     * @param request
     * @return
     */
    public ModelAndView agencyDetail(HttpServletRequest request) {

        return null;
    }

    /**
     * 添加渠道
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/addAgencyPage")
    public ModelAndView addAgencyPage(HttpServletRequest request) {

        return null;
    }

    /**
     * 添加渠道
     *
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/addAgency")
    public String addAgency(HttpServletRequest request) {

        return null;
    }


}

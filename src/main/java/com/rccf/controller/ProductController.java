package com.rccf.controller;

import com.rccf.component.Page;
import com.rccf.constants.UrlConstants;
import com.rccf.model.*;
import com.rccf.model.produce.ProduceArea;
import com.rccf.model.produce.ProduceHouseNature;
import com.rccf.service.BankRateService;
import com.rccf.service.BaseService;
import com.rccf.service.ProductService;
import com.rccf.util.PageUtil;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping(value = "/product", produces = UrlConstants.PRODUCES)
public class ProductController {

    @Autowired
    ProductService productService;

    @Autowired
    BaseService baseService;

    @Autowired
    BankRateService bankRateService;


    @RequestMapping(value = "/productDy")
    public ModelAndView findProductDiya(HttpServletRequest request) {
        String id = request.getParameter("id");
        int pid = 0;
        try {
            pid = Integer.valueOf(id);
        } catch (NumberFormatException e) {
            return new ModelAndView("/common/errorMsg").addObject("errorMsg", "参数类型错误");
        }
        ProductDiya diya = productService.fincProductdiyaByID(pid);

        DetachedCriteria areaDetachedCriteria = DetachedCriteria.forClass(ProduceArea.class);
        int areacount = baseService.getCount(areaDetachedCriteria);
        Page page = PageUtil.createPage(50,areacount,0);
        List<ProduceArea> areaArray = baseService.getList(page,areaDetachedCriteria);

        DetachedCriteria natureDetached = DetachedCriteria.forClass(ProduceHouseNature.class);
        int natureCount = baseService.getCount(natureDetached);
        Page naturePage = PageUtil.createPage(50,natureCount,0);
        List<ProduceHouseNature> natureList = baseService.getList(naturePage,natureDetached);

        BankLoanRate rate = bankRateService.findLastRate();




        ModelAndView modelAndView = new ModelAndView("/util/productdy_info");
        modelAndView.addObject("product", diya);
        modelAndView.addObject("areas",areaArray);
        modelAndView.addObject("natures",natureList);
        modelAndView.addObject("bankrete",rate);
        return modelAndView;
    }

    @RequestMapping(value = "/productZy")
    public ModelAndView findProductZhiya(HttpServletRequest request) {
        String id = request.getParameter("id");
        int pid = 0;
        try {
            pid = Integer.valueOf(id);
        } catch (NumberFormatException e) {
            return new ModelAndView("/common/errorMsg").addObject("errorMsg", "参数类型错误");
        }
        ProductZhiya zhiya = productService.findProductZhiyaByID(pid);
        DetachedCriteria areaDetachedCriteria = DetachedCriteria.forClass(ProduceArea.class);
        int areacount = baseService.getCount(areaDetachedCriteria);
        Page page = PageUtil.createPage(50, areacount, 0);
        List<ProduceArea> areaArray = baseService.getList(page, areaDetachedCriteria);

        DetachedCriteria natureDetached = DetachedCriteria.forClass(ProduceHouseNature.class);
        int natureCount = baseService.getCount(natureDetached);
        Page naturePage = PageUtil.createPage(50, natureCount, 0);
        List<ProduceHouseNature> natureList = baseService.getList(naturePage, natureDetached);

        DetachedCriteria metrialDetached = DetachedCriteria.forClass(ProducePersonMaterial.class);
        int metrialCount = baseService.getCount(metrialDetached);
        Page metrialPage = PageUtil.createPage(50, metrialCount, 0);
        List<ProducePersonMaterial> materialList = baseService.getList(metrialPage, metrialDetached);


        ModelAndView modelAndView = new ModelAndView("/util/productzy_info");
        modelAndView.addObject("zhiya", zhiya);
        modelAndView.addObject("areas", areaArray);
        modelAndView.addObject("natures", natureList);
        modelAndView.addObject("materials", materialList);

        return modelAndView;


    }



}

package com.rccf.util.produce;

import com.rccf.model.RAgency;
import com.rccf.service.BaseService;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

public class PageUtil {


    /**
     * 向前端页面传递所有机构
     *
     * @param modelAndView
     * @param baseService
     */
    public static void addAgencys(ModelAndView modelAndView, BaseService baseService) {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(RAgency.class);
        detachedCriteria.addOrder(Order.desc("createTime"));
        List<RAgency> agencys = baseService.getList(detachedCriteria);
        modelAndView.addObject("agencys", agencys);
    }







}

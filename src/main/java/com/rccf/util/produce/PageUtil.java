package com.rccf.util.produce;

import com.rccf.model.RAgency;
import com.rccf.model.produce.AProduceCreditType;
import com.rccf.service.BaseService;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.transform.Transformers;
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

    /**
     * 向前端页面传递信贷贷款类型
     *
     * @param modelAndView
     * @param baseService
     */
    public static void addCreditType(ModelAndView modelAndView, BaseService baseService) {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(AProduceCreditType.class);
        ProjectionList pList = Projections.projectionList();
        pList.add(Projections.property("id").as("id"));
        pList.add(Projections.property("name").as("name"));
        detachedCriteria.setProjection(pList);
        detachedCriteria.addOrder(Order.asc("id"));
        detachedCriteria.setResultTransformer(Transformers.aliasToBean(AProduceCreditType.class));
        List<AProduceCreditType> types = baseService.getList(detachedCriteria);
        modelAndView.addObject("credit_types", types);
    }





}

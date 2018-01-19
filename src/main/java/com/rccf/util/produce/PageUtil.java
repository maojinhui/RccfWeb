package com.rccf.util.produce;

import com.rccf.model.produce.AProduceCreditMaterialCompany;
import com.rccf.model.produce.AProduceCreditMaterialPerson;
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


//    /**
//     * 向前端页面传递所有机构
//     *
//     * @param modelAndView
//     * @param baseService
//     */
//    public static void addAgencys(ModelAndView modelAndView, BaseService baseService) {
//        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(RAgency.class);
//        detachedCriteria.addOrder(Order.desc("createTime"));
//        List<RAgency> agencys = baseService.getList(detachedCriteria);
//        modelAndView.addObject("agencys", agencys);
//    }

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

    /**
     * 添加信贷个人资料
     * @param modelAndView
     * @param baseService
     */
    public static void addCreditMaterialPerson(ModelAndView modelAndView , BaseService baseService){
        DetachedCriteria criteria = DetachedCriteria.forClass(AProduceCreditMaterialPerson.class);
        ProjectionList pList = Projections.projectionList();
        pList.add(Projections.property("id").as("id"));
        pList.add(Projections.property("name").as("name"));
        criteria.setProjection(pList);
        criteria.addOrder(Order.asc("id"));
        criteria.setResultTransformer(Transformers.aliasToBean(AProduceCreditMaterialPerson.class));
        List<AProduceCreditMaterialPerson> materials = baseService.getList(criteria);
        modelAndView.addObject("credit_person_material" , materials);
    }



    /**
     * 添加信贷公司材料
     * @param modelAndView
     * @param baseService
     */
    public static void addCreditMaterialCompany(ModelAndView modelAndView , BaseService baseService){
        DetachedCriteria criteria = DetachedCriteria.forClass(AProduceCreditMaterialCompany.class);
        ProjectionList pList = Projections.projectionList();
        pList.add(Projections.property("id").as("id"));
        pList.add(Projections.property("name").as("name"));
        criteria.setProjection(pList);
        criteria.addOrder(Order.asc("id"));
        criteria.setResultTransformer(Transformers.aliasToBean(AProduceCreditMaterialCompany.class));
        List<AProduceCreditMaterialCompany> materials = baseService.getList(criteria);
        modelAndView.addObject("credit_company_material" , materials);
    }

}

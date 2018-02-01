package com.rccf.util.accept;

import com.rccf.model.Employee;
import com.rccf.model.gzh.accpet.AcceptedTemp;
import com.rccf.service.BaseService;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

public class AcceptUtil {


    /**
     * 获取销售部业务员通知个数
     * @param baseService
     * @param code
     * @return
     */
    public static int getSalesNotificationCount(BaseService baseService,String code){
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(AcceptedTemp.class);
        detachedCriteria.add(Restrictions.eq("state" , 2));
        detachedCriteria.add(Restrictions.eq("employee" , code));
        List<AcceptedTemp> list =    baseService.getList(detachedCriteria);
        if(list!=null){
            return list.size();
        }
        return 0;
    }

    /**
     * 销售页面上添加提示个数
     * @param baseService
     * @param employee
     * @param modelAndView
     * @return
     */
    public static ModelAndView addSalesNotificationCount(BaseService baseService, Employee employee,ModelAndView modelAndView){
        int acceptNotificationCount = getSalesNotificationCount(baseService,employee.getCode());
        modelAndView.addObject("acceptNotificationCount" ,acceptNotificationCount );
        return modelAndView;
    }

    /**
     * 获取市场部后期专员通知个数
     * @param baseService
     * @param eID
     * @return
     */
    public static int getHouqiNotificationCount(BaseService baseService,int eID){
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(AcceptedTemp.class);
        detachedCriteria.add(Restrictions.eq("state" , 4));
        detachedCriteria.add(Restrictions.eq("houqi" , eID));
        List<AcceptedTemp> list =    baseService.getList(detachedCriteria);
        if(list!=null){
            return list.size();
        }
        return 0;
    }

    /**
     * 市场部页面上添加提示个数
     * @param baseService
     * @param employee
     * @param modelAndView
     * @return
     */
    public static ModelAndView addHouqiNotificationCount(BaseService baseService, Employee employee,ModelAndView modelAndView){
        int acceptNotificationCount = getHouqiNotificationCount(baseService,employee.getId());
        modelAndView.addObject("acceptNotificationCount" ,acceptNotificationCount );
        return modelAndView;
    }




}

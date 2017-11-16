package com.rccf.util.verify;

import com.rccf.service.BaseService;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import java.util.List;

public class BaseVerify {


    /**
     * 判断一个类  某个属性包含某个
     *
     * @param baseService
     * @param clazz
     * @param property
     * @param value
     * @return
     */
    public static boolean hasObjectWithClassPropertise(BaseService baseService, Class clazz, String property, String value) {
        DetachedCriteria criteria = DetachedCriteria.forClass(clazz);
        criteria.add(Restrictions.eq(property, value));
        List list = baseService.getList(criteria);
        if (list != null && list.size() > 0) {
            return true;
        }
        return false;
    }


}

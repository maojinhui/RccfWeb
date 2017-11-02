package com.rccf.util.verify;

import com.rccf.model.RCustomer;
import com.rccf.service.BaseService;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class CustomerVerify {


    @Autowired
    private static BaseService baseService;

    /**
     * 查询手机号是否已经存在在客户
     *
     * @param phone
     * @return
     */
    public static boolean hasCustomerByPhone(String phone) {
        DetachedCriteria criteria = DetachedCriteria.forClass(RCustomer.class);
        criteria.add(Restrictions.eq("phone", phone));
        List list = baseService.getList(criteria);
        if (list != null && list.size() > 0) {
            return true;
        }
        return false;
    }


    /**
     * 查询客户姓名是否已经存在在客户
     *
     * @param name
     * @return
     */
    public static boolean hasCustomerByName(String name) {
        DetachedCriteria criteria = DetachedCriteria.forClass(RCustomer.class);
        criteria.add(Restrictions.eq("name", name));
        List list = baseService.getList(criteria);
        if (list != null && list.size() > 0) {
            return true;
        }
        return false;
    }

}

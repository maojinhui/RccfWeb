package com.rccf.util.verify;

import com.rccf.model.RAgency;
import com.rccf.service.BaseService;
import com.rccf.util.Strings;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import java.util.List;

public class AgencyVerify {


    /**
     * 查看是否有该机构名称
     *
     * @param baseService
     * @param name
     * @return
     */
    public static boolean hasAgencyName(BaseService baseService, String name) {
        if (Strings.isNullOrEmpty(name)) {
            return false;
        }
        DetachedCriteria criteria = DetachedCriteria.forClass(RAgency.class);
        criteria.add(Restrictions.eq("name", name));
        List list = baseService.getList(criteria);
        if (list != null && list.size() > 0) {
            return true;
        }
        return false;
    }


    /**
     * 查看是否有该机构名称
     *
     * @param baseService
     * @param code
     * @return
     */
    public static boolean hasAgencyCode(BaseService baseService, String code) {
        if (Strings.isNullOrEmpty(code)) {
            return false;
        }
        DetachedCriteria criteria = DetachedCriteria.forClass(RAgency.class);
        criteria.add(Restrictions.eq("code", code));
        List list = baseService.getList(criteria);
        if (list != null && list.size() > 0) {
            return true;
        }
        return false;
    }


}

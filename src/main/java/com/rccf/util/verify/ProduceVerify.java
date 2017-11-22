package com.rccf.util.verify;

import com.rccf.model.AProduceDiya;
import com.rccf.model.RAgency;
import com.rccf.service.BaseService;
import com.rccf.util.Strings;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import java.util.List;

public class ProduceVerify {


    /**
     * 检验是否有产品编号
     *
     * @param baseService
     * @param code
     * @return
     */
    public static boolean hasProduceCode(BaseService baseService, String code) {
        if (Strings.isNullOrEmpty(code)) {
            return false;
        }
        DetachedCriteria criteria = DetachedCriteria.forClass(AProduceDiya.class);
        criteria.add(Restrictions.eq("code", code));
        List list = baseService.getList(criteria);
        if (list != null && list.size() > 0) {
            return true;
        }
        return false;
    }
}

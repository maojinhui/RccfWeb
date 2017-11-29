package com.rccf.util.verify;

import com.rccf.model.produce.AProduceDiya;
import com.rccf.model.produce.AProduceZhiya;
import com.rccf.model.produce.BaseProduct;
import com.rccf.service.BaseService;
import com.rccf.util.Strings;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import java.util.List;

public class ProduceVerify {


    /**
     * 通过id检验产品是否存在
     *
     * @param baseService
     * @param id
     * @return
     */
    public static boolean hasDiyaProduceByID(BaseService baseService, String id, String type) {
        if (!Strings.isNullOrEmpty(id) && !Strings.isNullOrEmpty(type)) {
            try {
                int _type = Integer.valueOf(type);
                int _id = Integer.valueOf(id);
                switch (_type) {
                    case 1:
                        AProduceDiya diya = (AProduceDiya) baseService.get(AProduceDiya.class, _id);
                        if (diya != null) {
                            return true;
                        }
                        break;
                    case 2:
                        AProduceZhiya zhiya = (AProduceZhiya) baseService.get(AProduceZhiya.class, _id);
                        if (zhiya != null) {
                            return true;
                        }
                        break;
                    case 0:
                        break;
                    default:
                        break;
                }


            } catch (NumberFormatException e) {
                e.printStackTrace();
                return false;
            }


        }
        return false;
    }

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

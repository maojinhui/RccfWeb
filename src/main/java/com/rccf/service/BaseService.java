package com.rccf.service;

import com.rccf.dao.BaseDao;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BaseService {

    @Autowired
    private BaseDao dao;

    /**
     * 查询总条数
     * @param clazz
     * @param detachedCriteria 可以为null
     * @return
     */
    public long getCount(Class clazz,DetachedCriteria detachedCriteria){
        if (null == clazz){
            return 0;
        }
        if (null==detachedCriteria){
            return  dao.getCount(clazz);
        }else{
            return dao.getCount(clazz, detachedCriteria);
        }
    }






}

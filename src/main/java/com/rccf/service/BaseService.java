package com.rccf.service;

import com.rccf.component.Page;
import com.rccf.dao.BaseDao;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BaseService {

    @Autowired
    private BaseDao dao;

    /**
     * 保存
     *
     * @param o
     */
    public boolean save(Object o) {
        return dao.save(o);
    }

    public boolean delete(Object o) {
        return dao.delete(o);
    }

    /**
     * 查询总条数
     *
     * @param detachedCriteria 可以为null
     * @return
     */
    public int getCount(DetachedCriteria detachedCriteria) {
        return dao.getCount(detachedCriteria);
    }


    public List getList(Page page, DetachedCriteria detachedCriteria) {
        return dao.getList(page, detachedCriteria);
    }


    public List getList(DetachedCriteria detachedCriteria) {
        return dao.getList(detachedCriteria);
    }

    public List queryBySql(String sql) {
        List<Object[]> list = dao.queryBySql(sql);
        return list;
    }

    public String queryMaxBySql(String sql) {
        return dao.queryMaxBySql(sql);
    }

    public List queryBySqlFormatObject(String sql) {
        return dao.queryBySqlFormatObject(sql);
    }

    public List queryBySqlFormatClass(String sql, Class clazz) {
        return dao.queryBySqlFormatClass(sql, clazz);
    }

    public boolean excuteSql(String sql) {
        return dao.excuteSql(sql);
    }

}

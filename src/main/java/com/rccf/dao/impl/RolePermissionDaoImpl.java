package com.rccf.dao.impl;

/**
 * Created by greatland on 17/7/11.
 */

import com.rccf.dao.RolePermissionDao;
import com.rccf.model.RolePermission;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

@Repository
public class RolePermissionDaoImpl extends HibernateDaoSupport implements RolePermissionDao{

    @Resource(name = "sessionFactory")
    public void setSuperSessionFactory(SessionFactory sessionFactory) {
        super.setSessionFactory(sessionFactory);
    }


    public void add(RolePermission rolePermission) {
        getHibernateTemplate().save(rolePermission);
    }

    public void deleteById(int id) {

    }

    public List<RolePermission> list() {

        return null;
    }
}

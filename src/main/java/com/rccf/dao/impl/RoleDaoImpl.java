package com.rccf.dao.impl;

import com.rccf.dao.RoleDao;
import com.rccf.model.Role;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

/**
 * Created by greatland on 17/7/11.
 */
@Repository
public class RoleDaoImpl extends HibernateDaoSupport implements RoleDao {

    @Autowired
    public void setSuperSessionFactory(SessionFactory sessionFactory) {
        super.setSessionFactory(sessionFactory);
    }


    public void add(Role role) {
        getHibernateTemplate().save(role);
    }

    public Role findRoleByID(int id) {
        Role role = getHibernateTemplate().get(Role.class,id);
        return role;
    }
}

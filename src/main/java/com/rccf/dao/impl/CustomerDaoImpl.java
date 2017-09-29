package com.rccf.dao.impl;

import com.rccf.dao.CustomerDao;
import com.rccf.model.Customer;
import com.rccf.model.CustomerProcess;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;

import javax.annotation.Resource;

public class CustomerDaoImpl extends HibernateDaoSupport implements CustomerDao {

    @Resource(name = "sessionFactory")
    public void setSuperSessionFactory(SessionFactory sessionFactory) {
        super.setSessionFactory(sessionFactory);
    }

    public boolean saveCustomer(Customer customer) {
        try {

        } catch (Exception e) {

        }

        return false;
    }

    public boolean saveProcess(CustomerProcess customerProcess) {
        return false;
    }

    public void findCustomerByID(int id) {

    }

    public void findCustomerProcessById(int id) {

    }
}

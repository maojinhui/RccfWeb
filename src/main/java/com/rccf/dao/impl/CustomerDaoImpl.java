package com.rccf.dao.impl;

import com.rccf.dao.CustomerDao;
import com.rccf.model.AcceptProcess;
import com.rccf.model.Customer;
import com.rccf.model.CustomerProcess;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

@Repository
public class CustomerDaoImpl extends HibernateDaoSupport implements CustomerDao {

    @Resource(name = "sessionFactory")
    public void setSuperSessionFactory(SessionFactory sessionFactory) {
        super.setSessionFactory(sessionFactory);
    }

    public boolean saveCustomer(Customer customer) {
        try {
            getHibernateTemplate().saveOrUpdate(customer);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean saveProcess(CustomerProcess customerProcess) {
        try {
            getHibernateTemplate().saveOrUpdate(customerProcess);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public Customer findCustomerByID(int id) {
        return getHibernateTemplate().get(Customer.class, id);
    }

    public CustomerProcess findCustomerProcessById(int id) {
        return getHibernateTemplate().get(CustomerProcess.class, id);
    }

    public List<CustomerProcess> listCustomerProcess(int customerId) {
        DetachedCriteria criteria = DetachedCriteria.forClass(CustomerProcess.class);
        criteria.add(Restrictions.eq("customerId", customerId));
        criteria.addOrder(Order.desc("updateTime"));
        List<CustomerProcess> processes = (List<CustomerProcess>) getHibernateTemplate().findByCriteria(criteria);
        return processes;
    }

    public boolean deleteCustomerProcess(int processid) {
        try {
            CustomerProcess customerProcess = findCustomerProcessById(processid);
            getHibernateTemplate().delete(customerProcess);
            return true;
        } catch (Exception e) {
            return false;
        }
    }


}

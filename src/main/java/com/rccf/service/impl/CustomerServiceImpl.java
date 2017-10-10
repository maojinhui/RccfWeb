package com.rccf.service.impl;

import com.rccf.dao.CustomerDao;
import com.rccf.model.Customer;
import com.rccf.model.CustomerProcess;
import com.rccf.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {


    @Autowired
    CustomerDao customerDao;


    public boolean saveCustomer(Customer customer) {
        return customerDao.saveCustomer(customer);
    }

    public boolean saveProcess(CustomerProcess customerProcess) {
        return customerDao.saveProcess(customerProcess);
    }

    public Customer findCustomerByID(int id) {
        return customerDao.findCustomerByID(id);
    }

    public CustomerProcess findCustomerProcessById(int id) {
        return customerDao.findCustomerProcessById(id);
    }

    public List<CustomerProcess> listCustomerProcess(int customer_id) {
        return customerDao.listCustomerProcess(customer_id);
    }

    public boolean deleteCustomerProcess(int processid) {
        return customerDao.deleteCustomerProcess(processid);
    }
}

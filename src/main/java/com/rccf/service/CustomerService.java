package com.rccf.service;

import com.rccf.dao.CustomerDao;
import com.rccf.model.Customer;
import com.rccf.model.CustomerProcess;

import java.util.List;

public interface CustomerService {

    boolean saveCustomer(Customer customer);


    boolean saveProcess(CustomerProcess customerProcess);


    Customer findCustomerByID(int id);


    CustomerProcess findCustomerProcessById(int id);


    List<CustomerProcess> listCustomerProcess(int id);

    boolean deleteCustomerProcess(int processid);

}

package com.rccf.dao;

import com.rccf.model.Customer;
import com.rccf.model.CustomerProcess;

public interface CustomerDao {

    boolean saveCustomer(Customer customer);


    boolean saveProcess(CustomerProcess customerProcess);


    void findCustomerByID(int id);


    void findCustomerProcessById(int id);

}

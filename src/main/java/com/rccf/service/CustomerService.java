package com.rccf.service;

import com.rccf.model.Customer;
import com.rccf.model.CustomerProcess;

public interface CustomerService {

    boolean saveCustomer(Customer customer);


    boolean saveProcess(CustomerProcess customerProcess);


    void findCustomerByID(int id);


    void findCustomerProcessById(int id);


}

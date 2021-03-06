package com.rccf.dao;

import com.rccf.model.Customer;
import com.rccf.model.CustomerProcess;

import java.util.List;

public interface CustomerDao {

    boolean saveCustomer(Customer customer);


    boolean saveProcess(CustomerProcess customerProcess);


    Customer findCustomerByID(int id);


    CustomerProcess findCustomerProcessById(int id);


    List<CustomerProcess> listCustomerProcess(int customerid);


    boolean deleteCustomerProcess(int processid);

}

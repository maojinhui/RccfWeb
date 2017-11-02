package com.rccf.dao;

import com.rccf.model.RCustomer;

import java.util.List;


public interface RCustomerDao {

    RCustomer findRCustomerByID(String id);

    List<RCustomer> findRCustomerByName(String name);

    List<RCustomer> findRCustomerByPhone(String phone);

}

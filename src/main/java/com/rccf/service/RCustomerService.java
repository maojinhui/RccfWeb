package com.rccf.service;

import com.rccf.model.RCustomer;
import com.rccf.model.RCustomerWork;

import java.util.List;

public interface RCustomerService {

    RCustomer findRCustomerByID(String id);

    List<RCustomer> findRCustomerByName(String name);

    List<RCustomer> findRCustomerByPhone(String phone);

    RCustomerWork findRCustomerWorkByCustomerid(String customer_id);


}

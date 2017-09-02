package com.rccf.dao;

import com.rccf.model.Accepted;

public interface AcceptedDao {

    boolean saveOrUpdate(Accepted accepted);

    Accepted findById(int id);

    Accepted findByClerk(String clerk);
}

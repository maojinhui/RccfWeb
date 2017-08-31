package com.rccf.service;

import com.rccf.model.Accepted;

public interface AcceptedService {

    boolean saveOrUpdate(Accepted accepted);

    Accepted findById(int id);


}

package com.rccf.dao;

import com.rccf.model.AcceptProcess;
import com.rccf.model.Accepted;

import java.util.List;

public interface AcceptedDao {

    boolean saveOrUpdate(Accepted accepted);

    Accepted findById(int id);

    Accepted findByClerk(String clerk);

    boolean saveProcess(AcceptProcess process);

    List<AcceptProcess> listProcessDetail(int accept_id);

    AcceptProcess findProcessByid(int id);

    boolean deleteProcessByID(int id);
}

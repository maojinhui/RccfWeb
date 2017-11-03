package com.rccf.service;

import com.rccf.model.Employee;
import com.rccf.model.EmployeeBase;
import com.rccf.model.EmployeeContract;
import com.rccf.model.EmployeeDocuments;

import java.util.List;

public interface EmployeeService {

    Employee findEmpolyeeById(int id);

    Employee findEmpolyeeByCode(String code);

    Employee findEmpolyeeByName(String name);

    Employee findEmpolyeeByPhone(String phone);

    boolean saveOrUpdate(Employee employee);

    boolean deleteEmployee(Employee employee);


    /**
     * @param code
     * @return
     */
    List<Employee> findEmployeesByCode(String code);

    EmployeeContract findContractByid(int id);

    EmployeeBase findBaseById(int id);

}

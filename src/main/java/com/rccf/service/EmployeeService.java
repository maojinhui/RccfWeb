package com.rccf.service;

import com.rccf.model.Employee;

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

}

package com.rccf.dao;

import com.rccf.model.Employee;

import java.util.List;

public interface EmployeeDao {

    Employee findEmpolyeeById(int id);

    Employee findEmpolyeeByCode(String code);

    Employee findEmpolyeeByName(String name);

    Employee findEmpolyeeByPhone(String phone);

    boolean saveOrUpdate(Employee employee);

    boolean deleteEmployee(Employee employee);

    List<Employee> findEmployeesByCode(String code);

}

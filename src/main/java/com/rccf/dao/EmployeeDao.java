package com.rccf.dao;

import com.rccf.model.Employee;
import com.rccf.model.EmployeeBase;
import com.rccf.model.EmployeeContract;

import java.util.List;

public interface EmployeeDao {

    Employee findEmpolyeeById(int id);

    Employee findEmpolyeeByCode(String code);

    Employee findEmpolyeeByName(String name);

    Employee findEmpolyeeByPhone(String phone);

    boolean saveOrUpdate(Employee employee);

    boolean deleteEmployee(Employee employee);

    List<Employee> findEmployeesByCode(String code);

    EmployeeContract findEmployeeContractById(int id);

    EmployeeBase findBaseById(int id);

}

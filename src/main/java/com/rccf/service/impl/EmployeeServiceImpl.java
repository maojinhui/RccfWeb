package com.rccf.service.impl;

import com.rccf.dao.EmployeeDao;
import com.rccf.model.Employee;
import com.rccf.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {


    @Autowired
    private EmployeeDao employeeDao;

    public Employee findEmpolyeeById(int id) {
        return employeeDao.findEmpolyeeById(id);
    }

    public Employee findEmpolyeeByCode(String code) {
        return employeeDao.findEmpolyeeByCode(code);
    }

    public Employee findEmpolyeeByName(String name) {
        return employeeDao.findEmpolyeeByName(name);
    }

    public Employee findEmpolyeeByPhone(String phone) {
        return employeeDao.findEmpolyeeByPhone(phone);
    }

    public boolean saveOrUpdate(Employee employee) {
        return employeeDao.saveOrUpdate(employee);
    }

    public boolean deleteEmployee(Employee employee) {
        return employeeDao.deleteEmployee(employee);
    }

    public List<Employee> findEmployeesByCode(String code) {
        return employeeDao.findEmployeesByCode(code);
    }

}

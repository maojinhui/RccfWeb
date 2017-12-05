package com.rccf.util.produce;

import com.rccf.model.Employee;
import com.rccf.model.produce.AProduceCredit;
import com.rccf.service.BaseService;
import org.springframework.web.servlet.ModelAndView;

public class CreditProducePage {


    public static  void addCreatePerson(ModelAndView modelAndView, AProduceCredit produce , BaseService baseService) {
        Integer createPerson = produce.getCreatePerson();
        Employee employee = (Employee) baseService.get(Employee.class, createPerson);
        if (employee != null) {
            modelAndView.addObject("createPName", employee.getName());
        }
    }

}

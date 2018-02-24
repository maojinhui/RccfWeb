package com.rccf.controller.gzh;

import com.rccf.constants.UrlConstants;
import com.rccf.model.Employee;
import com.rccf.model.LatterNumber;
import com.rccf.model.gzh.accpet.AcceptedTemp;
import com.rccf.service.BaseService;
import com.rccf.service.EmployeeService;
import com.rccf.util.BackUtil;
import com.rccf.util.ResponseUtil;
import com.rccf.util.Strings;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping(value = "/gzh/acceptcenter" , produces = UrlConstants.PRODUCES)
public class GZHAcceptCenterController {

    @Autowired
    BaseService baseService;

    @Autowired
    EmployeeService employeeService;


    @RequestMapping(value = "/accept/list")
    public ModelAndView acceptList(HttpServletRequest request){
        Employee employee = BackUtil.getLoginEmployee(request,employeeService);
        if (employee == null || employee.getState() == null || employee.getState() != 1) {
            return ResponseUtil.pageFail("用户登录状态有误");
        }
        ModelAndView modelAndView = new ModelAndView("/gzh/acceptcenter/index");
        return modelAndView;
    }



    @RequestMapping(value = "/accept/acceptCenterinfo")
    public ModelAndView acceptAcceptInfo(HttpServletRequest request) {
        Employee employee = BackUtil.getLoginEmployee(request, employeeService);
        String accept_id = request.getParameter("accept_id");
        if (employee == null || employee.getState() == null || employee.getState() != 1) {
            return ResponseUtil.pageFail("用户登录状态有误");
        }
        if (Strings.isNullOrEmpty(accept_id)) {
            return ResponseUtil.pageFail("上传信息错误");
        }
        AcceptedTemp acceptedTemp = (AcceptedTemp) baseService.get(AcceptedTemp.class, accept_id);
        if (acceptedTemp == null) {
            return ResponseUtil.pageFail("受理单信息有误");
        }
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("gzh/acceptcenter/acceptCenter");
        modelAndView.addObject("accept", acceptedTemp);

        DetachedCriteria criteria = DetachedCriteria.forClass(LatterNumber.class);
        ProjectionList list = Projections.projectionList();
        list.add(Projections.property("code").as("code"));
        list.add(Projections.property("id").as("id"));
        criteria.setProjection(list);
        criteria.setResultTransformer(Transformers.aliasToBean(LatterNumber.class));
        List<LatterNumber> numbers = baseService.getList(criteria);
        modelAndView.addObject("numbers", numbers);


        return modelAndView;
    }



}

package com.rccf.controller;

import com.rccf.constants.UrlConstants;
import com.rccf.model.Accepted;
import com.rccf.model.Employee;
import com.rccf.service.EmployeeService;
import com.rccf.util.DateUtil;
import com.rccf.util.ResponseUtil;
import com.rccf.util.file.ImportUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping(value = "/other", produces = UrlConstants.PRODUCES)
public class OtherController {


    private static int code = 5;

    @Autowired
    EmployeeService employeeService;


    @RequestMapping(value = "/importexcel")
    public ModelAndView importExcel() {
        return new ModelAndView("/other/accepted_excel");
    }


    @RequestMapping(value = "/importAccepted")
    public ModelAndView importAccepted(HttpServletRequest request, @RequestPart(value = "upload") MultipartFile file) {
//        MultipartRequest multipartRequest = (MultipartRequest) request;
        System.out.println("通过传统方式form表单提交方式导入excel文件！");


        InputStream in = null;
        List<List<Object>> listob = null;
//        MultipartFile file = multipartRequest.getFile("upload");

        if (file.isEmpty()) {
//            throw new Exception("文件不存在！");
            return new ModelAndView("/other/import_fail");
        }
        try {
            in = file.getInputStream();
            listob = new ImportUtil().getBankListByExcel(in, file.getOriginalFilename());
            in.close();
            //该处可调用service相应方法进行数据保存到数据库中，现只对数据输出
            int j = 5;
            for (int i = 0; i < listob.size(); i++) {
                if (i < 3) {
                    continue;
                }
                List<Object> lo = listob.get(i);
                String fuzongjian = (String) lo.get(22);
                String zongjian = (String) lo.get(23);
                Employee fuemployee = employeeService.findEmpolyeeByName(fuzongjian);
                Employee employee = employeeService.findEmpolyeeByName(zongjian);
                if (fuemployee == null && fuzongjian != "无" && fuzongjian != "") {
                    employee = new Employee();
                    employee.setRole(2);
                    employee.setCreateTime(DateUtil.date2Timestamp(new Date(System.currentTimeMillis())));
                    if (j > 10) {
                        employee.setCode("s0" + i);
                    } else if (j > 100) {
                        employee.setCode("s0" + i);
                    } else {
                        employee.setCode("s00" + i);
                    }
                    employee.setLeader(employee.getCode());
                    employee.setName(zongjian);
                    employee.setDuties("总监");
                    employeeService.saveOrUpdate(employee);
                    i++;
                } else {
                    continue;
                }
//                Accepted accepted = new Accepted();
//                accepted.setId(Integer.valueOf((String) lo.get(0)));
//                accepted.setCreateTime();
//                System.out.print(lo.get(0));
//                System.out.print(lo.get(1));
//                System.out.print(lo.get(2));
//                System.out.println(lo.get(3));


            }

            return new ModelAndView("/other/import_success");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new ModelAndView("/other/import_success");
    }

}

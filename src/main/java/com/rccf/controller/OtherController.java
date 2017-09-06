package com.rccf.controller;

import com.rccf.constants.UrlConstants;
import com.rccf.model.Accepted;
import com.rccf.model.Employee;
import com.rccf.service.BaseService;
import com.rccf.service.EmployeeService;
import com.rccf.util.DateUtil;
import com.rccf.util.ResponseUtil;
import com.rccf.util.SpringContextUtil;
import com.rccf.util.file.ImportUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;
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

    @Autowired
    BaseService baseService;


//    @Autowired
//    public DataSourceTransactionManager dataSourceTransactionManager;


    @RequestMapping(value = "/importexcel")
    public ModelAndView importExcel() {
        return new ModelAndView("/other/accepted_excel");
    }


    @RequestMapping(value = "/importAcceptedZongjian")
    public ModelAndView importAccepted(HttpServletRequest request, @RequestPart(value = "upload") MultipartFile file) {
//        MultipartRequest multipartRequest = (MultipartRequest) request;
        System.out.println("通过传统方式form表单提交方式导入excel文件！");
        HibernateTransactionManager transactionManager = (HibernateTransactionManager) SpringContextUtil
                .getBean("txManager");
        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW); // 事物隔离级别，开启新事务，这样会比较安全些。
        TransactionStatus status = transactionManager.getTransaction(def); // 获得事务状态
//        try {
////逻辑代码，可以写上你的逻辑处理代码
//            transactionManager.commit(status);
//        } catch (Exception e) {
//            transactionManager.rollback(status);
//        }


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
            int yewuyuanCode = 5;
            int fuzongjianCode = 1;
            int zongjianCode = 5;

            for (int i = 0; i < listob.size(); i++) {
                if (i < 3) {
                    continue;
                }
                List<Object> lo = listob.get(i);
                String yewuyuan = (String) lo.get(21);
                String fuzongjian = (String) lo.get(22);
                String zongjian = (String) lo.get(23);


                Employee zongjianemployee = employeeService.findEmpolyeeByName(zongjian);
                Employee fuzongjianemployee = employeeService.findEmpolyeeByName(fuzongjian);
                Employee yewuyuanEmployee = employeeService.findEmpolyeeByName(yewuyuan);

                if (zongjianemployee == null && zongjian != "") {
                    zongjianemployee = new Employee();
                    zongjianemployee.setRole(2);
                    zongjianemployee.setCreateTime(DateUtil.date2Timestamp(new Date(System.currentTimeMillis())));
                    if (zongjianCode > 10) {
                        zongjianemployee.setCode("s0" + zongjianemployee);
                    } else if (zongjianCode > 100) {
                        zongjianemployee.setCode("s0" + i);
                    } else {
                        zongjianemployee.setCode("s00" + i);
                    }
                    zongjianemployee.setLeader("1");
                    zongjianemployee.setName(zongjian);
                    zongjianemployee.setDuties("总监");
                    employeeService.saveOrUpdate(zongjianemployee);
                    zongjianCode++;
                }

//                if(fuzongjianemployee ==null){
//                    fuzongjianemployee = new Employee();
//                    fuzongjianemployee.setRole(3);
//                    fuzongjianemployee.setCreateTime(DateUtil.date2Timestamp(new Date(System.currentTimeMillis())));
//                    if (fuzongjianCode > 10) {
//                        zongjianemployee.setCode("f0" + zongjianemployee);
//                    } else if (fuzongjianCode > 100) {
//                        zongjianemployee.setCode("f0" + i);
//                    } else {
//                        zongjianemployee.setCode("f00" + i);
//                    }
//                    if(zongjianemployee!=null){
//                        fuzongjianemployee.setLeader(zongjianemployee.getCode());
//                    }else{
//                        fuzongjianemployee.setLeader("1");
//                    }
//                    zongjianemployee.setName(fuzongjian);
//                    zongjianemployee.setDuties("副总监");
//                    employeeService.saveOrUpdate(zongjianemployee);
//                    fuzongjianCode++;
//                }

//                if(yewuyuanEmployee ==null){
//                    yewuyuanEmployee = new Employee();
//                    yewuyuanEmployee.setRole(4);
//                    yewuyuanEmployee.setCreateTime(DateUtil.date2Timestamp(new Date(System.currentTimeMillis())));
//                    if (yewuyuanCode > 10) {
//                        yewuyuanEmployee.setCode("e0" + zongjianemployee);
//                    } else if (yewuyuanCode > 100) {
//                        yewuyuanEmployee.setCode("e0" + i);
//                    } else {
//                        yewuyuanEmployee.setCode("e00" + i);
//                    }
//
//                    if(fuzongjianemployee!=null){
//                        yewuyuanEmployee.setLeader(fuzongjianemployee.getCode());
//                    }else{
//                        yewuyuanEmployee.setLeader("1");
//                    }
//                    yewuyuanEmployee.setName(fuzongjian);
//                    yewuyuanEmployee.setDuties("副总监");
//                    employeeService.saveOrUpdate(yewuyuanEmployee);
//                    yewuyuanCode++;
//                }

//                Accepted accepted = new Accepted();
//                accepted.setId(Integer.valueOf((String) lo.get(0)));
//                accepted.setCreateTime();
//                System.out.print(lo.get(0));
//                System.out.print(lo.get(1));
//                System.out.print(lo.get(2));
//                System.out.println(lo.get(3));


            }

            transactionManager.commit(status);
            return new ModelAndView("/other/import_success");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            transactionManager.rollback(status);
            e.printStackTrace();
        }

        return new ModelAndView("/other/import_success");
    }


    @RequestMapping(value = "/importYewuyuan")
    public ModelAndView importAcceptedYwuyuan(HttpServletRequest request, @RequestPart(value = "upload") MultipartFile file) {
//        MultipartRequest multipartRequest = (MultipartRequest) request;
//        System.out.println("通过传统方式form表单提交方式导入excel文件！");
        HibernateTransactionManager transactionManager = (HibernateTransactionManager) SpringContextUtil
                .getBean("txManager");
        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW); // 事物隔离级别，开启新事务，这样会比较安全些。
        TransactionStatus status = transactionManager.getTransaction(def); // 获得事务状态

        InputStream in = null;
        List<List<Object>> listob = null;

        if (file.isEmpty()) {
            return new ModelAndView("/other/import_fail");
        }
        try {
            in = file.getInputStream();
            listob = new ImportUtil().getBankListByExcel(in, file.getOriginalFilename());
            in.close();
            //该处可调用service相应方法进行数据保存到数据库中，现只对数据输出
            int yewuyuanCode = 100;
//            int fuzongjianCode = 1;
//            int zongjianCode = 5;

            for (int i = 0; i < listob.size(); i++) {
                if (i < 3) {
                    continue;
                }
                List<Object> lo = listob.get(i);
                String yewuyuan = (String) lo.get(21);
                String fuzongjian = (String) lo.get(22);
                String zongjian = (String) lo.get(23);


//                Employee zongjianemployee = employeeService.findEmpolyeeByName(zongjian);
                Employee fuzongjianemployee = employeeService.findEmpolyeeByName(fuzongjian);
                Employee yewuyuanEmployee = employeeService.findEmpolyeeByName(yewuyuan);

                if (yewuyuanEmployee == null && !yewuyuan.equals("") && !yewuyuan.equals("无")) {
                    yewuyuanEmployee = new Employee();
                    yewuyuanEmployee.setRole(4);
                    yewuyuanEmployee.setCreateTime(DateUtil.date2Timestamp(new Date(System.currentTimeMillis())));
                    if (yewuyuanCode > 10) {
                        yewuyuanEmployee.setCode("e0" + yewuyuanCode);
                    } else if (yewuyuanCode > 100) {
                        yewuyuanEmployee.setCode("e" + yewuyuanCode);
                    } else {
                        yewuyuanEmployee.setCode("e00" + yewuyuanCode);
                    }
                    if (fuzongjianemployee != null) {
                        yewuyuanEmployee.setLeader(fuzongjianemployee.getCode());
                    } else {
                        yewuyuanEmployee.setLeader("1");
                    }
                    yewuyuanEmployee.setName(yewuyuan);
                    yewuyuanEmployee.setDuties("业务员");
                    employeeService.saveOrUpdate(yewuyuanEmployee);
                    yewuyuanCode++;
                }

//                Accepted accepted = new Accepted();
//                accepted.setId(Integer.valueOf((String) lo.get(0)));
//                accepted.setCreateTime();
//                System.out.print(lo.get(0));
//                System.out.print(lo.get(1));
//                System.out.print(lo.get(2));
//                System.out.println(lo.get(3));


            }

            transactionManager.commit(status);
            return new ModelAndView("/other/import_success");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            transactionManager.rollback(status);
            e.printStackTrace();
        }

        return new ModelAndView("/other/import_success");
    }


    @RequestMapping(value = "/importFuzongjian")
    public ModelAndView importAcceptedFuzongjian(HttpServletRequest request, @RequestPart(value = "upload") MultipartFile file) {
//        MultipartRequest multipartRequest = (MultipartRequest) request;
//        System.out.println("通过传统方式form表单提交方式导入excel文件！");
        HibernateTransactionManager transactionManager = (HibernateTransactionManager) SpringContextUtil
                .getBean("txManager");
        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW); // 事物隔离级别，开启新事务，这样会比较安全些。
        TransactionStatus status = transactionManager.getTransaction(def); // 获得事务状态
//        try {
////逻辑代码，可以写上你的逻辑处理代码
//            transactionManager.commit(status);
//        } catch (Exception e) {
//            transactionManager.rollback(status);
//        }


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
//            int yewuyuanCode = 5;
            int fuzongjianCode = 1;
//            int zongjianCode = 5;

            for (int i = 0; i < listob.size(); i++) {
                if (i < 3) {
                    continue;
                }
                List<Object> lo = listob.get(i);
                String yewuyuan = (String) lo.get(21);
                String fuzongjian = (String) lo.get(22);
                String zongjian = (String) lo.get(23);


                Employee zongjianemployee = employeeService.findEmpolyeeByName(zongjian);
                Employee fuzongjianemployee = employeeService.findEmpolyeeByName(fuzongjian);
//                Employee yewuyuanEmployee = employeeService.findEmpolyeeByName(yewuyuan);

                if (fuzongjianemployee == null && fuzongjian != "" && !fuzongjian.equals("无") && !fuzongjian.equals("")) {
                    fuzongjianemployee = new Employee();
                    fuzongjianemployee.setRole(4);
                    fuzongjianemployee.setCreateTime(DateUtil.date2Timestamp(new Date(System.currentTimeMillis())));
                    if (fuzongjianCode > 10) {
                        fuzongjianemployee.setCode("f0" + fuzongjianCode);
                    } else if (fuzongjianCode > 100) {
                        fuzongjianemployee.setCode("f0" + fuzongjianCode);
                    } else {
                        fuzongjianemployee.setCode("f00" + fuzongjianCode);
                    }

                    if (fuzongjianemployee != null) {
                        fuzongjianemployee.setLeader(zongjianemployee.getCode());
                    } else {
                        fuzongjianemployee.setLeader("1");
                    }
                    fuzongjianemployee.setName(fuzongjian);
                    fuzongjianemployee.setDuties("副总监");
                    employeeService.saveOrUpdate(fuzongjianemployee);
                    fuzongjianCode++;
                }

//                Accepted accepted = new Accepted();
//                accepted.setId(Integer.valueOf((String) lo.get(0)));
//                accepted.setCreateTime();
//                System.out.print(lo.get(0));
//                System.out.print(lo.get(1));
//                System.out.print(lo.get(2));
//                System.out.println(lo.get(3));


            }

            transactionManager.commit(status);
            return new ModelAndView("/other/import_success");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            transactionManager.rollback(status);
            e.printStackTrace();
        }

        return new ModelAndView("/other/import_success");
    }


    @RequestMapping(value = "/accepteds")
    public ModelAndView importAcceptedFromExcel(HttpServletRequest request, @RequestPart(value = "upload") MultipartFile file) {
        HibernateTransactionManager transactionManager = (HibernateTransactionManager) SpringContextUtil
                .getBean("txManager");
        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW); // 事物隔离级别，开启新事务，这样会比较安全些。
        TransactionStatus status = transactionManager.getTransaction(def); // 获得事务状态


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

            for (int i = 0; i < listob.size(); i++) {
                if (i < 3) {
                    continue;
                }
                List<Object> lo = listob.get(i);
                System.out.println(lo.size());
                String yewuyuan = (String) lo.get(21);
                String fuzongjian = (String) lo.get(22);
                String zongjian = (String) lo.get(23);
                Employee zongjianemployee = employeeService.findEmpolyeeByName(zongjian);
                Employee fuzongjianemployee = employeeService.findEmpolyeeByName(fuzongjian);
                Employee yewuyuanEmployee = employeeService.findEmpolyeeByName(yewuyuan);

                Accepted accepted = new Accepted();
                accepted.setCreateTime(DateUtil.date2Timestamp(new Date(System.currentTimeMillis())));
                accepted.setAcceptTime(DateUtil.date2Timestamp(DateUtil.string2Date2((String) lo.get(2))));
                accepted.setLetterNumber((String) lo.get(3));
                accepted.setAcceptedNumber((String) lo.get(4));
                accepted.setCustomerName((String) lo.get(5));
                accepted.setCustomerPhone((String) lo.get(6));
                accepted.setBusinessType(getType((String) lo.get(7)));
                accepted.setAgency((String) lo.get(8));
                accepted.setBusinessNature((String) lo.get(9));
                accepted.setWantMoney(getMoney((String) lo.get(10)));
                accepted.setLoanMoney(getMoney((String) lo.get(11)));
                accepted.setServiceAgreement(getAgreement((String) lo.get(12)));
                accepted.setServiceFee(getFee((String) lo.get(16)));
                accepted.setServiceFeeActual(getActual((String) lo.get(20)));
                accepted.setClerk(yewuyuanEmployee.getCode());
                accepted.setClerkName(yewuyuanEmployee.getName());
                if (fuzongjianemployee != null) {
                    accepted.setDeputyDirector(fuzongjianemployee.getCode());
                }
                if (zongjianemployee != null) {
                    accepted.setDirector(zongjianemployee.getCode());
                }

                accepted.setEndDate(DateUtil.date2Timestamp(DateUtil.string2Date2((String) lo.get(29))));
                if (lo.size() > 31) {
                    accepted.setBeizhu((String) lo.get(31));
                }
                if (lo.size() > 30) {
                    accepted.setState(getState((String) lo.get(30)));
                }
                accepted.setHouqi((String) lo.get(24));
                baseService.save(accepted);
            }

            transactionManager.commit(status);
            return new ModelAndView("/other/import_success");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (IndexOutOfBoundsException ie) {
            ie.printStackTrace();
        } catch (Exception e) {
            transactionManager.rollback(status);
            e.printStackTrace();
        }

        return new ModelAndView("/other/import_success");
    }


    private int getType(String loanType) {
        if (loanType == null || loanType.equals("")) {
            return -1;
        } else if (loanType.equals("信贷")) {
            return 0;
        } else if (loanType.equals("抵押")) {
            return 1;
        } else if (loanType.equals("质押")) {
            return 2;
        } else if (loanType.equals("权证")) {
            return 3;
        } else if (loanType.equals("车贷")) {
            return 4;
        } else if (loanType.equals("理财")) {
            return 5;
        } else if (loanType.equals("拼份")) {
            return 6;
        } else if (loanType.equals("其他")) {
            return 7;
        } else {
            return 10;
        }
    }

    private int getAgreement(String str) {
        if (str != null && str.equals("是")) {
            return 1;
        }
        return 0;
    }

    private Double getFee(String str) {
        double d = 0;
        if (str == null || str.equals("")) {
            return d;
        }
        if (str.contains("%")) {
            str = str.replace("%", "");
        }

        try {
            d = Double.valueOf(str);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return d;
    }

    private int getActual(String s) {
        if (s == null || s.equals("")) {
            return 0;
        }
        int i = Integer.valueOf(s);
        return i;
    }

    private int getState(String stat) {
        if (stat == null || stat.equals("")) {
            return 0;
        }
        if (stat.equals("被拒") || stat.equals("拒单")) {
            return 3;
        }

        if (stat.equals("办结")) {
            return 2;
        }

        if (stat.equals("撤单")) {
            return 4;
        }
        return 0;
    }

    private Double getMoney(String money) {
        double d = 0;
        if (money == null || money.equals("")) {
            return d;
        }
        try {
            d = Double.valueOf(money);
            return d;
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return d;

    }


}


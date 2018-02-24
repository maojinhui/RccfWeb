package com.rccf.controller.gzh;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.rccf.constants.ResponseConstants;
import com.rccf.constants.UrlConstants;
import com.rccf.model.Accepted;
import com.rccf.model.Employee;
import com.rccf.model.gzh.Accept;
import com.rccf.model.gzh.accpet.AcceptPeroduceSimple;
import com.rccf.model.gzh.accpet.AcceptedTemp;
import com.rccf.model.gzh.accpet.AcceptedTempLog;
import com.rccf.service.BaseService;
import com.rccf.service.EmployeeService;
import com.rccf.util.BackUtil;
import com.rccf.util.DateUtil;
import com.rccf.util.ResponseUtil;
import com.rccf.util.Strings;
import com.rccf.util.accept.AcceptUtil;
import com.rccf.util.file.BackCustomerImg;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping(value = "/gzh/accept", produces = UrlConstants.PRODUCES)
public class GZHAcceptController {

    @Autowired
    BaseService baseService;


    @Autowired
    EmployeeService employeeService;


    /**
     * 销售填报受理单
     *
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/generate/sale")
    public String submitAccepted(HttpServletRequest request) {
        Employee login = BackUtil.getLoginEmployee(request, employeeService);
        if (login == null) {
            return ResponseUtil.fail(0, ResponseConstants.MSG_EMPLOYEE_IS_NULL);
        }

        String oldData;
        String newData;

        String code = null;
        String loginName = login.getName();
        String deputy = null;
        String director = null;
        String employeeName = login.getName();
        String deputyName = null;
        String directorName = null;


        int role = login.getRole();
        String department = login.getDepartment();
        if (role == 4) {
            code = login.getCode();
            deputy = login.getDuptyDirector();
            director = login.getDirector();

        } else {
            return ResponseUtil.fail(0, "目前仅支持业务员提交受理单");
        }

        String accepttemp_id = request.getParameter("accept_id");
        String customer_id = request.getParameter("customer_id");
        String customer_name = request.getParameter("customer_name");
        String customer_phone = request.getParameter("customer_phone");
        String customer_idcard = request.getParameter("customer_idcard");
        String loan_type = request.getParameter("loan_type");
        String want_money = request.getParameter("loan_amount");
        String service_propertion = request.getParameter("service_propertion");
        String customer_files = request.getParameter("customer_files");
        String houqi = request.getParameter("houqi");

        AcceptedTemp acceptedTemp = null;
        if (!Strings.isNullOrEmpty(accepttemp_id)) {
            acceptedTemp = (AcceptedTemp) baseService.get(AcceptedTemp.class, accepttemp_id);
        }
        if (acceptedTemp == null) {
            acceptedTemp = new AcceptedTemp();
            acceptedTemp.setCreateTime(DateUtil.date2Timestamp(new Date()));
            acceptedTemp.setEmployee(code);
            acceptedTemp.setEmployeeName(loginName);
            if (!Strings.isNullOrEmpty(deputy)) {
                Employee deputyEmployee = employeeService.findEmpolyeeByCode(deputy);
                acceptedTemp.setDeputy(deputy);
                acceptedTemp.setDeputyName(deputyEmployee.getName());
            }
            if (!Strings.isNullOrEmpty(director)) {
                Employee directorEmployee = employeeService.findEmpolyeeByCode(director);
                acceptedTemp.setDirector(director);
                acceptedTemp.setDirectorName(directorEmployee.getName());
            }
            acceptedTemp.setDirector(director);

            oldData = null;
        } else {
            oldData = acceptedTemp.toString();
        }
        acceptedTemp.setState(1);
        acceptedTemp.setContent(null);
        if (!Strings.isNullOrEmpty(customer_id)) {
            acceptedTemp.setCustomerId(customer_id);
        } else {
            acceptedTemp.setCustomerId(null);
        }

        if (!Strings.isNullOrEmpty(customer_name)) {
            acceptedTemp.setCustomerName(customer_name);
        } else {
            return ResponseUtil.fail(0,"请填写客户真实姓名");
        }

        if (!Strings.isNullOrEmpty(customer_phone)) {
            if(Strings.isMobileNO(customer_phone)){
                acceptedTemp.setCustomerPhone(customer_phone);
            }else {
                return ResponseUtil.fail(0,"请填写正确的电话号码");
            }
        } else {
            return ResponseUtil.fail(0,"请填写客户联系电话");
        }

        if (!Strings.isNullOrEmpty(customer_idcard)) {
            acceptedTemp.setCustomerIdcard(customer_idcard);
        } else {
//            acceptedTemp.setCustomerIdcard(null);
            return ResponseUtil.fail(0,"请填写客户身份证号");
        }


        if (!Strings.isNullOrEmpty(customer_files)) {
            JSONArray fileArray = new JSONArray();
//            acceptedTemp.setCustomerFile(customer_files);
            JSONArray array = JSON.parseArray(customer_files);
            if (array.size() > 0) {
                for (int i = 0; i < array.size(); i++) {
                    String url = array.getString(i);
                    String newUrl = BackCustomerImg.copyFile(url);
                    fileArray.add(newUrl);
                }
                acceptedTemp.setCustomerFile(JSON.toJSONString(fileArray));
            }
        } else {
//            acceptedTemp.setCustomerFile(null);
            return ResponseUtil.fail(0,"请选择客户的文件");
        }

        if (!Strings.isNullOrEmpty(loan_type)) {
            if(loan_type.equals("-1")){
                return ResponseUtil.fail(0,"请填写客户贷款类型");
            }
            acceptedTemp.setCustomerLoanType(Integer.valueOf(loan_type));


        } else {
            acceptedTemp.setCustomerLoanType(null);
        }

        if (!Strings.isNullOrEmpty(want_money)) {
            acceptedTemp.setCustomerWantmoney(Double.valueOf(want_money));
        } else {
//            acceptedTemp.setCustomerWantmoney(null);
            return ResponseUtil.fail(0,"请填写客户预贷金额");
        }

        if (!Strings.isNullOrEmpty(service_propertion)) {
            acceptedTemp.setServiceProportion(Double.valueOf(service_propertion));
        } else {
//            acceptedTemp.setServiceProportion(null);
            return ResponseUtil.fail(0,"请填写服务费比例");
        }

        if (!Strings.isNullOrEmpty(houqi)) {
            acceptedTemp.setHouqi(Integer.valueOf(houqi));
            Employee houqiEmployee = employeeService.findEmpolyeeById(Integer.valueOf(houqi));
            acceptedTemp.setHouqiName(houqiEmployee.getName());
        }else{
            return ResponseUtil.fail(0,"请选择对应的后期人员");
        }


        boolean save = baseService.save(acceptedTemp);

        if (save) {
            String acceptTempId = acceptedTemp.getId();
            AcceptedTempLog tempLog = new AcceptedTempLog();
            tempLog.setAcceptedTempId(acceptTempId);
            tempLog.setContent(null);
            tempLog.setState(1);
            tempLog.setCreatePerson(login.getId());
            tempLog.setCreateTime(DateUtil.date2Timestamp(new Date()));
            tempLog.setOldData(oldData);
            tempLog.setNewData(acceptedTemp.toString());
            boolean saveLog = baseService.save(tempLog);
            if (saveLog) {
                return ResponseUtil.success();
            } else {
                return ResponseUtil.fail(0, "受理日志保存失败");
            }
        } else {
            return ResponseUtil.fail(0, "受理单提交失败");
        }
    }


    /**
     * 市场部提交缺少客户资料
     *
     * @param request
     * @return
     */

    @ResponseBody
    @RequestMapping(value = "/submit/state2")
    public String submitState2(HttpServletRequest request) {
        AcceptedTempLog log = new AcceptedTempLog();
        Employee employee = BackUtil.getLoginEmployee(request, employeeService);
        String accept_id = request.getParameter("accept_id");
        if (Strings.isNullOrEmpty(accept_id)) {
            return ResponseUtil.fail(0, "参数错误");
        }
        AcceptedTemp acceptedTemp = (AcceptedTemp) baseService.get(AcceptedTemp.class, accept_id);
        if (acceptedTemp == null) {
            return ResponseUtil.fail(0, "受理单未找到");
        }


        if (acceptedTemp.getHouqi() != employee.getId()) {
            return ResponseUtil.fail(0, "您还没有操作本受理单权限");
        }
        String content = request.getParameter("content");
        if (Strings.isNullOrEmpty(content)) {
            return ResponseUtil.fail(0, "请填写退回原因");
        }
        log.setAcceptedTempId(acceptedTemp.getId());
        log.setCreateTime(DateUtil.date2Timestamp(new Date(System.currentTimeMillis())));
        log.setCreatePerson(employee.getId());
        log.setState(2);
        log.setContent(content);
        log.setOldData(acceptedTemp.toString());



        acceptedTemp.setState(2);
        acceptedTemp.setContent(content);
        boolean save = baseService.save(acceptedTemp);
        if (save) {
            log.setNewData(acceptedTemp.toString());
            boolean saveLog = baseService.save(log);
            if(!saveLog){
                return ResponseUtil.fail(0,"保存日志失败，请重新提交");
            }

            return ResponseUtil.success();
        }

        return ResponseUtil.fail(0, "提交失败,请联系管理员");
    }

    @ResponseBody
    @RequestMapping(value = "/submit/state3")
    public String submitState3(HttpServletRequest request) {
        AcceptedTempLog log = new AcceptedTempLog();
        Employee employee = BackUtil.getLoginEmployee(request, employeeService);
        if (employee == null && employee.getState() == null && employee.getState() != 1) {
            return ResponseUtil.fail(0, "登录状态错误，请重新登录");
        }
        String channelFee = request.getParameter("qudaoFee");
        String sanfangFee = request.getParameter("sanfangFee");
        String cailiaoFee = request.getParameter("cailiaoFee");
        String produces = request.getParameter("products");
        String accept_id = request.getParameter("accept_id");
        if (Strings.isNullOrEmpty(accept_id)) {
            return ResponseUtil.fail(0, "没有获取到受理单ID");
        }

        AcceptedTemp acceptedTemp = (AcceptedTemp) baseService.get(AcceptedTemp.class, accept_id);
        if (acceptedTemp == null) {
            return ResponseUtil.fail(0, "没有查找到对应的受理单");
        }
        log.setAcceptedTempId(acceptedTemp.getId());
        log.setCreateTime(DateUtil.date2Timestamp(new Date(System.currentTimeMillis())));
        log.setCreatePerson(employee.getId());
        log.setState(3);
        log.setContent("");
        log.setOldData(acceptedTemp.toString());


        acceptedTemp.setChannelFee(channelFee);
        acceptedTemp.setSanfangFee(sanfangFee);
        acceptedTemp.setMaterialFee(cailiaoFee);
        if (Strings.isNullOrEmpty(produces)) {
            return ResponseUtil.fail(0, "请选择产品后提交");
        }
        JSONArray array = JSON.parseArray(produces);
        if (array == null || array.size() < 1) {
            return ResponseUtil.fail(0, "请选择产品后提交");
        }
        AcceptPeroduceSimple acceptPeroduceSimple = null;
        JSONArray produceArray = new JSONArray();
        for (int i = 0; i < array.size(); i++) {
            JSONObject object = array.getJSONObject(i);
            String product_id = object.getString("productId");
            String product_type = object.getString("productType");

            String sql = "SELECT  `id`, `agency_name` ,name ,`code`,`create_person`,0 as type , 0 as temprow , 0 as rowNo from ";
            if(product_type.equals("0")){//信贷
                sql = sql+"a_produce_credit where id = "+product_id;
            }else if(product_type.equals("1")){//抵押
                sql = sql+"a_produce_diya  where id = "+product_id;
            }else if(product_type.equals("2")){//质押
                sql = sql +"a_produce_zhiya where id = "+product_id;
            }
            acceptPeroduceSimple = (AcceptPeroduceSimple) baseService.queryObjectBySqlFormatClass(AcceptPeroduceSimple.class,sql);
            if(acceptPeroduceSimple !=null){
                JSONObject object1 = JSON.parseObject(JSON.toJSONString(acceptPeroduceSimple));
                produceArray.add(object1);
            }
        }
        if(produceArray.size()<1){
            return ResponseUtil.fail(0,"没有找到相关产品，请核对信息");
        }

        String productinfo = JSON.toJSONString(produceArray);
        acceptedTemp.setProduceInfo(productinfo);
        acceptedTemp.setState(3);

        boolean save = baseService.save(acceptedTemp);
        if(save){
            log.setNewData(acceptedTemp.toString());
            boolean saveLog = baseService.save(log);
            if(!saveLog){
                return ResponseUtil.fail(0,"保存日志失败，请重新提交");
            }
            return ResponseUtil.success();
        }
        return ResponseUtil.fail(0, "提交失败");
    }


    @ResponseBody
    @RequestMapping(value = "/submit/state4")
    public String submitState4(HttpServletRequest request) {
        AcceptedTempLog log = new AcceptedTempLog();
        Employee employee = BackUtil.getLoginEmployee(request, employeeService);
        if (employee == null && employee.getState() == null && employee.getState() != 1) {
            return ResponseUtil.fail(0, "登录状态错误，请重新登录");
        }
        String content = request.getParameter("content");
        if (Strings.isNullOrEmpty(content)) {
            return ResponseUtil.fail(0, "请填写退回原因");
        }

        String accept_id = request.getParameter("accept_id");
        if (Strings.isNullOrEmpty(accept_id)) {
            return ResponseUtil.fail(0, "没有获取到受理单ID");
        }

        AcceptedTemp acceptedTemp = (AcceptedTemp) baseService.get(AcceptedTemp.class, accept_id);
        if (acceptedTemp == null) {
            return ResponseUtil.fail(0, "没有查找到对应的受理单");
        }
        log.setAcceptedTempId(acceptedTemp.getId());
        log.setCreateTime(DateUtil.date2Timestamp(new Date(System.currentTimeMillis())));
        log.setCreatePerson(employee.getId());
        log.setState(4);
        log.setContent(content);
        log.setOldData(acceptedTemp.toString());

       acceptedTemp.setContent(content);
       acceptedTemp.setState(4);
        boolean save = baseService.save(acceptedTemp);
        if(save){
            log.setNewData(acceptedTemp.toString());
            boolean saveLog = baseService.save(log);
            if(!saveLog){
                return ResponseUtil.fail(0,"保存日志失败，请重新提交");
            }
            return ResponseUtil.success();
        }
        return ResponseUtil.fail(0, "提交失败");
    }


    @ResponseBody
    @RequestMapping(value = "/submit/state5")
    public String submitState5(HttpServletRequest request){
        AcceptedTempLog log = new AcceptedTempLog();
        Employee employee = BackUtil.getLoginEmployee(request,employeeService);
        String  accept_id= request.getParameter("accept_id");
        if (employee == null && employee.getState() == null && employee.getState() != 1) {
            return ResponseUtil.fail(0, "登录状态错误，请重新登录");
        }
        if (Strings.isNullOrEmpty(accept_id)) {
            return ResponseUtil.fail(0, "没有获取到受理单ID");
        }

        String accept_number = request.getParameter("accept_number");
        if(!Strings.isNullOrEmpty(accept_number)){
            return ResponseUtil.fail(0,"此客户已生成受理单，请不要重复提交");
        }


        AcceptedTemp acceptedTemp = (AcceptedTemp) baseService.get(AcceptedTemp.class, accept_id);
        if (acceptedTemp == null) {
            return ResponseUtil.fail(0, "没有查找到对应的受理单");
        }

        log.setAcceptedTempId(acceptedTemp.getId());
        log.setCreateTime(DateUtil.date2Timestamp(new Date(System.currentTimeMillis())));
        log.setCreatePerson(employee.getId());
        log.setState(5);
        log.setContent("");
        log.setOldData(acceptedTemp.toString());

        String code = request.getParameter("code");
        if(Strings.isNullOrEmpty(code)){
            return ResponseUtil.fail(0,"请填写产品编号");
        }
        String accept_time = request.getParameter("accept_time");

        acceptedTemp.setLetterNumber(code);
        acceptedTemp.setAcceptTime(DateUtil.date2Timestamp(DateUtil.string2Date(accept_time)));


        Accepted accepted = new Accepted();
        accepted.setAcceptedNumber(getLastNumber());
        accepted.setCustomerName(acceptedTemp.getCustomerName());
        accepted.setCustomerPhone(acceptedTemp.getCustomerPhone());
        accepted.setAgency(Strings.getAcceptedAgency(acceptedTemp.getProduceInfo()));
        accepted.setClerk(acceptedTemp.getEmployee());
        accepted.setDeputyDirector(acceptedTemp.getDeputy());
        accepted.setDirector(acceptedTemp.getDirector());
        accepted.setCreateTime(DateUtil.date2Timestamp(new Date(System.currentTimeMillis())));
        accepted.setLetterNumber(acceptedTemp.getLetterNumber());
        accepted.setClerkName(acceptedTemp.getEmployeeName());
        accepted.setAcceptTime(acceptedTemp.getAcceptTime());
        accepted.setBusinessType(acceptedTemp.getCustomerLoanType());
        accepted.setWantMoney(acceptedTemp.getCustomerWantmoney());
        accepted.setServiceFee(acceptedTemp.getServiceProportion());
        accepted.setState(1);
        accepted.setHouqi(acceptedTemp.getHouqiName());
        accepted.setCustomerIdcard(acceptedTemp.getCustomerIdcard());
        boolean save = baseService.save(accepted);
        if(save){
            acceptedTemp.setAcceptId(accepted.getId());
            acceptedTemp.setAcceptNumber(accepted.getAcceptedNumber());
            acceptedTemp.setState(5);
            boolean saveAccepttemp = baseService.save(acceptedTemp);
            if(!saveAccepttemp){
                return ResponseUtil.fail(0,"受理单更新失败");
            }
            log.setNewData(acceptedTemp.toString());
            boolean saveLog = baseService.save(log);
            if(saveLog){
                return ResponseUtil.success();
            }else{
                return ResponseUtil.fail(0,"保存日志失败，请重新提交");
            }
        }
        return ResponseUtil.fail(0,"保存失败");
    }




    /**
     * 销售部受理信息页面
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/list/sales")
    public ModelAndView saleAcceptInfolist(HttpServletRequest request) {
        Employee employee = BackUtil.getLoginEmployee(request, employeeService);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/gzh/sales/accept_info_list");
        AcceptUtil.addSalesNotificationCount(baseService, employee, modelAndView);
        return modelAndView;
    }


    /**
     * 市场部受理信息页面
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/list/shichang")
    public ModelAndView saleAcceptInfoShichang(HttpServletRequest request) {
        Employee employee = BackUtil.getLoginEmployee(request, employeeService);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/gzh/shichangbu/accept_info_list");
        AcceptUtil.addSalesNotificationCount(baseService, employee, modelAndView);
        return modelAndView;
    }


    @ResponseBody
    @RequestMapping(value = "/info/list/sale")
    public String getSaleAcceptInfo(HttpServletRequest request) {
        Employee login = BackUtil.getLoginEmployee(request, employeeService);
        String code = login.getCode();
        String department = login.getDepartment();
        int role = login.getRole();

        if (role == 4) { // 销售
            DetachedCriteria detachedCriteria = DetachedCriteria.forClass(AcceptedTemp.class);
            detachedCriteria.add(Restrictions.eq("employee", code));
            detachedCriteria.addOrder(Order.desc("createTime"));
            List<AcceptedTemp> acceptedTemps = baseService.getList(detachedCriteria);
            JSONArray array = JSON.parseArray(JSON.toJSONString(acceptedTemps));
            return ResponseUtil.success_front(array);
        }

        return ResponseUtil.fail();
    }


    @ResponseBody
    @RequestMapping(value = "/info/list/shichang")
    public String getSaleAcceptInfoShichang(HttpServletRequest request) {
        Employee login = BackUtil.getLoginEmployee(request, employeeService);
        int eId = login.getId();
        String code = login.getCode();
        String department = login.getDepartment();
        int role = login.getRole();

        if (role == 4) { // 后期专员
            DetachedCriteria detachedCriteria = DetachedCriteria.forClass(AcceptedTemp.class);
            detachedCriteria.add(Restrictions.eq("houqi", eId));
            detachedCriteria.addOrder(Order.desc("createTime"));
            List<AcceptedTemp> acceptedTemps = baseService.getList(detachedCriteria);
            JSONArray array = JSON.parseArray(JSON.toJSONString(acceptedTemps));
            return ResponseUtil.success_front(array);
        }

        return ResponseUtil.fail();
    }



    @ResponseBody
    @RequestMapping(value = "/info/list/acceptcenter")
    public String getSaleAcceptInfoAcceptcenter(HttpServletRequest request) {
        Employee login = BackUtil.getLoginEmployee(request, employeeService);
        int eId = login.getId();
        String code = login.getCode();
        String department = login.getDepartment();
        int role = login.getRole();
        if (role == 5) { // 受理中心
            DetachedCriteria detachedCriteria = DetachedCriteria.forClass(AcceptedTemp.class);
            detachedCriteria.addOrder(Order.desc("createTime"));
//            detachedCriteria.add(Restrictions.eq("state" , 3));
            detachedCriteria.add(Restrictions.or(Restrictions.eq("state" , 3),
                    Restrictions.eq("state" , 5)));
            List<AcceptedTemp> acceptedTemps = baseService.getList(detachedCriteria);
            JSONArray array = JSON.parseArray(JSON.toJSONString(acceptedTemps));
            return ResponseUtil.success_front(array);
        }

        return ResponseUtil.fail();
    }



    @RequestMapping(value = "/info")
    public ModelAndView acceptInfo(HttpServletRequest request) {
        Employee loginEmployee = BackUtil.getLoginEmployee(request, employeeService);
        if (loginEmployee == null) {
            return ResponseUtil.pageFail("登录信息失效，请重新登录");
        }
        String accept_id = request.getParameter("accept_id");
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/gzh/accept/info");
        modelAndView.addObject("employee", loginEmployee);//添加人员信息
        AcceptedTemp acceptedTemp = (AcceptedTemp) baseService.get(AcceptedTemp.class, accept_id);
        modelAndView.addObject("accept", acceptedTemp); //添加受理单信息


        return modelAndView;
    }




    private String getLastNumber() {
        DateFormat format = new SimpleDateFormat("yyyyMMdd");
        String preFix = format.format(new Date(System.currentTimeMillis())) + "-";
        String sql = " SELECT a.`accepted_number`   from `accepted`  a ORDER BY  id DESC limit 1";
        List list = baseService.queryBySql(sql);
        String lastString = (String) list.get(0);
        lastString = lastString.substring(lastString.indexOf("-") + 1);
        int number_now = Integer.valueOf(lastString) + 1;
        return preFix + number_now;
    }

}

package com.rccf.controller.gzh;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.rccf.constants.UrlConstants;
import com.rccf.model.Employee;
import com.rccf.model.ILoanType;
import com.rccf.model.customer.CustomerSubmit;
import com.rccf.model.customer.RCustomerLoanProgram;
import com.rccf.model.customer.RCustomerSubmitLog;
import com.rccf.model.produce.ProduceRepayment;
import com.rccf.model.temp.ProduceTem;
import com.rccf.service.BaseService;
import com.rccf.service.EmployeeService;
import com.rccf.util.BackUtil;
import com.rccf.util.DateUtil;
import com.rccf.util.ResponseUtil;
import com.rccf.util.Strings;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping(value = "/gzh/shichang" , produces = UrlConstants.PRODUCES)
public class GZHShichangController {

    @Autowired
    EmployeeService employeeService;

    @Autowired
    BaseService baseService;

    @RequestMapping(value = "/index")
    public  ModelAndView index(HttpServletRequest request){
        Employee employee = BackUtil.getLoginEmployee(request,employeeService);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/gzh/shichangbu/index");
        int employeeID= employee.getId();

        String sql = "SELECT count(*) from r_customer_submit_log WHERE  state=1 and submit_houqi = 154";
        DetachedCriteria criteria = DetachedCriteria.forClass(RCustomerSubmitLog.class);
        criteria.add(Restrictions.eq("state",1));
        criteria.add(Restrictions.eq("submitHouqi",employeeID));
//        criteria.setProjection(Projections.rowCount());
        int notificationCount = baseService.getCount(criteria);
        modelAndView.addObject("notificationCount",notificationCount);
        String sql_logs = "\n" +
                "  SELECT id,customer_id,customer_name,submit_saleman,state, \n" +
                "  (SELECT name from employee WHERE id = submit_saleman ) as submit_saleman_name,\n" +
                "  (SELECT name from employee WHERE id = submit_houqi ) as houqi_name,\n" +
                "  DATE_FORMAT(submit_time,'%m-%d') as month_day,\n" +
                "  DATE_FORMAT(submit_time,'%H:%i') as hourminute\n" +
                "  FROM r_customer_submit_log log " +
                "  where state=1 and submit_houqi = " +employeeID+" \n"+
                "   ;\n";
        List<CustomerSubmit> submits =    baseService.queryBySqlFormatClass(CustomerSubmit.class,sql_logs);
        modelAndView.addObject("submitlogs",submits);

        String sql_already = "SELECT program_id  as  id ,customer_id,\n" +
                "  (SELECT name from r_customer WHERE id=customer_id) as customer_name,\n" +
                "  submit_person as submit_saleman , state,\n" +
                "  (SELECT name from employee WHERE id = submit_person ) as submit_saleman_name,\n" +
                "  (SELECT name from employee WHERE id = create_person ) as houqi_name,\n" +
                "  DATE_FORMAT(create_time,'%m-%d') as month_day,\n" +
                "  DATE_FORMAT(create_time,'%H:%i') as hourminute\n" +
                "FROM r_customer_loan_program program  where  create_person = " + employeeID +
                " ;";
        List<CustomerSubmit> programs =    baseService.queryBySqlFormatClass(CustomerSubmit.class,sql_already);
        modelAndView.addObject("programs",programs);



        return modelAndView;
    }

    @RequestMapping(value = "/customer/info")
    public ModelAndView customerIndex(HttpServletRequest request){
        Employee employee = BackUtil.getLoginEmployee(request,employeeService);
        String log_id = request.getParameter("log_id");

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/gzh/shichangbu/market_customer");




        int employeeID= employee.getId();
        if(Strings.isNullOrEmpty(log_id)){
            return ResponseUtil.pageFail("参数错误");
        }
        RCustomerSubmitLog log = (RCustomerSubmitLog) baseService.get(RCustomerSubmitLog.class,Integer.valueOf(log_id));
        modelAndView.addObject("log",log);
        modelAndView.addObject("customer_id",log.getCustomerId());
        modelAndView.addObject("log_id",log_id);
        DetachedCriteria criteria = DetachedCriteria.forClass(ILoanType.class);
//        criteria.createAlias("id","id");
//        criteria.createAlias("name","name");
        List<ILoanType> iLoanTypes = baseService.getList(criteria);
        JSONArray jsonArray = JSON.parseArray(JSON.toJSONString(iLoanTypes));
        JSONObject object = new JSONObject();
        for (int i = 0 ; i< jsonArray.size();i++){
            JSONObject obj = jsonArray.getJSONObject(i);
            String id = obj.getString("id");
            String name = obj.getString("name");
            object.put(id,name);
        }
        modelAndView.addObject("loanTypes",object);

        DetachedCriteria repaymentCriteria = DetachedCriteria.forClass(ProduceRepayment.class);
//        repaymentCriteria.createAlias("id","id");
//        repaymentCriteria.createAlias("name","name");
        List<ProduceRepayment> produceRepayments = baseService.getList(repaymentCriteria);
        JSONArray produceRepaymentsJsonarray = JSON.parseArray(JSON.toJSONString(produceRepayments));
        JSONObject repaymentObject = new JSONObject();
        for (int i = 0 ; i< produceRepaymentsJsonarray.size();i++){
            JSONObject obj = produceRepaymentsJsonarray.getJSONObject(i);
            String id = obj.getString("id");
            String name = obj.getString("name");
            repaymentObject.put(id,name);
        }
        modelAndView.addObject("repayments",repaymentObject);




        return modelAndView;
    }


    @RequestMapping(value = "/page/program")
    public ModelAndView programPage(HttpServletRequest request ){
        String customer_id = request.getParameter("customer_id");
        String log_id = request.getParameter("log_id");
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/gzh/shichangbu/market_loan_program");
        modelAndView.addObject("customer_id",customer_id);
        modelAndView.addObject("log_id",log_id);


        Employee employee = BackUtil.getLoginEmployee(request, employeeService);
        //登录用户
        String depart = employee.getDepartment();
        int role = employee.getRole();
        boolean showAll = false;
        if (depart.contains("系统")) {
            showAll = true;
        } else if (depart.contains("市场部")) {
            if (role <= 2) {
                showAll = true;
            } else {
                showAll = false;
            }
        } else {
            return ResponseUtil.pageFail("没有找到产品");
        }
        String eInfo = "";
        if (!showAll) {
            eInfo = "where p.create_person = " + employee.getId();
        }
        String sql = "SELECT *\n" +
                "FROM (SELECT `id`, `name`, `code`, agency_id,\n" +
                "         agency_name,\n" +
                "         1 AS type,`state`,create_time,log,\n" +
                "(SELECT audit_opinion from a_produce_audit_log WHERE type=1 and produce_id=p.id ORDER BY audit_time DESC  LIMIT  1) as reason" +
                "       FROM `a_produce_diya` as p\n" + eInfo +
                "       UNION ALL\n" +
                "       SELECT `id`, `name`, `code`, agency_id,\n" +
                "          agency_name,\n" +
                "         2 AS type,`state`,create_time,log,\n" +
                "       (SELECT audit_opinion from a_produce_audit_log WHERE type=2 and produce_id=p.id ORDER BY audit_time DESC  LIMIT  1) as reason" +
                "       FROM `a_produce_zhiya` as p\n" + eInfo +
                "       UNION ALL\n" +
                "      SELECT `id`, `name`, `code`, agency_id,\n" +
                "      agency_name,\n" +
                "       0 AS type,`state`,create_time,log,\n" +
                "       (SELECT audit_opinion from a_produce_audit_log WHERE type=0 and produce_id=p.id ORDER BY audit_time DESC  LIMIT  1) as reason      " +
                "       FROM `a_produce_credit` as p\n" + eInfo +
                "     ) AS data  where data.state = 1 \n" +
                "ORDER BY data.create_time DESC ";

        List<ProduceTem> produeList = baseService.queryBySqlFormatClass(ProduceTem.class, sql);
//        String str = JSON.toJSONString(list, SerializerFeature.DisableCircularReferenceDetect);
//        JSONArray array = JSON.parseArray(str);
        modelAndView.addObject("produeList" , produeList);

        return modelAndView;
    }

    @ResponseBody
    @RequestMapping(value = "/program/submit")
    public String submitProgram(HttpServletRequest request){
        Employee employee = BackUtil.getLoginEmployee(request,employeeService);
        String customer_id = request.getParameter("customer_id");
        String products = request.getParameter("products");
        String log_id = request.getParameter("log_id");
        String no_product = request.getParameter("no_product");

        if(Strings.isNullOrEmpty(log_id)){
            ResponseUtil.fail(0,"参数错误");
        }
        int logId = Integer.valueOf(log_id);
        RCustomerSubmitLog log = (RCustomerSubmitLog) baseService.get(RCustomerSubmitLog.class,logId);

        if(!Strings.isNullOrEmpty(no_product)){
            log.setState(3);
            boolean save = baseService.save(log);
            if(save){
                return ResponseUtil.success();
            }else{
                return ResponseUtil.fail(0,"提交出错");
            }
        }

        if(Strings.isNullOrEmpty(products)){
            return ResponseUtil.fail(0,"请添加产品信息");
        }

        JSONArray array = JSON.parseArray(products);
        if(array.size()<1){
            return ResponseUtil.fail(0,"请添加产品信息");
        }
        RCustomerLoanProgram program = new RCustomerLoanProgram();
        program.setCreateTime(DateUtil.date2Timestamp(new Date()));
        program.setCreatePerson(employee.getId());
        program.setCustomerId(customer_id);
        program.setProducts(products);
        program.setState(1);
        program.setSubmitPerson(log.getSubmitSaleman());
        boolean save = baseService.save(program);
        if(save){
            log.setState(2);
            baseService.save(log);
            return ResponseUtil.success();
        }else{
            return ResponseUtil.fail(0,"提交信息失败");
        }
    }


    @RequestMapping(value = "/page/data")
    public ModelAndView dataPage(HttpServletRequest request){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/gzh/shichangbu/data");
        return modelAndView;
    }

    @RequestMapping(value = "/info/all")
    public ModelAndView  showAllInfo(HttpServletRequest request){
        Employee employee = BackUtil.getLoginEmployee(request,employeeService);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/gzh/shichangbu/index_allinfo");
        int employeeID= employee.getId();

//        String sql = "SELECT count(*) from r_customer_submit_log WHERE  state=1 and submit_houqi = 154";
        DetachedCriteria criteria = DetachedCriteria.forClass(RCustomerSubmitLog.class);
        criteria.add(Restrictions.eq("state",1));
        criteria.add(Restrictions.eq("submitHouqi",employeeID));
//        criteria.setProjection(Projections.rowCount());
        int notificationCount = baseService.getCount(criteria);
        modelAndView.addObject("notificationCount",notificationCount);
        String sql_logs = "\n" +
                "  SELECT id,customer_id,customer_name,submit_saleman,state, \n" +
                "  (SELECT name from employee WHERE id = submit_saleman ) as submit_saleman_name,\n" +
                "  (SELECT name from employee WHERE id = submit_houqi ) as houqi_name,\n" +
                "  DATE_FORMAT(submit_time,'%m-%d') as month_day,\n" +
                "  DATE_FORMAT(submit_time,'%H:%i') as hourminute\n" +
                "  FROM r_customer_submit_log log " +
                "  where state=1 and submit_houqi = " +employeeID+" \n"+
                "   ;\n";
        List<CustomerSubmit> submits =    baseService.queryBySqlFormatClass(CustomerSubmit.class,sql_logs);
        modelAndView.addObject("submitlogs",submits);

        String sql_already = "SELECT program_id  as  id ,customer_id,\n" +
                "  (SELECT name from r_customer WHERE id=customer_id) as customer_name,\n" +
                "  submit_person as submit_saleman , state,\n" +
                "  (SELECT name from employee WHERE id = submit_person ) as submit_saleman_name,\n" +
                "  (SELECT name from employee WHERE id = create_person ) as houqi_name,\n" +
                "  DATE_FORMAT(create_time,'%m-%d') as month_day,\n" +
                "  DATE_FORMAT(create_time,'%H:%i') as hourminute\n" +
                "FROM r_customer_loan_program program  where  create_person = " + employeeID +
                " ;";
        List<CustomerSubmit> programs =    baseService.queryBySqlFormatClass(CustomerSubmit.class,sql_already);
        modelAndView.addObject("programs",programs);
        return modelAndView;
    }



}

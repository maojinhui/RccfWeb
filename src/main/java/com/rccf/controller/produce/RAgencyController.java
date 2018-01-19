package com.rccf.controller.produce;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.rccf.constants.UrlConstants;
import com.rccf.constants.build.DebugManager;
import com.rccf.model.Employee;
import com.rccf.model.produce.RAgency;
import com.rccf.service.BaseService;
import com.rccf.service.EmployeeService;
import com.rccf.util.BackUtil;
import com.rccf.util.DateUtil;
import com.rccf.util.ResponseUtil;
import com.rccf.util.Strings;
import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping(value = "/back/ragency", produces = UrlConstants.PRODUCES)
public class RAgencyController {

    private static final Logger logger = Logger.getLogger(RAgencyController.class);

    @Autowired
    BaseService baseService;

    @Autowired
    EmployeeService employeeService;


    @RequestMapping(value = "/entry")
    public ModelAndView entryAgency(HttpServletRequest request) {
        Employee employee = BackUtil.getLoginEmployee(request, employeeService);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/product/agency_entry");
        return modelAndView;
    }


    @ResponseBody
    @RequestMapping(value = "/submit")
    public String submitAgency(@RequestParam(value = "file", required = true) MultipartFile file, HttpServletRequest request) {

        String agency_id = request.getParameter("agency_id");
        RAgency rAgency = null;
        if (!Strings.isNullOrEmpty(agency_id)) {
            rAgency = (RAgency) baseService.get(RAgency.class, agency_id);
        }


        Employee employee = BackUtil.getLoginEmployee(request, employeeService);
        if (employee == null) {
            return ResponseUtil.fail(0, "登录失效，请重新登录");
        }


        if (rAgency == null) {
            rAgency = new RAgency();
            rAgency.setCreateTime(DateUtil.date2Timestamp(new Date()));
            rAgency.setCreatePerson(employee.getId());
            rAgency.setState(1);
        }

        String srcImg = "";
        logger.info("开始上传文件");
        String server_path;
        String host_address;
        if (DebugManager.DEBUG) {
            server_path = DebugManager.LOCAL_IMAGE_PATH;
            host_address = DebugManager.LOCAL_HOST_ADDRESS;
        } else {
            server_path = DebugManager.SERVER_PATH_PRODUCT_FILE;
            host_address = DebugManager.SERVER_ADDRESS_PRODUCT_FILE;
        }
        String fileName = file.getOriginalFilename();
        String extention = "";
        if (fileName.contains(".")) {
            extention = fileName.substring(fileName.lastIndexOf("."));
        }

        String path_second = "rccf_" + System.currentTimeMillis() + File.separator;
        server_path = server_path + path_second;
        host_address = host_address + path_second;


        String path = server_path + fileName;
        logger.info("path:" + path);

        File f = new File(path);
        File parentFile = f.getParentFile();
        if (!parentFile.exists())
            parentFile.mkdirs();
        if (!file.isEmpty()) {//文件是否为空
            try {
                int state = IOUtils.copy(file.getInputStream(), new FileOutputStream(new File(path)));
                if (state > 0) {
                    srcImg = host_address + fileName;
//                    return ResponseUtil.success(srcImg);
                }
            } catch (IOException e) {
                e.printStackTrace();
                return ResponseUtil.fail(0, "文件上传失败");
            }
        } else {
            return ResponseUtil.fail(0, "上传的文件为空");
        }
        rAgency.setAnnex(path);
        rAgency.setAnnexUrl(srcImg);


        String product_type = request.getParameter("product_type");
        String product_org = request.getParameter("product_org");
        String product_name = request.getParameter("product_name");
        String product_person = request.getParameter("product_person");
        String product_time = request.getParameter("product_time");
        String channel_name = request.getParameter("channel_name");
        String channel_phone = request.getParameter("channel_phone");
        String channel_weixin = request.getParameter("channel_weixin");
        String channel_email = request.getParameter("channel_email");
        String channel_dupty = request.getParameter("channel_duty");
        String channel_info = request.getParameter("channel_info");

        /*********机构产品信息************/
        if (!Strings.isNullOrEmpty(product_type)) {
            int type = Integer.valueOf(product_type);
            if (type < 0) {
                return ResponseUtil.fail(0, "请选择产品类型");
            } else {
                rAgency.setProductType(type);
            }
        } else {
            return ResponseUtil.fail(0, "产品类型不能为空");
        }
        if (!Strings.isNullOrEmpty(product_org)) {
            rAgency.setAgencyName(product_org);
        } else {
            return ResponseUtil.fail(0, "请填写机构名称");
        }

        if (!Strings.isNullOrEmpty(product_name)) {
            rAgency.setProductName(product_name);
        } else {
            return ResponseUtil.fail(0, "请填写产品名称");
        }

        if (!Strings.isNullOrEmpty(product_person)) {
            rAgency.setRecommend(product_person);
        } else {
            return ResponseUtil.fail(0, "请填写推荐人");
        }

        if (!Strings.isNullOrEmpty(product_time)) {
            rAgency.setEntryTime(DateUtil.strToSqlDate(product_time));
        } else {
            return ResponseUtil.fail(0, "请填写准入时间");
        }
        /*************联系人信息*****************/
        if (!Strings.isNullOrEmpty(channel_name)) {
            rAgency.setContactName(channel_name);
        } else {
            return ResponseUtil.fail(0, "请填写联系人姓名");
        }

        if (!Strings.isNullOrEmpty(channel_name)) {
            rAgency.setContactName(channel_name);
        } else {
            return ResponseUtil.fail(0, "请填写联系人姓名");
        }
        if (!Strings.isNullOrEmpty(channel_phone)) {
            rAgency.setContactPhone(channel_phone);
        } else {
            return ResponseUtil.fail(0, "请填写联系人电话");
        }

        if (!Strings.isNullOrEmpty(channel_weixin)) {
            rAgency.setContactWechat(channel_weixin);
        }
        if (!Strings.isNullOrEmpty(channel_email)) {
            rAgency.setContactEmail(channel_email);
        }
        if (!Strings.isNullOrEmpty(channel_dupty)) {
            rAgency.setContactDupty(channel_dupty);
        }
        if (!Strings.isNullOrEmpty(channel_info)) {
            rAgency.setDescription(channel_info);
        }

        boolean save = baseService.save(rAgency);
        if (save) {
            return ResponseUtil.success();
        }
        return ResponseUtil.fail(0, "提交失败");
    }


    @ResponseBody
    @RequestMapping(value = "/page/list")
    public ModelAndView listPage(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/product/agency_list");

        return modelAndView;
    }


    @ResponseBody
    @RequestMapping(value = "/list")
    public String agencyList(HttpServletRequest request) {
        Employee employee = BackUtil.getLoginEmployee(request, employeeService);
        int eid = employee.getId();
        String depart = employee.getDepartment();
        int role = employee.getRole();
        if (!depart.equals("系统") && !depart.contains("市场")) {
            return ResponseUtil.fail(0, "获取失败");
        }
        DetachedCriteria criteria = DetachedCriteria.forClass(RAgency.class);
        if (role == 4) {
            criteria.add(Restrictions.eq("createPerson", eid));
        }
        criteria.addOrder(Order.desc("createTime"));
        List<RAgency> list = baseService.getList(criteria);
        JSONArray array = JSON.parseArray(JSON.toJSONString(list));
        return ResponseUtil.success_front(array);
    }


}

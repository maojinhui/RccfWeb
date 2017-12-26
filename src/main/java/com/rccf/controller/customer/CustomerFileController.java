package com.rccf.controller.customer;


import com.alibaba.fastjson.JSONObject;
import com.rccf.constants.UrlConstants;
import com.rccf.constants.build.DebugManager;
import com.rccf.model.Employee;
import com.rccf.model.customer.RCustomerFile;
import com.rccf.model.customer.RCustomerFileDel;
import com.rccf.service.BaseService;
import com.rccf.service.EmployeeService;
import com.rccf.util.BackUtil;
import com.rccf.util.DateUtil;
import com.rccf.util.ResponseUtil;
import com.rccf.util.Strings;
import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.UUID;

@Controller
@RequestMapping(value = "/customer/file" , produces = UrlConstants.PRODUCES)
public class CustomerFileController {

    private static final Logger logger = Logger.getLogger(CustomerFileController.class);

    @Autowired
    BaseService baseService;

    @Autowired
    EmployeeService employeeService;

    @ResponseBody
    @RequestMapping(value = "/upload")
    public String uploadFile(@RequestParam(value = "file", required = false) MultipartFile file  , HttpServletRequest request , HttpServletResponse response){
        Employee employee = BackUtil.getLoginEmployee(request,employeeService);
        if(employee==null){
            return ResponseUtil.fail(0,"登录失效，请重新登录");
        }
        String srcImg = "";
        logger.info("开始上传图片");
        String server_path ;
        String  host_address ;
        if(DebugManager.DEBUG){
            server_path = DebugManager.LOCAL_IMAGE_PATH;
            host_address = DebugManager.LOCAL_HOST_ADDRESS;
        }else{
            server_path = DebugManager.SERVER_IMAGE_CUSTOMER_FILE_PATH;
            host_address = DebugManager.SERVER_CUSTOMER_HOST_ADDRESS;
        }
        String fileName = file.getOriginalFilename();
        String extention = fileName.substring(fileName.lastIndexOf("."));
        // 新文件名
        String newFileName = UUID.randomUUID().toString().substring(0, 16) + extention;
        // 获得项目的路径
//        ServletContext sc = request.getSession().getServletContext();
        String path = server_path+newFileName;
        logger.info("path:" + path);

        File f = new File(path);
        File parentFile = f.getParentFile();
        if (!parentFile.exists())
            parentFile.mkdirs();

        if (!file.isEmpty()) {//文件是否为空
            try {
                int  state = IOUtils.copy(file.getInputStream(), new FileOutputStream(new File(path)));
                if(state>0){
                    srcImg = host_address+newFileName;
                }
            } catch (IOException e) {
                e.printStackTrace();
                return ResponseUtil.fail(0, "文件上传失败");
            }
        }else{
            return ResponseUtil.fail(0,"上传的文件为空");
        }
        logger.info("图片上传完成");

        String customer_id = request.getParameter("customer_id");
        int eid = employee.getId() ;
        String fileID = request.getParameter("file_id");
        RCustomerFile customerFile ;
        if(!Strings.isNullOrEmpty(fileID)){
            customerFile = (RCustomerFile) baseService.get(RCustomerFile.class , Integer.valueOf(fileID));
        }else{
            customerFile =   new RCustomerFile();
        }

        customerFile.setCreatePerson(eid);
        customerFile.setCreateTime(DateUtil.date2Timestamp(new Date()));
        customerFile.setCustomerId(customer_id);
        customerFile.setFile(path);
        customerFile.setUrl(srcImg);
        customerFile.setType(1);
        boolean save =  baseService.save(customerFile);
        if(save){
            JSONObject object = new JSONObject();
            object.put("id", customerFile.getId());
            object.put("url" , customerFile.getUrl());

            return ResponseUtil.success(object);
        }
        return ResponseUtil.fail();
    }


    @ResponseBody
    @RequestMapping(value = "/del")
    public String deleteFile(HttpServletRequest request){
        Employee employee = BackUtil.getLoginEmployee(request,employeeService);

        String fileId = request.getParameter("file_id");
        if(!Strings.isNullOrEmpty(fileId)){
            RCustomerFile file = (RCustomerFile) baseService.get(RCustomerFile.class,Integer.valueOf(fileId));
            if(file!=null){
                RCustomerFileDel del = new RCustomerFileDel();
                del.setCustomerId(file.getCustomerId());
                del.setDelTime(DateUtil.date2Timestamp(new Date()));
                del.setFile(file.getFile());
                if(employee!=null){
                    del.setDelPerson(employee.getId());
                }
                boolean save =  baseService.save(del);
                if(save){
                    boolean delstate =  baseService.delete(file);
                    if(delstate){
                        return ResponseUtil.success();
                    }else{
                        return ResponseUtil.fail(0,"删除失败,请重新操作");
                    }
                }else{
                    return ResponseUtil.fail(0,"删除失败,请重新操作");
                }
            }
        }

        return ResponseUtil.fail(0,"没有找到该文件");
    }





}

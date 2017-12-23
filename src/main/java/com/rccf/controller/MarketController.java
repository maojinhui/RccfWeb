package com.rccf.controller;


import com.alibaba.fastjson.JSONObject;
import com.rccf.constants.UrlConstants;
import com.rccf.constants.build.DebugManager;
import com.rccf.model.Employee;
import com.rccf.model.poster.BPoster;
import com.rccf.model.poster.BPosterFont;
import com.rccf.service.BaseService;
import com.rccf.service.EmployeeService;
import com.rccf.util.BackUtil;
import com.rccf.util.DateUtil;
import com.rccf.util.ResponseUtil;
import com.rccf.util.Strings;
import com.rccf.util.image.Watermark;
import org.apache.commons.io.IOUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
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
import java.util.UUID;
import org.apache.log4j.Logger;


@Controller
@RequestMapping(value = "/market" , produces = UrlConstants.PRODUCES)
public class MarketController {

    private Logger logger = Logger.getLogger(this.getClass().getSimpleName());

    @Autowired
    BaseService baseService;

    @Autowired
    EmployeeService employeeService;



    @RequestMapping(value = "/add_mould")
    public ModelAndView addMarketText(HttpServletRequest request){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/marketing/m_add_mould");
        DetachedCriteria criteria = DetachedCriteria.forClass(BPosterFont.class);
        List<BPosterFont> list = baseService.getList(criteria);
        modelAndView.addObject("fonts",list);
        return modelAndView;
    }


    @ResponseBody
    @RequestMapping(value = "/upload")
    public String uploadImageAndText(@RequestParam(value = "file", required = true) MultipartFile file , HttpServletRequest request){
        Employee employee = BackUtil.getLoginEmployee(request,employeeService);
        String srcImg = "";
        logger.info("开始上传图片");
        String server_path ;
        String  host_address ;
        if(DebugManager.DEBUG){
            server_path = DebugManager.LOCAL_IMAGE_PATH;
            host_address = DebugManager.LOCAL_HOST_ADDRESS;
        }else{
            server_path = DebugManager.SERVER_IMAGE_PATH;
            host_address = DebugManager.SERVER_HOST_ADDRESS;
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


        String nameObject = request.getParameter("name");
        String phoneObject = request.getParameter("phone");
        String title = request.getParameter("title");

        JSONObject object = new JSONObject();
        object.put("img",path);
        object.put("name",nameObject);
        object.put("phone",phoneObject);

        BPoster bPoster = new BPoster();
        bPoster.setTitle(title);
        bPoster.setBackimg(path);
        bPoster.setExtra(object.toJSONString());
        bPoster.setType(1);
        bPoster.setThumb(srcImg);
        bPoster.setAddTime(DateUtil.date2Timestamp(new Date()));
        if(employee!=null){
            bPoster.setAddPerson(employee.getId());
        }
        boolean save = baseService.save(bPoster);
        if(save){
            return ResponseUtil.success();
        }
        return ResponseUtil.fail(0,"上传失败");
    }


    @ResponseBody
    @RequestMapping(value = "/preview")
    public String reviewTheImage(@RequestParam(value = "file", required = true) MultipartFile file ,HttpServletRequest request){
        Employee employee = BackUtil.getLoginEmployee(request,employeeService);
        String srcImg = "";
        logger.info("开始上传图片");
        String server_path ;
        String  host_address ;
        if(DebugManager.DEBUG){
            server_path = DebugManager.LOCAL_IMAGE_PATH;
            host_address = DebugManager.LOCAL_HOST_ADDRESS;
        }else{
            server_path = DebugManager.SERVER_IMAGE_PATH;
            host_address = DebugManager.SERVER_HOST_ADDRESS;
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
                    srcImg = host_address+fileName;
                }
            } catch (IOException e) {
                e.printStackTrace();
                return ResponseUtil.fail(0, "文件上传失败");
            }
        }else{
            return ResponseUtil.fail(0,"上传的文件为空");
        }
        logger.info("图片上传完成");


        String nameObject = request.getParameter("name");
        String phoneObject = request.getParameter("phone");
        String title = request.getParameter("title");

        JSONObject object = new JSONObject();
        object.put("img",path);
        object.put("name",nameObject);
        object.put("phone",phoneObject);

        BPoster bPoster = new BPoster();
        bPoster.setTitle(title);
        bPoster.setBackimg(path);
        bPoster.setExtra(object.toJSONString());
        bPoster.setType(1);
        bPoster.setThumb(srcImg);
        bPoster.setAddTime(DateUtil.date2Timestamp(new Date()));
        if(employee!=null){
            bPoster.setAddPerson(employee.getId());
        }
        String name = "钱小融";
        String phone = "188888888888";
        String src = "/temp/t_"+new Date().getTime()+".jpg";
        String targetPath = request.getRealPath("")+src;
        String tempPath = Watermark.getWatermarkImage(bPoster,name,phone,targetPath);
        if(Strings.isNullOrEmpty(tempPath)){
            return ResponseUtil.fail(0,"生成失败");
        }
        return ResponseUtil.success(src);
    }



}

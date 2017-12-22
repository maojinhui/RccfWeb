package com.rccf.controller;


import com.rccf.constants.UrlConstants;
import com.rccf.constants.build.DebugManager;
import com.rccf.service.BaseService;
import com.rccf.util.ResponseUtil;
import org.apache.commons.io.IOUtils;
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
import java.util.UUID;
import org.apache.log4j.Logger;


@Controller
@RequestMapping(value = "/market" , produces = UrlConstants.PRODUCES)
public class MarketController {

    private Logger logger = Logger.getLogger(this.getClass().getSimpleName());

    @Autowired
    BaseService baseService;

    @RequestMapping(value = "/add_mould")
    public ModelAndView addMarketText(HttpServletRequest request){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/marketing/m_add_mould");
        return modelAndView;
    }


    @ResponseBody
    @RequestMapping(value = "/upload")
    public String uploadImageAndText(@RequestParam(value = "file", required = true) MultipartFile file , HttpServletRequest request){
        String  srcImg = "";

        logger.info("开始上传图片");
        String server_path = DebugManager.SERVER_IMAGE_PATH;
        if(DebugManager.DEBUG){
            server_path = DebugManager.LOCAL_IMAGE_PATH;
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
//                    srcImg =
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








        return ResponseUtil.fail();
    }






}

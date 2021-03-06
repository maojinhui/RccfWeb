package com.rccf.controller.poster;

import com.rccf.constants.UrlConstants;
import com.rccf.service.BaseService;
import com.rccf.util.ResponseUtil;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.UUID;
import java.util.logging.Logger;

@Controller
@RequestMapping(value = "/poster/upload" , produces = UrlConstants.PRODUCES)
public class UploadController {

    private Logger logger = Logger.getLogger(this.getClass().getSimpleName());

    @Autowired
    BaseService baseService;


    @ResponseBody
    @RequestMapping(value = "/simple")
    public String uploadImageAndText(@RequestParam(value = "file", required = true) MultipartFile file , HttpServletRequest request){
        System.out.println("开始接受文件");
        String server_path = "/home/php-www/photo/";
        String loacl_path = "/Users/greatland/Desktop/";
        String fileName = file.getOriginalFilename();
        String extention = fileName.substring(fileName.lastIndexOf("."));
        // 新文件名
        String newFileName = UUID.randomUUID().toString().substring(0, 16) + extention;
        // 获得项目的路径
//        ServletContext sc = request.getSession().getServletContext();
        String path = loacl_path+newFileName;
        logger.info("path" + path);

        File f = new File(path);
        if (!f.exists())
            f.mkdirs();

        if (!file.isEmpty()) {//文件是否为空
            try {
                IOUtils.copy(file.getInputStream(), new FileOutputStream(new File(path)));
            } catch (IOException e) {
//                e.printStackTrace();
                return ResponseUtil.fail(0, "文件上传失败");
            }
        }else{
            return ResponseUtil.fail(0,"上传的文件为空");
        }
        return ResponseUtil.fail();
    }




}

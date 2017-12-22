package com.rccf.controller.poster;

import com.rccf.constants.UrlConstants;
import com.rccf.service.BaseService;
import com.rccf.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping(value = "/poster/upload" , produces = UrlConstants.PRODUCES)
public class UploadController {

    @Autowired
    BaseService baseService;


    public String uploadImageAndText(@RequestParam(value = "file", required = true) MultipartFile file , HttpServletRequest request){
        System.out.println("开始接受文件");
        String server_path = "/usr/local/nginx/html/image/haibao/";
        String locale_path = "/usr/local/";





        return ResponseUtil.fail();
    }




}

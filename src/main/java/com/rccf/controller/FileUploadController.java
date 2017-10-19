package com.rccf.controller;

import com.rccf.constants.UrlConstants;
import com.rccf.model.EmployeeDocuments;
import com.rccf.service.BaseService;
import com.rccf.service.DocumentService;
import com.rccf.util.DateUtil;
import com.rccf.util.ResponseUtil;
import com.rccf.util.Strings;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.UUID;
import java.util.logging.Logger;

@Controller
@RequestMapping(value = "/upload", produces = UrlConstants.PRODUCES)
public class FileUploadController extends UploadController {

    private Logger logger = Logger.getLogger(this.getClass().getSimpleName());


    @Autowired
    DocumentService documentService;

    @Autowired
    BaseService baseService;


    @RequestMapping(value = "page")
    public ModelAndView uploadPage() {
        return new ModelAndView("/back/employee/employee_edit");
    }


    @ResponseBody
    @RequestMapping(value = "/document")
    public String employeeUpload(@RequestParam(value = "file", required = false) MultipartFile file, HttpServletRequest request) {
        String type = request.getParameter("type");
        if (Strings.isNullOrEmpty(type)) {
            return ResponseUtil.fail(0, "类型错误");
        }

        String eid = request.getParameter("eid");
        String fileName = file.getOriginalFilename();
        String extention = fileName.substring(fileName.lastIndexOf("."));
        // 新文件名
        String newFileName = UUID.randomUUID().toString().substring(0, 16) + extention;
        // 获得项目的路径
        ServletContext sc = request.getSession().getServletContext();

        String serverPath = sc.getRealPath("upload"); // 设定文件保存的目录
        String secondPath = File.separator + "employee" + File.separator + DateUtil.date2StringSimple(new Date()) + File.separator;
        String path = serverPath + secondPath;
        logger.info("path" + path);
        String filePath = path + newFileName;

        String url_path = File.separator + "file" + File.separator + "employee" + File.separator + DateUtil.date2StringSimple(new Date()) + File.separator + newFileName;
        logger.info("url_path" + url_path);

        File f = new File(path);
        if (!f.exists())
            f.mkdirs();

        if (!file.isEmpty()) {//文件是否为空
            try {
                IOUtils.copy(file.getInputStream(), new FileOutputStream(new File(filePath)));
            } catch (IOException e) {
//                e.printStackTrace();
                return ResponseUtil.fail(0, "文件上传失败");
            }
        }

        EmployeeDocuments document = null;
        if (!Strings.isNullOrEmpty(eid)) {
            document = documentService.findDocumentByeID(eid);
            if (document == null) {
                document = new EmployeeDocuments();
                document.setEid(eid);
            }
            if (type.equals("idcard_positive")) {
                document.setIdcardPositive(url_path);
            } else if (type.equals("idcard_negative")) {
                document.setIdcardNegative(url_path);
            } else if (type.equals("education")) {
                document.setEducation(url_path);
            } else if (type.equals("job_title")) {
                document.setJobTitle(url_path);
            } else if (type.equals("picture")) {
                document.setPicture(url_path);
            } else if (type.equals("leavingproof")) {
                document.setLeavingProof(url_path);
            } else if (type.equals("cridit_report")) {
                document.setCriditReport(url_path);
            } else if (type.equals("nocrime_report")) {
                document.setNocrimeReport(url_path);
            } else if (type.equals("examination")) {
                document.setExamination(url_path);
            } else {
                ResponseUtil.fail(0, "类型错误");
            }

            boolean state = baseService.save(document);
            if (state) {
                return ResponseUtil.success();
            } else {
                return ResponseUtil.fail();
            }

        } else {
            return ResponseUtil.fail(0, "没有找到用户id");
        }


    }


}

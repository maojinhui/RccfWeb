package com.rccf.controller.poster;

import com.rccf.constants.UrlConstants;
import com.rccf.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "" , produces = UrlConstants.PRODUCES)
public class UploadController {

    @Autowired
    BaseService baseService;



}

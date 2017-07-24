package com.rccf.controller;

import com.rccf.model.Role;
import com.rccf.service.RoleService;
import com.rccf.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * 权限管理后台界面
 * Created by greatland on 17/7/12.
 */
@Controller
@RequestMapping(value = "/admin")
public class PermissionAdminController {


    @Autowired
    RoleService roleService;


    @RequestMapping(value = "/login")
    public String login() {

        return "back/admin/login";
    }

    public String login(HttpServletRequest request) {


        return null;
    }

    @RequestMapping(value = "get_role")
    @ResponseBody
    public String getRole(HttpServletRequest request){
        int id = Integer.parseInt(request.getParameter("role_id"));
        Role role = roleService.findRoleByID(id);
        return ResponseUtil.success(role);
    }



}

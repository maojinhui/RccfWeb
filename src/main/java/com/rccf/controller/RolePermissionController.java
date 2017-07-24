package com.rccf.controller;

import com.rccf.model.Role;
import com.rccf.model.RolePermission;
import com.rccf.service.RolePermissonService;
import com.rccf.service.RoleService;
import com.rccf.util.DateUtil;
import com.rccf.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.Timestamp;
import java.util.Date;

/**
 * Created by greatland on 17/7/11.
 */
@Controller
@RequestMapping(value = "/permission")
public class RolePermissionController {

    @Autowired
    private RolePermissonService rolePermissonService;

    @Autowired
    private RoleService roleService;


    /**
     * 添加用户组拥有权限
     * @return
     */
    @RequestMapping(value = "/addrp")
    @ResponseBody
    public String addRolePermission() {
        RolePermission permission = new RolePermission();
        permission.setPermissionId(1);
        permission.setRoleId(1);
        Date date = new Date();
        Timestamp ts = new Timestamp(date.getTime());
        permission.setCreateTime(ts);
        rolePermissonService.add(permission);
        return ResponseUtil.success();
    }


    /**
     * 添加用户组
     * @param des
     * @param role_name
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/addrole")
    public String addRole(@RequestParam(value = "des", required = false) String des,
                          @RequestParam(value = "role_name") String role_name) {
        Role role = new Role();
        role.setCreateTime(DateUtil.date2Timestamp(new Date()));
        if (des != null && des != "") {
            role.setDescription(des);
        } else {
            role.setDescription("大小");
        }
        role.setRoleName(role_name);
        roleService.add(role);
        return ResponseUtil.success();
    }








}

package com.rccf.service.impl;

import com.rccf.dao.RoleDao;
import com.rccf.model.Role;
import com.rccf.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by greatland on 17/7/11.
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleDao dao;

    public void add(Role role) {
        dao.add(role);
    }

    public Role findRoleByID(int id) {
        return dao.findRoleByID(id);
    }
}

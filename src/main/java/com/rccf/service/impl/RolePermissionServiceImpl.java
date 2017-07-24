package com.rccf.service.impl;

import com.rccf.dao.RolePermissionDao;
import com.rccf.model.RolePermission;
import com.rccf.service.RolePermissonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by greatland on 17/7/11.
 */
@Service
public class RolePermissionServiceImpl implements RolePermissonService {

    @Autowired
    private RolePermissionDao dao;

    public void add(RolePermission rolePermission) {
        dao.add(rolePermission);
    }

    public void deleteById(int id) {
        dao.deleteById(id);
    }

    public List<RolePermission> list() {
        return dao.list();
    }
}

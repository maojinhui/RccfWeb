package com.rccf.dao;

import com.rccf.model.RolePermission;

import java.util.List;

/**
 * Created by greatland on 17/7/11.
 */
public interface RolePermissionDao {

    /**
     * 增加角色权限对应关系
     * @param rolePermission
     */
    void add(RolePermission rolePermission);

    /**
     *
     * 根据id删除一条角色权限对应关系
     * @param id
     */
    void deleteById(int id);

    /**
     * 列出角色权限对应关系
     * @return
     */
    List<RolePermission> list();

}

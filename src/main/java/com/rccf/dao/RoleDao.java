package com.rccf.dao;

import com.rccf.model.Role;

/**
 * Created by greatland on 17/7/11.
 */
public interface RoleDao {


    void add(Role role);

    Role findRoleByID(int id);

}

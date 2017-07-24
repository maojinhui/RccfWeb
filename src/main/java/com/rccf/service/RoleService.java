package com.rccf.service;

import com.rccf.model.Role;

/**
 * Created by greatland on 17/7/11.
 */
public interface RoleService {

    void add(Role role);

    Role findRoleByID(int id);

}

package com.uaa.auth.dao;

import com.uaa.auth.entity.Role;

import java.util.List;
import java.util.UUID;

/**
 * Created by wyf on 2017/11/22.
 */
public interface RoleDAO {

    void insert(Role role);

    int deleteById(UUID id);

    void update(Role role);

    Role selectById(UUID id);

    List<Role> selectAll();

}

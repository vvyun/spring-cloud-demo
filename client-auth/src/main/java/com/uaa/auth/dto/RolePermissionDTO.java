package com.uaa.auth.dto;

import com.uaa.auth.entity.Permission;
import lombok.Data;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

/**
 * Created by wyf on 2017/11/22.
 */
@Data
public class RolePermissionDTO {

    private Long relationId;

    private UUID roleId;

    private String name;

    private Timestamp updateTime;

    private String description;

    private List<Permission> permissions;

    public Long getRelationId() {
        return relationId;
    }

    public void setRelationId(Long relationId) {
        this.relationId = relationId;
    }

    public UUID getRoleId() {
        return roleId;
    }

    public void setRoleId(UUID roleId) {
        this.roleId = roleId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Permission> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<Permission> permissions) {
        this.permissions = permissions;
    }
}

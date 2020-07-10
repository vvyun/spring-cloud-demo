package com.uaa.auth.entity;

import lombok.Data;

import java.util.UUID;

/**
 * Created by wyf on 2017/11/22.
 */
@Data
public class Permission {

    private UUID id;

    private String permission;

    private String description;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

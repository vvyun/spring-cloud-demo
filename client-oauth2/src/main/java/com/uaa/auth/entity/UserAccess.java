package com.uaa.auth.entity;

import lombok.Data;

@Data
public class UserAccess {

    private String orgId;
    private String groupId;
    private String userId;
    private String userName;
    private String userPassword;
    private String orgType;
    private String flag;
    private String lastModifyTime;
    private String lastModifyUser;
}

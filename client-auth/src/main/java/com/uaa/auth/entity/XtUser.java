package com.uaa.auth.entity;

import lombok.Data;

import java.text.SimpleDateFormat;

@Data
public class XtUser {

    private String orgId;
    private String groupId;
    private String userId;
    private String userName;
    private String userPassword;
    private String orgType;
    private String flag;
    private String lastModifyTime;
    private String lastModifyUser;


    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }


    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }


    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }


    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }


    public String getOrgType() {
        return orgType;
    }

    public void setOrgType(String orgType) {
        this.orgType = orgType;
    }


    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }


    public String getLastModifyTime() {
        return lastModifyTime;
    }

    public void setLastModifyTime(java.sql.Date lastModifyTime) {
        this.lastModifyTime = new SimpleDateFormat("yyyy-MM-dd").format(lastModifyTime);
    }


    public String getLastModifyUser() {
        return lastModifyUser;
    }

    public void setLastModifyUser(String lastModifyUser) {
        this.lastModifyUser = lastModifyUser;
    }

}

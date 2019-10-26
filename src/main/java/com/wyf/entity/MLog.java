package com.wyf.entity;


public class MLog {

    private long uuid;
    private long type;
    private String requestContent;
    private String responseContent;
    private long flag;
    private java.sql.Timestamp requestTime;
    private java.sql.Timestamp responseTime;


    public long getUuid() {
        return uuid;
    }

    public void setUuid(long uuid) {
        this.uuid = uuid;
    }


    public long getType() {
        return type;
    }

    public void setType(long type) {
        this.type = type;
    }


    public String getRequestContent() {
        return requestContent;
    }

    public void setRequestContent(String requestContent) {
        this.requestContent = requestContent;
    }


    public String getResponseContent() {
        return responseContent;
    }

    public void setResponseContent(String responseContent) {
        this.responseContent = responseContent;
    }


    public long getFlag() {
        return flag;
    }

    public void setFlag(long flag) {
        this.flag = flag;
    }


    public java.sql.Timestamp getRequestTime() {
        return requestTime;
    }

    public void setRequestTime(java.sql.Timestamp requestTime) {
        this.requestTime = requestTime;
    }


    public java.sql.Timestamp getResponseTime() {
        return responseTime;
    }

    public void setResponseTime(java.sql.Timestamp responseTime) {
        this.responseTime = responseTime;
    }

    @Override
    public String toString() {
        return "MLog{" +
                "uuid=" + uuid +
                ", type=" + type +
                ", requestContent='" + requestContent + '\'' +
                ", responseContent='" + responseContent + '\'' +
                ", flag=" + flag +
                '}';
    }
}

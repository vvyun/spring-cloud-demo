package com.wyf.entity;

public class Permission {

    private int id;

    private String name;
    private int type;
    private String url;
    private int sort;
    private int pid;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public Permission(int id, String name, int type, String url, int sort, int pid) {
        super();
        this.id = id;
        this.name = name;
        this.type = type;
        this.url = url;
        this.sort = sort;
        this.pid = pid;
    }

    public Permission() {

    }


}

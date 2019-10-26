package com.wyf.entity;

/**
 * @author Yunfei
 */
public class User {
    private String name;
    private String id;
    private String password;
    private int status;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public User() {
        super();
    }

    public User(String name, String id, String password, int status) {
        this.name = name;
        this.id = id;
        this.password = password;
        this.status = status;
    }
}

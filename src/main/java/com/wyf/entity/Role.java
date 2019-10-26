package com.wyf.entity;

public class Role {

    private int id;
    private String name;
    private String describtion;

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

    public String getDescribtion() {
        return describtion;
    }

    public void setDescribtion(String describtion) {
        this.describtion = describtion;
    }

    public Role(int id, String name, String describtion) {
        super();
        this.id = id;
        this.name = name;
        this.describtion = describtion;
    }

    public Role(String name, String describtion) {
        super();
        this.name = name;
        this.describtion = describtion;
    }

    public Role() {

    }

}

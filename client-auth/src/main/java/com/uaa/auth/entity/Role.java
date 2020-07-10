package com.uaa.auth.entity;

import lombok.Data;

import java.sql.Timestamp;
import java.util.UUID;


@Data
public class Role {

    private UUID id;

    private String name;

    private Timestamp updateTime;

    private String description;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
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

    public static class RoleBuilder {
        private Role role = new Role();

        public RoleBuilder withId(UUID id) {
            role.setId(id);
            return this;
        }

        public RoleBuilder withName(String name) {
            role.setName(name);
            return this;
        }

        public RoleBuilder withDescription(String description) {
            role.setDescription(description);
            return this;
        }

        public RoleBuilder withUpdateTime(Timestamp time) {
            role.setUpdateTime(time);
            return this;
        }

        public Role build() {
            return role;
        }
    }
}

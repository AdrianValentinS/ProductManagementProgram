package com.sample.group.springproductmanagementproject.security.domain;

public enum UserPerms {
    READ("user:read"),
    WRITE("user:write");

    private final String permission;

    UserPerms(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}

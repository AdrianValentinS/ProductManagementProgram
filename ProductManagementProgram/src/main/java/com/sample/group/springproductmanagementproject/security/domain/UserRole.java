package com.sample.group.springproductmanagementproject.security.domain;

import com.google.common.collect.Sets;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

public enum UserRole
{
    ADMIN(Sets.newHashSet(UserPerms.READ, UserPerms.WRITE)),
    USER(Sets.newHashSet(UserPerms.READ));

    private final Set<UserPerms> permissions;

    UserRole(Set<UserPerms> permissions) {
        this.permissions = permissions;
    }

    public Set<UserPerms> getPermissions() {
        return permissions;
    }

    public Set<SimpleGrantedAuthority> getGrantedAuthorities() {
        Set<SimpleGrantedAuthority> permissions = getPermissions().stream().map(permission -> new SimpleGrantedAuthority(permission.getPermission())).collect(Collectors.toSet());
        permissions.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        return permissions;
    }
}

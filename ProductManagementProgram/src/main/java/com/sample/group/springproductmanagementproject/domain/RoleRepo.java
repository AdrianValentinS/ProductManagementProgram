package com.sample.group.springproductmanagementproject.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface RoleRepo extends JpaRepository<Role, UUID> {
    List<Role> findAllByNameIn(List<UserRole> names);
}

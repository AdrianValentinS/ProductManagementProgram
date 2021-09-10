package com.sample.group.springproductmanagementproject.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface EmployeeRepo extends JpaRepository<Employee, UUID> {

    Optional<Employee> findByUsername(final String username);
}


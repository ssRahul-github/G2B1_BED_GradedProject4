package com.greatlearning.employee.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.greatlearning.employee.model.Role;

public interface RoleRepository extends JpaRepository<Role,Long> {
//	Optional<Role> findByRolename(String rolename);

}
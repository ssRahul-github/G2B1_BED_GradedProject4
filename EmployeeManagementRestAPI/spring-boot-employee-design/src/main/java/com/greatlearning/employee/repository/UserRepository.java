package com.greatlearning.employee.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.greatlearning.employee.model.User;

public interface UserRepository extends JpaRepository<User,Long> {
	Optional<User> findByUsername(String username);

}

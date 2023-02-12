package com.greatlearning.employee.service;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.springframework.stereotype.Service;

import com.greatlearning.employee.model.User;
import com.greatlearning.employee.repository.UserRepository;

@Service
public class UserService {
	
	private final UserRepository userRepository;
	public UserService(UserRepository userRepository)
	{
		this.userRepository=userRepository;
	}
	public User AddUser(User user)
	{
		User saveduser=this.userRepository.save(user);
		return saveduser;
	}
	public Set<User> fetchAllUsers()
	{
		return new HashSet<>(this.userRepository.findAll());
	}
	public User fetchById(long userId)
	{
		return this.userRepository
				   .findById(userId)
				   .orElseThrow(()->new IllegalArgumentException
						   ("Invalid user id"));
	}

	public void deleteById(long userId)
	{ 
		this.userRepository.deleteById(userId);
	}
	
	}


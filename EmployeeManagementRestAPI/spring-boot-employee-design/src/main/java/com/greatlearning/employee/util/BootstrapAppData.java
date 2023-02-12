package com.greatlearning.employee.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.greatlearning.employee.model.Employee;
import com.greatlearning.employee.model.Role;
import com.greatlearning.employee.model.User;
import com.greatlearning.employee.repository.EmployeeRepository;
import com.greatlearning.employee.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class BootstrapAppData {
	private final EmployeeRepository employeeRepository;
	private final UserRepository userRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@EventListener(ApplicationReadyEvent.class)
	public void insertEmployees(ApplicationReadyEvent event)
	{
		Employee Anil=new Employee();
		Anil.setFirstname("Anil");
		Anil.setLastname("Jain");
		Anil.setEmail("AnilJain@gmail.com");
		this.employeeRepository.save(Anil);
		Employee Sunil=new Employee();
		Sunil.setFirstname("Sunil");
		Sunil.setLastname("Sharma");
		Sunil.setEmail("SunilSharma@gmail.com");
		this.employeeRepository.save(Sunil);
		
		User user1=new User();
		user1.setUsername("user1");
		user1.setPassword(passwordEncoder.encode("user"));
		Role userRole=new Role();
		userRole.setRoleName("USER");
		
		userRole.setUsers(user1);
		user1.addRole(userRole);
		
		
		User Admin=new User();
		Admin.setUsername("Admin");
		Admin.setPassword(passwordEncoder.encode("admin"));
		Role AdminRole=new Role();
		AdminRole.setRoleName("ADMIN");
		AdminRole.setUsers(Admin);
		Admin.addRole(AdminRole);
		
		User superAdmin=new User();
		superAdmin.setUsername("superAdmin");
		superAdmin.setPassword(passwordEncoder.encode("super"));
		Role superAdminRole=new Role();
		superAdminRole.setRoleName("SUPERADMIN");
		
		superAdminRole.setUsers(superAdmin);
		superAdmin.addRole(superAdminRole);
		
		this.userRepository.save(user1);
		this.userRepository.save(Admin);
		this.userRepository.save(superAdmin);
	}
}

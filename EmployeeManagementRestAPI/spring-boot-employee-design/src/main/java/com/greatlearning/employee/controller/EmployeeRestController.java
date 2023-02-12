package com.greatlearning.employee.controller;

import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.greatlearning.employee.model.Employee;
import com.greatlearning.employee.model.Role;
import com.greatlearning.employee.model.User;
import com.greatlearning.employee.service.EmployeeService;
import com.greatlearning.employee.service.RoleService;
import com.greatlearning.employee.service.UserService;

//import io.swagger.v3.oas.annotations.OpenAPIDefinition;
//import io.swagger.v3.oas.annotations.responses.ApiResponse;


@RestController
@RequestMapping("/api/employees")
public class EmployeeRestController {
	
	@Autowired
	private EmployeeService employeeService;
	private RoleService roleService;
	private UserService userService;
	public EmployeeRestController(EmployeeService employeeService,
			UserService userService)
	{
		this.employeeService=employeeService;
		this.userService=userService;
	}
	@GetMapping
	public Set<Employee> fetchAllemployees()
	{
		return this.employeeService.fetchAllEmployees();
	}
	@GetMapping("/{id}")
	public Employee fetchById(@PathVariable("id") long employeeId)
	{
		return this.employeeService.fetchById(employeeId);
	}
	@PostMapping
	public Employee save(@RequestBody Employee employee)
	{
		return this.employeeService.AddEmployee(employee);
	}
	@PostMapping("/setroles")
	public Role save(@RequestBody Role role)
	{
		return this.roleService.AddRole(role);
	}
	@PostMapping("/setuser")
	public User save(@RequestBody User user)
	{
		return this.userService.AddUser(user);
	}
	@GetMapping("/fetchroles")
	public Set<Role> fetchAllroles()
	{
		return this.roleService.fetchAllRoles();
	}
	@DeleteMapping("/{id}")
	public void deleteById(@PathVariable("id") long employeeId)
	{
		this.employeeService.deleteById(employeeId);
	}
	
	@Transactional
	@PutMapping("/{id}")
	public Employee updateEmployee(@PathVariable("id") long employeeId, @RequestBody Employee employee)
	{
		return this.employeeService.updateEmployee(employeeId,employee);
	}
	@GetMapping("/search/{id}")
	public Set<Employee> fetchByFirstname(@PathVariable("id") String search)
	{
		return this.employeeService.FetchByFirstname(search);
	}
	@GetMapping("/sort")
	public List<Employee> fetchAllEmployeesByFirstname(
	@RequestParam(name="order",required=true) String order)		
	{
		return this.employeeService.fetchAllEmployeesByFirstname(order);
	}
	
}	



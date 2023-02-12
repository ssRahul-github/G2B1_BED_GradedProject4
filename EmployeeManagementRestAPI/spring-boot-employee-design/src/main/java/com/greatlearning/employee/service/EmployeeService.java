package com.greatlearning.employee.service;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.springframework.stereotype.Service;

import com.greatlearning.employee.model.Employee;
import com.greatlearning.employee.repository.EmployeeRepository;

@Service
public class EmployeeService {
	
	private final EmployeeRepository employeeRepository;
	public EmployeeService(EmployeeRepository employeeRepository)
	{
		this.employeeRepository=employeeRepository;
	}
	public Employee AddEmployee(Employee employee)
	{
		Employee savedEmployee=this.employeeRepository.save(employee);
		return savedEmployee;
	}
	public Set<Employee> fetchAllEmployees()
	{
		return new HashSet<>(this.employeeRepository.findAll());
	}
	public Employee fetchById(long employeeId)
	{
		return this.employeeRepository
				   .findById(employeeId)
				   .orElseThrow(()->new IllegalArgumentException
						   ("Invalid employee id"));
	}

	public void deleteById(long employeeId)
	{ 
		this.employeeRepository.deleteById(employeeId);
	}
	public Employee updateEmployee(long employeeId, Employee updatedEmployee) {
		System.out.println("Passed in employee is "+ updatedEmployee);
		System.out.println("Passed employee-id is :: "+ employeeId);
		Employee employeeFromRepository = this.fetchById(employeeId);
		employeeFromRepository.setFirstname(updatedEmployee.getFirstname());
		employeeFromRepository.setEmail(updatedEmployee.getEmail());
		employeeFromRepository.setLastname(updatedEmployee.getLastname());
		this.employeeRepository.save(employeeFromRepository);
		return employeeFromRepository;
		
	}
	public Set<Employee> FetchByFirstname(String firstname)
	{
		return new HashSet<>(this.employeeRepository.findEmployeeByFirstname(firstname));
		
	}
	public List<Employee> fetchAllEmployeesByFirstname(String order)
	{

		if(order.equalsIgnoreCase("asc"))
			return new ArrayList<>(this.employeeRepository.findAllByOrderByFirstnameAsc());
		else if(order.equalsIgnoreCase("desc"))
			return new ArrayList<>(this.employeeRepository.findAllByOrderByFirstnameDesc());
		else
			return null;
	}
	}


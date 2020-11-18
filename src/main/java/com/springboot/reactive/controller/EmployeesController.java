package com.springboot.reactive.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.reactive.model.Employee;
import com.springboot.reactive.repository.EmployeeRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("v1/employees")
public class EmployeesController 
{
	@Autowired
	private EmployeeRepository repository;
	
	@GetMapping
	public Flux<Employee> getAllEmployees()
	{
		return repository.findAll();
	}
	
	@GetMapping("/{id}")
	public Mono<Employee> getEmployeeById(@PathVariable String id)
	{
		return repository.findById(id);
	}
	
	@PostMapping
	public Mono<Employee> saveEmployees(@RequestBody Employee emp)
	{
		return repository.save(emp);
	}
	
	@PutMapping("/update")
	public Mono<Employee> updateEmployee(@RequestBody Employee emp)
	{
		return repository.save(emp);
	}
	
	@DeleteMapping
	public Mono<Void> deleteEmployees()
	{
		return repository.deleteAll();
	}
	
	@DeleteMapping("/{id}")
	public Mono<Void> deleteEmployeeById(@PathVariable String id)
	{
		return repository.deleteById(id);
	}

}

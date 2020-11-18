
package com.springboot.reactive.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import com.springboot.reactive.model.Employee;

@Repository
public interface EmployeeRepository extends ReactiveMongoRepository<Employee, String> 
{

}

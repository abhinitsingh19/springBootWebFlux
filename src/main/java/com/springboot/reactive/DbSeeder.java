package com.springboot.reactive;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.mongodb.core.ReactiveMongoOperations;
import org.springframework.stereotype.Component;

import com.springboot.reactive.model.Employee;
import com.springboot.reactive.repository.EmployeeRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class DbSeeder implements CommandLineRunner
{
	@Autowired
	private EmployeeRepository employeeRepository;
	@Autowired
	private ReactiveMongoOperations reactiveMongoOperations;
//	
//	private List<Employee>employees = Arrays.asList(new Employee("1","Abhinit","BasicDev"),
//            new Employee("1","Ram","Daac"));
	
	private Flux<Employee> employeesList = Flux.just(
			new Employee("1","Abhinit","BasicDev"),
            new Employee("2","Rahul","Daac"),
            new Employee("3","Rama","systech"),
            new Employee("4","kamlesh","Daac"));
	@Override
	public void run(String... args) throws Exception 
	{
		DbSetup();
	}
	private void DbSetup()
	{
		
		System.out.println("saving in db");
		Flux<Employee> employees = employeesList.flatMap(emp->employeeRepository.save(emp));
		
	}
	
}

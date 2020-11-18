package com.springboot.reactive.test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;
import reactor.test.StepVerifier;

public class FlatMapTest 
{
	private Map<String,String> empDetailsMap = null;
	@Before
	public void setUp()
	{
		if(empDetailsMap == null)
		{
			empDetailsMap = new HashMap<>();
			empDetailsMap.put("1", "Ram");
			empDetailsMap.put("2", "Ramesh");
			empDetailsMap.put("3", "Rameshwar");
			empDetailsMap.put("4", "Rama");
			empDetailsMap.put("5", "Ramakant");
			empDetailsMap.put("6", "Ramu");
			empDetailsMap.put("7", "Ramit");
			empDetailsMap.put("8", "Rambahadur");
		}
	}


	@Test
	public void flatMapTest()
	{
		List<String> empList = Arrays.asList("1","2","3","4","5","6","7","8");
 		Flux<String> flux = Flux.fromIterable(empList);
 		Flux<String> fluxEmpNames = flux.flatMap(empId->getEmployeeDetails(empId)).log();
 		
 		
 		StepVerifier.create(fluxEmpNames).expectNextCount(8).verifyComplete();
	}
	
	private Mono<String> getEmployeeDetails(String empId) 
	{
		// TODO Auto-generated method stub
		String empName = empDetailsMap.getOrDefault(empId, "Not Found");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Mono.just(empName);
	}

	@Test
	public void flatMapTestUsingParallelSchedulers()
	{
		List<String> empList = Arrays.asList("1","2","3","4","5","6","7","8");
 		Flux<String> flux = Flux.fromIterable(empList).window(2).flatMap(identifiers->identifiers.flatMap
 				(empId->getEmployeeDetails(empId)
 				.subscribeOn(Schedulers.parallel())))
 				.log();
 		
 		StepVerifier.create(flux).expectNextCount(8).verifyComplete();
	}
}

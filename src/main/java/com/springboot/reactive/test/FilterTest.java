package com.springboot.reactive.test;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

public class FilterTest 
{
	
	@Test
	public void filter1()
	{
		List<String> cities = Arrays.asList("Pune","Mumbai","Jaipur","Gurgaon","Delhi");
		Flux<String> citiesFlux = Flux.fromIterable(cities);
		Flux<String> filteredCities = citiesFlux.filter(city->city.startsWith("J"));
		
		StepVerifier.create(filteredCities)
		.expectNext("Jaipur")
		.verifyComplete();
	}
	
	@Test
	public void filter2()
	{
		List<String> cities = Arrays.asList("Pune","Mumbai","Jaipur","Gurgaon","Delhi");
		Flux<String> citiesFlux = Flux.fromIterable(cities);
		Flux<String> filteredCities = citiesFlux.filter(city->city.length()<5);
		
		StepVerifier.create(filteredCities)
		.expectNext("Pune")
		.verifyComplete();
	}

}

package com.springboot.reactive.test;

import org.junit.Test;

import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

public class MonoTest 
{

	@Test
	public void mono1()
	{
		Mono.just("A")
		.log()
		.subscribe(item->System.out.println(item));
	}
	
	@Test
	public void mono2()
	{
		Mono.error(new Exception("some exception occured"))
		.log()
		.doOnError(error->System.out.println(error.getMessage()))
		.subscribe();
	}
	
	@Test
	public void junitmono1()
	{
		Mono<String> mono = Mono.just("A").log();
		
		StepVerifier.create(mono).expectNext("A")
		.verifyComplete();
		
	}
	
	@Test
	public void junitmono2()
	{
		Mono<String> mono = Mono.error(new RuntimeException("Exception occured"));
		StepVerifier.create(mono).expectError(RuntimeException.class).verify();
		
	}
}

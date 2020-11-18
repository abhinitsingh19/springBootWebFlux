package com.springboot.reactive.test;

import org.junit.Test;

import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

public class ErrorHandlingTest 
{

	@Test
	public void doOnError()
	{
		Flux<String> flux = Flux.just("A","B","C")
	    .concatWith(Flux.error(new RuntimeException("some error")))
	    .doOnError(error -> System.out.println("error occured"+error))
	    .concatWith(Flux.just("D"));
		
		StepVerifier.create(flux.log())
		.expectNext("A", "B","C")
		.expectError()
		.verify();
	}
	
	@Test
	public void onErrorReturn()
	{
		Flux<String> flux = Flux.just("A","B","C")
	    .concatWith(Flux.error(new RuntimeException("some error")))
	    .onErrorReturn("fallback value")
	    .concatWith(Flux.just("D"));
		
		StepVerifier.create(flux.log())
		.expectNext("A", "B","C","fallback value","D")
		.verifyComplete();
	}
	
	@Test
	public void onErrorResume()
	{
		Flux<String> flux = Flux.just("A","B","C")
	    .concatWith(Flux.error(new RuntimeException("some error")))
//	    .onErrorResume(it->Flux.just("D"));
		.onErrorResume(RuntimeException.class,it->Flux.just("D"));
		
		StepVerifier.create(flux.log())
		.expectNext("A", "B","C","D")
		.verifyComplete();
	}
	
	@Test
	public void onErrorMap()
	{
		Flux<String> flux = Flux.just("A","B","C");
//	    .concatWith(Flux.error(new RuntimeException("some error"))
//	    .onErrorResume(it->Flux.just("D"));
//		.onErrorResume(RuntimeException.class,it->Flux.just("D"));
//	    .onErrorMap(error->error.getClass()==RuntimeException.class, error->)
		
		StepVerifier.create(flux.log())
		.expectNext("A", "B","C","D")
		.verifyComplete();
	}
	
}

package com.springboot.reactive.test;

import org.junit.Test;

import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

public class FluxUnitTest 
{
	@Test
	public void flux1()
	{
		Flux<String> flux = Flux.just("A","B","C");
		
		StepVerifier.create(flux)
		.expectNext("A")
		.expectNext("B")
		.expectNext("C")
		.expectNext("D")
		.verifyComplete();
	}
	
	@Test
	public void flux2()
	{
		Flux<String> flux = Flux.just("A","B","C")
							.concatWith(Flux.error(new RuntimeException("some error occured")))
							.log();
		
		StepVerifier.create(flux)
		.expectNext("A")
		.expectNext("B")
		.expectNext("C")
//		.expectError(RuntimeException.class)
		.expectErrorMessage("some error occured")
		.verify();
	}

}

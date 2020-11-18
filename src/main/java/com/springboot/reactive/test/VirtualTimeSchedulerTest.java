package com.springboot.reactive.test;

import java.time.Duration;

import org.junit.Test;

import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;
import reactor.test.scheduler.VirtualTimeScheduler;

public class VirtualTimeSchedulerTest 
{
	@Test
	public void timeOperator() 
	{
		Flux<Integer> flux = Flux.just(1,2,3,4,5)
		.delayElements(Duration.ofSeconds(1))
		.log();
		
		StepVerifier.create(flux).
		expectNext(1,2,3,4,5)
		.verifyComplete();
	}
	
	@Test
	public void manipulateTimeOperatorUsingVirtualTimeScheduler() 
	{
		
		VirtualTimeScheduler.getOrSet();
		Flux<Integer> flux = Flux.just(1,2,3,4,5)
		.delayElements(Duration.ofSeconds(1))
		.log();
		
		StepVerifier.withVirtualTime(()->flux)
		.thenAwait(Duration.ofSeconds(1))
		.expectNext(1)
		.thenAwait(Duration.ofSeconds(4))
		.expectNext(2,3,4,5)
		.verifyComplete();
	}

}

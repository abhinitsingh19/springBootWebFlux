package com.springboot.reactive.test;

import java.time.Duration;
import java.util.function.Consumer;
import java.util.function.Supplier;

import org.junit.Test;

import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;
import reactor.test.scheduler.VirtualTimeScheduler;

public class VirtualizingTimeTest 
{
	@Test
	public void testWithoutVirtualTimeSchedular()
	{
		Flux<Long> flux = Flux.interval(Duration.ofSeconds(1))
				.take(3);
		StepVerifier.create(flux.log()).expectNext(0L, 1L, 2L)
		.verifyComplete();
	}
	
	@Test
	public void testWithVirtualTimeSchedular()
	{
		VirtualTimeScheduler.getOrSet();
		Flux<Long> flux = Flux.interval(Duration.ofSeconds(1))
				.take(3);
		StepVerifier.withVirtualTime(()->flux.log())
		.thenAwait(Duration.ofSeconds(3))
		.expectNext(0L, 1L, 2L)
		.verifyComplete();
	}
}

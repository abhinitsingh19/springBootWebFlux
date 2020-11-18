package com.springboot.reactive.test;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import reactor.core.publisher.Flux;

public class FluxTest 
{
	@Test
	public  void fluxTest()
	{
		Flux<String> flux = Flux.just("A","B","C");
		flux
		.log()
		.subscribe(f->System.out.println(f));
		
	}
	
	@Test
	public  void fluxTestWithError()
	{
		Flux<String> flux = Flux.just("A","B","C");
		flux
		.concatWith(Flux.error(new RuntimeException("error in events emitted from publisher")))
		.log()
		.subscribe(f->System.out.println(f),
				error->System.out.println(error));
		
	}
	
	@Test
	public  void fluxIterable()
	{
		List<String> eventList = Arrays.asList("A","B","C");
		Flux<String> flux = Flux.fromIterable(eventList);
		flux
		.log()
		.subscribe(f->System.out.println(f));
		
	}
	
	@Test
	public  void fluxRange()
	{
		Flux<Integer> flux = Flux.range(5, 6);
		flux
		.log()
		.subscribe(f->System.out.println(f));
		
	}
	
	@Test
	public  void fluxFromInterval() throws InterruptedException
	{
		Flux<Long> flux = Flux.interval(Duration.ofSeconds(1));
		flux
		.log()
		.subscribe(f->System.out.println(f));
		Thread.sleep(10000);
		
	}
	
	@Test
	public  void fluxWithTake() throws InterruptedException
	{
		Flux<Long> flux = Flux.interval(Duration.ofSeconds(1));
		flux
		.log()
		.take(3)
		.subscribe(f->System.out.println(f));
		Thread.sleep(10000);
		
	}
	
	@Test
	public  void fluxWithRequest() throws InterruptedException
	{
		Flux<Integer> flux = Flux.range(1,9);
		flux
		.log()
		.subscribe(f->System.out.println(f),err->{},()-> {},sub->sub.request(3));
		
	}
	
	@Test
	public  void fluxWithErrorHandling() throws InterruptedException
	{
		Flux<String> flux = Flux.just("A","B","C");
		flux.concatWith(Flux.error(new RuntimeException("error in events emitted from publisher")))
		.onErrorReturn("fallback value on exception")
		.log()
		.subscribe(f->System.out.println(f));
		
	}
}

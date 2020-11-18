package com.springboot.reactive.test;

import java.time.Duration;

import org.junit.Test;

import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;
import reactor.util.function.Tuple2;

public class combineReactiveStreamTest 
{
	@Test
	public void combineUsingMerge()
	{
		Flux<String> flux1 =  Flux.just("A","B","C");
		Flux<String> flux2 =  Flux.just("X","Y","Z");
		
		Flux<String> mergedFlux = Flux.merge(flux1,flux2);
		
		StepVerifier.create(mergedFlux).expectNext("A","B","C","X","Y","Z")
		.verifyComplete();
		
	}
	
	@Test
	public void combineUsingMergeWithDelay()
	{
		Flux<String> flux1 =  Flux.just("A","B","C").delayElements(Duration.ofSeconds(1));
		Flux<String> flux2 =  Flux.just("X","Y","Z").delayElements(Duration.ofSeconds(1));
		
		Flux<String> mergedFlux = Flux.merge(flux1,flux2);
		
		StepVerifier.create(mergedFlux.log())
		.expectNextCount(6)
//		.expectNext("A","B","C","X","Y","Z")
		.verifyComplete();
		
	}
	
	@Test
	public void combineWithConcat()
	{
		Flux<String> flux1 =  Flux.just("A","B","C").delayElements(Duration.ofSeconds(1));
		Flux<String> flux2 =  Flux.just("X","Y","Z").delayElements(Duration.ofSeconds(1));
		
		Flux<String> mergedFlux = Flux.concat(flux1,flux2);
		
		StepVerifier.create(mergedFlux.log())
//		.expectNextCount(6)
		.expectNext("A","B","C","X","Y","Z")
		.verifyComplete();
		
	}
	
	@Test
	public void combineWithZip()
	{
		Flux<String> flux1 =  Flux.just("A","B","C").delayElements(Duration.ofSeconds(1));
		Flux<String> flux2 =  Flux.just("X","Y","Z").delayElements(Duration.ofSeconds(1));
		
		Flux<Tuple2<String, String>> mergedFlux = Flux.zip(flux1,flux2);
		
		Flux<String> finalFlux = mergedFlux.map(it->it.getT1()+it.getT2());
		
		StepVerifier.create(finalFlux.log())
		.expectNext("AX","BY","CZ")
		.verifyComplete();
		
	}
	
	@Test
	public void combineWithZipWith()
	{
		Flux<String> flux1 =  Flux.just("A","B","C").delayElements(Duration.ofSeconds(1));
		Flux<String> flux2 =  Flux.just("X","Y","Z").delayElements(Duration.ofSeconds(1));
		
		Flux<Tuple2<String, String>> mergedFlux = flux1.zipWith(flux2);
		
		Flux<String> finalFlux = mergedFlux.map(it->it.getT1()+it.getT2());
		
		StepVerifier.create(finalFlux.log())
		.expectNext("AX","BY","CZ")
		.verifyComplete();
		
	}
}

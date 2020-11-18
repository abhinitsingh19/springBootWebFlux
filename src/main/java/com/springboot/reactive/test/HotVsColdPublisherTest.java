package com.springboot.reactive.test;

import java.time.Duration;

import org.junit.Test;

import reactor.core.Disposable;
import reactor.core.publisher.ConnectableFlux;
import reactor.core.publisher.Flux;

public class HotVsColdPublisherTest 
{
	@Test
	public void coldPublisherTest()
	{
		Flux<String> flux = Flux.just("A","B","C","D")
		.delayElements(Duration.ofSeconds(1));
		
		flux.subscribe(it->System.out.println("subcriber1 "+it));
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

		flux.subscribe(it->System.out.println("subcriber2 "+it));
		
		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
	
	@Test
	public void HotPublisherTest()
	{
		Flux<String> flux = Flux.just("A","B","C","D")
		.delayElements(Duration.ofSeconds(1));
		
		ConnectableFlux<String> connectableFlux = flux.publish();
		connectableFlux.connect();
		
		connectableFlux.subscribe(it->System.out.println("subcriber1 "+it));
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

		connectableFlux.subscribe(it->System.out.println("subcriber2 "+it));
		
		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}

}

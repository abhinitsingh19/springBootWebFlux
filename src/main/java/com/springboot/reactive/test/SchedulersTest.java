package com.springboot.reactive.test;

import java.time.Duration;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.junit.Test;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

public class SchedulersTest 
{
	
	@Test
	public void defaultThreadingModel()
	{
		Mono.just("1")
		.log()
		.map(data -> {
			log("map",data);
			return Integer.parseInt(data);
			}
		)
		.subscribe(data -> System.out.println(data));
	}
	
	@Test
	public void timeOperator()
	{
		Mono.just("1").delayElement(Duration.ofSeconds(1))
		.log()
		.map(data -> {
			log("map",data);
			return Integer.parseInt(data);
			}
		)
		.subscribe(data -> System.out.println(data));
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void log(String op, String data) 
	{
		System.out.println("Inside Operator "+op+" data being mapped "+data
				+" by Thread "+Thread.currentThread().getName());
	}
	
	@Test
	public void schedulerExample()
	{
		Mono.just("1")
		.log()
		.publishOn(Schedulers.fromExecutorService(executorService()))
		.map(data -> {
			log("map",data);
			return Integer.parseInt(data);
			}
		)
		.subscribe(data -> System.out.println(data));
	}

	private ExecutorService executorService() 
	{
		// TODO Auto-generated method stub
		return Executors.newFixedThreadPool(10);
	}
	
	@Test
	public void subscribeOnExample()
	{
		Mono.just("1")
		.log()
		.subscribeOn(Schedulers.parallel())
		.map(data -> {
			log("map1",data);
			return Integer.parseInt(data);
			}
		)
		.subscribeOn(Schedulers.elastic())
		.subscribeOn(Schedulers.single())
		.map(data -> 
		{
			log("map2",data);
			return data*data;
		}
				)
		
		.subscribe(data -> System.out.println(data));
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	@Test
	public void runOnOperator()
	{
		System.out.println("Number of CPU Cores "+Runtime.getRuntime().availableProcessors());
		Flux.range(1, 10)
		.parallel()
		.runOn(Schedulers.parallel())
		.map(data -> 
		{
			log("map",data);
			return data;
		}
				)
		
		.subscribe(data -> System.out.println(data));
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	private void log(String op, Integer data) 
	{
		System.out.println("Inside Operator "+op+" data being mapped "+data
				+" by Thread "+Thread.currentThread().getName());
		
	}

}

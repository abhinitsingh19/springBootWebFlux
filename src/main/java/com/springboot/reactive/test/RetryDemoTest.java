package com.springboot.reactive.test;

import java.time.Duration;

import org.junit.Test;

import reactor.core.publisher.Mono;
import reactor.util.retry.Retry;

public class RetryDemoTest 
{

	@Test
	public void fixedNumberOfRetries()
	{
		Mono.error(new RuntimeException("error occured")).log()
		.retry(3).subscribe();
	}
	
	@Test
	public void retryWithFixedDelay()
	{
		Mono.error(new RuntimeException("error occured")).log()
		.retryWhen(Retry.fixedDelay(3, Duration.ofSeconds(2)))
		.subscribe();
		
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void retryWithMinimumBackoff()
	{
		Mono.error(new RuntimeException("error occured")).log()
		.retryWhen(Retry.backoff(3, Duration.ofSeconds(2)))
		.subscribe();
		
		try {
			Thread.sleep(20000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
//	@Test
//	public void retryWithExponentialBackoff()
//	{
//		Mono.error(new RuntimeException("error occured")).log()
//		.retryExponentialBackoff(3, Duration.ofSeconds(1),Duration.ofSeconds(4),true)
//		.subscribe();
//		
//		try {
//			Thread.sleep(20000);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
}

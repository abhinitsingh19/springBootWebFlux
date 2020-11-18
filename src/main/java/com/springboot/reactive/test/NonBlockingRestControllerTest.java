package com.springboot.reactive.test;

import java.time.Duration;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;

import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

@RunWith(SpringRunner.class) 
@WebFluxTest
public class NonBlockingRestControllerTest 
{
	@Autowired
	private WebTestClient webTestClient;
	
	@Test
	public  void nonBlockingRestApiTest1()
	{
		webTestClient.get()
		.uri("/flux")
		.accept(MediaType.APPLICATION_JSON_UTF8)
		.exchange()
		.expectStatus().is2xxSuccessful()
		.expectBodyList(Integer.class)
		.hasSize(5);
		
	}
	
	@Test
	public  void nonBlockingRestApiTest2()
	{
		Flux<Integer> responseBody = webTestClient.get()
		.uri("/flux")
		.accept(MediaType.APPLICATION_JSON_UTF8)
		.exchange()
		.expectStatus().is2xxSuccessful()
		.returnResult(Integer.class)
		.getResponseBody();
		
		StepVerifier.create(responseBody)
		.expectNext(1, 2, 3, 4, 5)
		.verifyComplete();
		
	}
	
	@Test
	public  void nonBlockingRestApiTest3()
	{
		webTestClient = webTestClient.mutate()
		.responseTimeout(Duration.ofSeconds(6))
		.build();
		
		webTestClient.get()
		.uri("/flux")
		.accept(MediaType.APPLICATION_JSON_UTF8)
		.exchange()
		.expectStatus().is2xxSuccessful();
		
	}
}

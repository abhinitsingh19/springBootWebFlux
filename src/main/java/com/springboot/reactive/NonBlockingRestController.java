package com.springboot.reactive;

import java.time.Duration;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Flux;

@RestController
public class NonBlockingRestController 
{
	
	@GetMapping("/flux")
	public Flux<Integer> getNumbers()
	{ 
		Flux<Integer> flux = Flux.just(1,2,3,4,5)
				.delayElements(Duration.ofSeconds(1))
				.log();
		return flux;
		
	}
	
//	@GetMapping("/fluxStream",produces= [Mediatype.A])
	@GetMapping(value="/fluxStream",produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
	public Flux<Integer> getNumberStream()
	{ 
		Flux<Integer> flux = Flux.just(1,2,3,4,5)
				.delayElements(Duration.ofSeconds(1))
				.log();
		return flux;
		
	}

}

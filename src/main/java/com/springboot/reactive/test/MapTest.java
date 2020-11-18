package com.springboot.reactive.test;

import org.junit.Test;

import reactor.core.publisher.Flux;

public class MapTest 
{

	@Test
	public void map1()
	{
		Flux.range(1, 5)
		.log()
		.map(data->data*data)
		.subscribe(data->System.out.println(data));
	}
	
	@Test
	public void map2()
	{
		Flux.range(1, 5)
		.log()
		.map(data->data*data)
		.filter(data->data%2==0)
		.subscribe(data->System.out.println(data));
	}
}

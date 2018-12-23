package com.cj.autoconfig.service;

import java.util.stream.Stream;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Profile("Java8")
@Service
public class Java8CalculateService implements CalculateService {

	@Override
	public Integer sum(Integer... values) {
		
		Integer sum=Stream.of(values).reduce(0, Integer::sum);
		System.out.println("Java8 sum result:"+sum);
		return sum;
	}
}
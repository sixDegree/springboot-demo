package com.cj.autoconfig.service;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Profile("Java7")
@Service
public class Java7CalculateService implements CalculateService {

	@Override
	public Integer sum(Integer... values) {
		Integer sum=0;
		for(Integer item:values) {
			sum+=item;
		}
		System.out.println("Java7 sum result:"+sum);
		return sum;
	}
}

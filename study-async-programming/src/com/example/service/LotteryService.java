package com.example.service;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class LotteryService {
	public List<Integer> draw(int max, int size){
		try {
			TimeUnit.SECONDS.sleep(3);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ThreadLocalRandom.current().ints(1, max)
				.distinct()
				.limit(size)
				.sorted()
				.boxed()
				.collect(Collectors.toList());
	}

}

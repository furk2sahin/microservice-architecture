package com.example.lottery.service.business;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Service;

import com.example.lottery.service.LotteryService;

@Service
@RefreshScope // konfigurasyonlar dısardan alındığı için konfigurasyon değişikliğinde otomatik refresh edecek.
public class StandardLotteryService implements LotteryService {

	@Value("${lottery.max}") // SpEL
	private int lotteryMax;
	@Value("${lottery.size}")
	private int lotterySize;
	
	@Override
	public List<Integer> draw() {
		return ThreadLocalRandom.current()
				.ints(1, lotteryMax)
				.distinct()
				.limit(lotterySize)
				.sorted()
				.boxed()
				.collect(Collectors.toList());
	}

}

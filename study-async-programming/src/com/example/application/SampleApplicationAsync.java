package com.example.application;

import java.util.concurrent.TimeUnit;

import com.example.service.LotteryAsyncService;

public class SampleApplicationAsync {
	public static void main(String[] args) throws InterruptedException {
		var lotteryAsyncService = new LotteryAsyncService();
		System.err.println("Just started...");
		lotteryAsyncService.asyncDraw(60, 6)
				.thenAccept(numbers -> System.err.println(Thread.currentThread().getName() + " " + numbers)); // sonuç üretildiðinde tetiklenir
		for(var i = 0; i < 10; i++) {
			System.err.println(Thread.currentThread().getName() + " - i:" + i);
		}
		TimeUnit.SECONDS.sleep(10);
	}
}

package com.example.application;

import com.example.service.LotteryService;

public class SampleApplication {
	public static void main(String[] args) {
		var lotteryService = new LotteryService();
		System.err.println("Just started...");
		var numbers = lotteryService.draw(60, 6);
		System.err.println(numbers);

	}
}

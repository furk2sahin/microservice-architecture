package com.example.service;

import java.util.Comparator;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import dto.Ticker;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@Service
public class BinanceReactiveClient {

	@Value("${binance.rest.http.url.base}")
	private String url;

	private WebClient webClient = WebClient.builder().baseUrl(url).build();
	
	private final static List<String> SYMBOLS = List.of(
			"BTCUSDT", "LTCBTC", "ETHBTC", "BNBBTC", "NEOBTC", "EOSETH"
	);
	
	@PostConstruct
	public void init() {
		webClient = WebClient.builder().baseUrl(url).build();
	}
	
	@Scheduled(fixedRate = 10_000)
	public void callTicketPriceService() {
		Flux.fromIterable(SYMBOLS)
			.parallel()
			.runOn(Schedulers.boundedElastic())
			.flatMap(this::getTicker)
			.ordered(Comparator.comparing(Ticker::getSymbol))
			.subscribe(System.err::println);
	}
	
	public Mono<Ticker> getTicker(String symbol){
		return webClient.get()
				.uri(uriBuilder -> uriBuilder.path("/ticker/price")
						.queryParam("symbol", symbol).build())
				.retrieve()
				.bodyToMono(Ticker.class);
	}
}

package com.zensar.config;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

import com.zensar.entity.StockPrice;
import com.zensar.entity.StockQueue;
import com.zensar.scheduler.StockPriceWebSocketHandler;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {
	private Random random;

	@Override
	public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
		Queue<StockPrice> q = new LinkedList<>();
		Queue<StockPrice> r = new LinkedList<>();
		Queue<StockPrice> t = new LinkedList<>();
		StockQueue stockQueuest = new StockQueue(q, r, t);
		registry.addHandler(new StockPriceWebSocketHandler(stockQueuest), "/stockWebSocket").setAllowedOrigins("*");
	}

	@Bean
	public StockQueue stockQueue() {
		return new StockQueue();
	}
}
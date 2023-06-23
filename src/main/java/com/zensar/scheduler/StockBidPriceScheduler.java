package com.zensar.scheduler;

import java.time.LocalDateTime;
import java.util.Queue;
import java.util.Random;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.zensar.entity.OrdersTable;
import com.zensar.entity.StockPrice;
import com.zensar.entity.StockQueue;

@Component
public class StockBidPriceScheduler {
	private final StockQueue stockQueues;
	private final OrdersTable ordersTable;

	public StockBidPriceScheduler(StockQueue stockQueues, OrdersTable ordersTable) {
		this.stockQueues = stockQueues;
		this.ordersTable = ordersTable;
	}

	@Scheduled(fixedDelay = 3000)
	public void generateAppleBidPrice() {
		Random random = new Random();
		int askPrice = stockQueues.getAppleQueue().peek().getAskPrice();
		int bidPrice = random.nextInt(askPrice - 29) + 30;

		Queue<StockPrice> appleStock = stockQueues.getAppleQueue();
		appleStock.peek().setBidPrice(bidPrice);
		stockQueues.setAppleQueue(appleStock);

		if (bidPrice == askPrice) {
			StockPrice appleProduct = appleStock.peek();
			LocalDateTime localDateTime = LocalDateTime.now();
			OrdersTable order = new OrdersTable(appleProduct.getStockName(), appleProduct.getAskPrice(),
					appleProduct.getBidPrice(), localDateTime);
		}
	}
//    @Scheduled(fixedDelay = 3000)
//    public void generateIBMBidPrice() {
//        Random random = new Random();
//        int askPrice = stockQueues.getIbmQueue().peek().getAskPrice();
//        int bidPrice = random.nextInt(askPrice - 29) + 30;
//
//        Queue<StockPrice> ibmStock = stockQueues.getIbmQueue();
//        ibmStock.peek().setBidPrice(bidPrice);
//        stockQueues.setAppleQueue(ibmStock);
//
//        if (bidPrice == askPrice) {
//            StockPrice ibmProduct = ibmStock.peek();
//            LocalDateTime localDateTime = LocalDateTime.now();
//            OrdersTable order = new OrdersTable(ibmProduct.getStockName(), ibmProduct.getAskPrice(), ibmProduct.getBidPrice(), localDateTime);
//        }
//    }
//    @Scheduled(fixedDelay = 3000)
//    public void generateZensarBidPrice() {
//        Random random = new Random();
//        int askPrice = stockQueues.getZensarQueue().peek().getAskPrice();
//        int bidPrice = random.nextInt(askPrice - 29) + 30;
//
//        Queue<StockPrice> zensarStock = stockQueues.getZensarQueue();
//        zensarStock.peek().setBidPrice(bidPrice);
//        stockQueues.setAppleQueue(zensarStock);
//
//        if (bidPrice == askPrice) {
//            StockPrice zensarProduct = zensarStock.peek();
//            LocalDateTime localDateTime = LocalDateTime.now();
//            OrdersTable order = new OrdersTable(zensarProduct.getStockName(), zensarProduct.getAskPrice(), zensarProduct.getBidPrice(), localDateTime);
//        }
//    }
}

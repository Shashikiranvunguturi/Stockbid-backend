package com.zensar.scheduler;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.zensar.entity.StockPrice;
import com.zensar.entity.StockQueue;

@Component
public class StockAskPriceScheduler {
    private final StockQueue stockQueues;

    public StockAskPriceScheduler(StockQueue stockQueues) {
        this.stockQueues = stockQueues;
    }

    @Scheduled(fixedDelay = 3000)
    public void generateAppleAskPrice() {
        Random random = new Random();
        int askPrice = random.nextInt(31) + 30;

        Queue<StockPrice> appleStock = new LinkedList<>();
        appleStock.add(new StockPrice(1, "Apple", askPrice, 0));
        stockQueues.setAppleQueue(appleStock);
    }
//
//    @Scheduled(fixedDelay = 5000)
//    public void generateIbmAskPrice() {
//        Random random = new Random();
//        int askPrice = random.nextInt(31) + 30;
//
//        Queue<StockPrice> ibmStock = new LinkedList<>();
//        ibmStock.add(new StockPrice(2, "IBM", askPrice, 0));
//        stockQueues.setIbmQueue(ibmStock);
//    }
//
//    @Scheduled(fixedDelay = 7000)
//    public void generateZensarAskPrice() {
//        Random random = new Random();
//        int askPrice = random.nextInt(31) + 30;
//
//        Queue<StockPrice> zensarStock = new LinkedList<>();
//        zensarStock.add(new StockPrice(3, "Zensar", askPrice, 0));
//        stockQueues.setZensarQueue(zensarStock);
//    }
}

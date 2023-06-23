package com.zensar.entity;

import java.util.LinkedList;
import java.util.Queue;

public class StockQueue {
    private Queue<StockPrice> appleQueue = new LinkedList<>();
    private Queue<StockPrice> ibmQueue = new LinkedList<>();
    private Queue<StockPrice> zensarQueue = new LinkedList<>();

    public StockQueue() {
        super();
    }

    public StockQueue(Queue<StockPrice> appleQueue, Queue<StockPrice> ibmQueue, Queue<StockPrice> zensarQueue) {
        super();
        this.appleQueue = appleQueue;
        this.ibmQueue = ibmQueue;
        this.zensarQueue = zensarQueue;
    }

    public Queue<StockPrice> getAppleQueue() {
        return appleQueue;
    }

    public void setAppleQueue(Queue<StockPrice> appleQueue) {
        this.appleQueue = appleQueue;
    }

    public Queue<StockPrice> getIbmQueue() {
        return ibmQueue;
    }

    public void setIbmQueue(Queue<StockPrice> ibmQueue) {
        this.ibmQueue = ibmQueue;
    }

    public Queue<StockPrice> getZensarQueue() {
        return zensarQueue;
    }

    public void setZensarQueue(Queue<StockPrice> zensarQueue) {
        this.zensarQueue = zensarQueue;
    }

    public Queue<StockPrice> getStockQueue(String stockName) {
        switch (stockName.toLowerCase()) {
            case "apple":
                return this.getAppleQueue();
            case "ibm":
                return this.getIbmQueue();
            case "zensar":
                return this.getZensarQueue();
            default:
                throw new IllegalArgumentException("Invalid stock name: " + stockName);
        }
    }

    public void setStockQueue(String stockName, Queue<StockPrice> stockQueue) {
        switch (stockName.toLowerCase()) {
            case "apple":
                this.setAppleQueue(stockQueue);
                break;
            case "ibm":
                this.setIbmQueue(stockQueue);
                break;
            case "zensar":
                this.setZensarQueue(stockQueue);
                break;
            default:
                throw new IllegalArgumentException("Invalid stock name: " + stockName);
        }
    }
}

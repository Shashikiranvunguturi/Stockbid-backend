package com.zensar.entity;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.beans.factory.annotation.Autowired;

import com.zensar.dto.StockPriceDto;

import lombok.Data;

@Entity
@Data
public class StockPrice {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Autowired
	private StockQueue stockqueue;
	private int stockId;
	private String stockName;
	private int askPrice;
	private int bidPrice;
	public StockPrice() {
		super();
	}
	public StockPrice(int stockId, String stockName, int askPrice, int bidPrice) {
		super();
		this.stockId = stockId;
		this.stockName = stockName;
		this.askPrice = askPrice;
		this.bidPrice = bidPrice;
	}
	public int getStockId() {
		return stockId;
	}
	public void setStockId(int stockId) {
		this.stockId = stockId;
	}
	public String getStockName() {
		return stockName;
	}
	public void setStockName(String stockName) {
		this.stockName = stockName;
	}
	public int getAskPrice() {
		return askPrice;
	}
	public void setAskPrice(int askPrice) {
		this.askPrice = askPrice;
	}
	public int getBidPrice() {
		return bidPrice;
	}
	public void setBidPrice(int bidPrice) {
		this.bidPrice = bidPrice;
	}
	public List<StockPriceDto> getLastThreeBids(String stockName) {
        List<StockPriceDto> lastThreeBids = new ArrayList<>();
        Queue<StockPrice> stockQueue=new LinkedList<>();
        if(stockName=="Apple")
        {
        	stockQueue=stockqueue.getAppleQueue();
        }
        else if(stockName=="Ibm")
        {
        	stockQueue=stockqueue.getIbmQueue();
        }
        else if(stockName=="Zensar")
        {
        	stockQueue=stockqueue.getZensarQueue();
        }
        int count = 0;
        Iterator<StockPrice> iterator = ((LinkedList<StockPrice>) stockQueue).descendingIterator();
        while (iterator.hasNext() && count < 3) {
            StockPrice stock = iterator.next();
            lastThreeBids.add(new StockPriceDto(stock.getStockId(),stock.getStockName(), stock.getBidPrice(),stock.getAskPrice()));
            count++;
        }

        return lastThreeBids;
    }
}

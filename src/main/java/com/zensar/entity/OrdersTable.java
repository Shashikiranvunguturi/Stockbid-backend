package com.zensar.entity;

import java.time.LocalDateTime;

import javax.persistence.Entity;

import org.springframework.stereotype.Component;

import lombok.Data;

@Entity
@Data
@Component
public class OrdersTable {
	
	private String stockName;
	private int bidPrice;
	private int askPrice;
	private LocalDateTime localDateTime;
	
	public OrdersTable() {
		super();
	}

	public OrdersTable(String stockName, int bidPrice, int askPrice, LocalDateTime localDateTime) {
		super();
		this.stockName = stockName;
		this.bidPrice = bidPrice;
		this.askPrice = askPrice;
		this.localDateTime = localDateTime;
	}

	public String getStockName() {
		return stockName;
	}

	public void setStockName(String stockName) {
		this.stockName = stockName;
	}

	public int getBidPrice() {
		return bidPrice;
	}

	public void setBidPrice(int bidPrice) {
		this.bidPrice = bidPrice;
	}

	public int getAskPrice() {
		return askPrice;
	}

	public void setAskPrice(int askPrice) {
		this.askPrice = askPrice;
	}

	public LocalDateTime getLocalDateTime() {
		return localDateTime;
	}

	public void setLocalDateTime(LocalDateTime localDateTime) {
		this.localDateTime = localDateTime;
	}

	@Override
	public String toString() {
		return "OrdersTable [stockName=" + stockName + ", bidPrice=" + bidPrice + ", askPrice=" + askPrice
				+ ", localDateTime=" + localDateTime + "]";
	}
	
	
	

}

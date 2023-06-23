package com.zensar.dto;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class StockPriceDto {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int stockId;
	private String stockName;
	private int askPrice;
	private int bidPrice;
	public StockPriceDto() {
		super();
	}
	public StockPriceDto(int stockId, String stockName, int askPrice, int bidPrice) {
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
	

}

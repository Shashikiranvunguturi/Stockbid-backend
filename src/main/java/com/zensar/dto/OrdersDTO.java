package com.zensar.dto;

import java.time.LocalDateTime;
import java.util.List;

public class OrdersDTO {
	
	private int sNo;
	private String stockName;
	private List<Integer> bidPrice;
	private List<Integer> askPrice;
	private LocalDateTime localDateTime;
	public OrdersDTO(String stockName, List<Integer> bidPrice, List<Integer> askPrice, LocalDateTime localDateTime) {
		super();
		this.stockName = stockName;
		this.bidPrice = bidPrice;
		this.askPrice = askPrice;
		this.localDateTime = localDateTime;
	}
	public OrdersDTO(List<Integer> bidPrice, List<Integer> askPrice) {
		super();
		this.bidPrice = bidPrice;
		this.askPrice = askPrice;
	}
	public OrdersDTO(String stockName, List<Integer> bidPrice, LocalDateTime localDateTime) {
		super();
		this.stockName = stockName;
		this.bidPrice = bidPrice;
		this.localDateTime = localDateTime;
	}
	public OrdersDTO(String stockName, LocalDateTime localDateTime,List<Integer> askPrice) {
		super();
		this.stockName = stockName;
		this.askPrice = askPrice;
		this.localDateTime = localDateTime;
	}

	public OrdersDTO() {
		super();
	}

	public OrdersDTO(int sNo, String stockName, List<Integer> bidPrice, List<Integer> askPrice, LocalDateTime localDateTime) {
		super();
		this.sNo = sNo;
		this.stockName = stockName;
		this.bidPrice = bidPrice;
		this.askPrice = askPrice;
		this.localDateTime = localDateTime;
	}

	
	
	public OrdersDTO(String stockName, List<Integer> bidPrice, List<Integer> askPrice) {
		super();
		this.stockName = stockName;
		this.bidPrice = bidPrice;
		this.askPrice = askPrice;
	}
	public int getsNo() {
		return sNo;
	}

	public void setsNo(int sNo) {
		this.sNo = sNo;
	}

	public String getStockName() {
		return stockName;
	}

	public void setStockName(String stockName) {
		this.stockName = stockName;
	}

	public List<Integer> getBidPrice() {
		return bidPrice;
	}

	public void setBidPrice(List<Integer> bidPrice) {
		this.bidPrice = bidPrice;
	}

	public List<Integer> getAskPrice() {
		return askPrice;
	}

	public void setAskPrice(List<Integer> askPrice) {
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
		return "OrdersDTO [sNo=" + sNo + ", stockName=" + stockName + ", bidPrice=" + bidPrice + ", askPrice="
				+ askPrice + ", localDateTime=" + localDateTime + "]";
	}
	
	

}

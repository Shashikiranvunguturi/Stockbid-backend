package com.zensar.scheduler;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;

import com.zensar.entity.StockPrice;
import com.zensar.entity.StockQueue;
import com.zensar.entity.OrdersTable;

@Component
@ComponentScan("com.zensar.stock")
public class StockPriceWebSocketHandler implements WebSocketHandler {
	private WebSocketSession session;
	private Timer timer;
	private final StockQueue stockQueuest;
	private List<OrdersTable> orderTableList;

	public StockPriceWebSocketHandler(StockQueue stockQueues) {
		this.stockQueuest = stockQueues;
		this.orderTableList = new ArrayList<>();
	}

	private void handleTextMessages(String message1, String message2, String message3) {
		timer = new Timer();
		timer.scheduleAtFixedRate(new TimerTask() {
			@Override
			public void run() {
				String stockName = message1;
				String stockName2 = message2;
				String stockName3 = message3;
				if (session.isOpen()) {
					try {
						Queue<StockPrice> stockQueue = generateAppleStockQueue(stockName);
						Queue<StockPrice> stockQueue2 = generateIBMStockQueue(stockName2);
						Queue<StockPrice> stockQueue3 = generateZensarStockQueue(stockName3);
						if (stockQueue != null && stockQueue2 != null && stockQueue3 != null) {
							compareBidAskPrice(stockQueue);
							compareBidAskPrice(stockQueue2);
							compareBidAskPrice(stockQueue3);
							JSONArray jsonArray = new JSONArray();
							jsonArray.put(stockQueue);
							jsonArray.put(stockQueue2);
							jsonArray.put(stockQueue3);

							// Add orderTableList to the jsonArray
							JSONArray orderTableArray = new JSONArray();
							for (OrdersTable order : orderTableList) {
								JSONObject orderObject = new JSONObject();
								orderObject.put("stockName", order.getStockName());
								orderObject.put("bidPrice", order.getBidPrice());
								orderObject.put("askPrice", order.getAskPrice());
								orderObject.put("dateTime", order.getLocalDateTime().toString());
								orderTableArray.put(orderObject);
							}
							jsonArray.put(orderTableArray);

							session.sendMessage(new TextMessage(jsonArray.toString()));
						} else {
							session.sendMessage(new TextMessage("Invalid stock name: " + stockName));
						}
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}, 0, 4000);
	}

	private Queue<StockPrice> generateAppleStockQueue(String stockName) {
		Random random = new Random();
		int askPrice = random.nextInt(31) + 30;
		int bidPrice = random.nextInt(askPrice - 29) + 30;
		int askPrice2 = random.nextInt(31) + 30;
		int bidPrice2 = random.nextInt(askPrice - 29) + 30;
		int askPrice3 = random.nextInt(31) + 30;
		int bidPrice3 = random.nextInt(askPrice - 29) + 30;

		Queue<StockPrice> stockQueue = new LinkedList<>();
		stockQueue.add(new StockPrice(1, stockName, askPrice, bidPrice));
		stockQueue.add(new StockPrice(1, stockName, askPrice2, bidPrice2));
		stockQueue.add(new StockPrice(1, stockName, askPrice3, bidPrice3));
		stockQueuest.setAppleQueue(stockQueue);
		return stockQueue;
	}

	private Queue<StockPrice> generateIBMStockQueue(String stockName) {
		Random random = new Random();
		int askPrice4 = random.nextInt(31) + 30;
		int bidPrice4 = random.nextInt(askPrice4 - 29) + 30;
		int askPrice5 = random.nextInt(31) + 30;
		int bidPrice5 = random.nextInt(askPrice4 - 29) + 30;
		int askPrice6 = random.nextInt(31) + 30;
		int bidPrice6 = random.nextInt(askPrice4 - 29) + 30;

		Queue<StockPrice> stockQueue = new LinkedList<>();
		stockQueue.add(new StockPrice(2, stockName, askPrice4, bidPrice4));
		stockQueue.add(new StockPrice(2, stockName, askPrice5, bidPrice5));
		stockQueue.add(new StockPrice(2, stockName, askPrice6, bidPrice6));
		stockQueuest.setIbmQueue(stockQueue);
		return stockQueue;
	}

	private Queue<StockPrice> generateZensarStockQueue(String stockName) {
		Random random = new Random();
		int askPrice7 = random.nextInt(31) + 30;
		int bidPrice7 = random.nextInt(askPrice7 - 29) + 30;
		int askPrice8 = random.nextInt(31) + 30;
		int bidPrice8 = random.nextInt(askPrice7 - 29) + 30;
		int askPrice9 = random.nextInt(31) + 30;
		int bidPrice9 = random.nextInt(askPrice7 - 29) + 30;

		Queue<StockPrice> stockQueue = new LinkedList<>();
		stockQueue.add(new StockPrice(3, stockName, askPrice7, bidPrice7));
		stockQueue.add(new StockPrice(3, stockName, askPrice8, bidPrice8));
		stockQueue.add(new StockPrice(3, stockName, askPrice9, bidPrice9));
		stockQueuest.setZensarQueue(stockQueue);
		return stockQueue;
	}

	private void compareBidAskPrice(Queue<StockPrice> stockQueue) {
		StockPrice stock = stockQueue.peek();
		int askPrice = stock.getAskPrice();
		int bidPrice = stock.getBidPrice();
		if (askPrice == bidPrice) {
			OrdersTable orderTable = new OrdersTable(stock.getStockName(), bidPrice, askPrice, LocalDateTime.now());
			orderTableList.add(orderTable);
//            for (OrdersTable order : orderTableList) {
//                System.out.println(order);
//            }
			System.out.println(stock.getStockName() + ":" + " Bid Price: " + bidPrice + " Ask Price: " + askPrice
					+ " Date & Time: " + LocalDateTime.now());

		}
	}

	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		this.session = session;
		handleTextMessages("apple", "ibm", "zensar");
	}

	@Override
	public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
		// TODO Auto-generated method stub
	}

	@Override
	public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
		// TODO Auto-generated method stub
	}

	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
		if (timer != null) {
			timer.cancel();
			timer = null;
		}
	}

	@Override
	public boolean supportsPartialMessages() {
		return false;
	}
}

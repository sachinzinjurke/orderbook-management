package com.cs.orderbook.facade;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cs.constants.OrderBookStatusType;
import com.cs.core.Execution;
import com.cs.core.Order;
import com.cs.orderbook.service.OrderBookService;
import com.cs.orderbook.stats.OrderBookSummaryStats;
import com.cs.orderbook.stats.OrderBookValidityStats;

public class OrderBookManager {
	
	private static final Logger logger = LoggerFactory.getLogger(OrderBookManager.class.getName());
	
	private OrderBookService orderBookService;

	public void createOrderBook(Integer instrumentId) {
		
		orderBookService.createOrderBook(instrumentId);
	}
	public void addOrderToOrderBook(Integer instrumentId, Order order) {
		orderBookService.addOrderToOrderBook(instrumentId, order);
	}
	public void executeOrderBook(Integer instrumentId, Execution execution) {
		orderBookService.addOrderBookForExecution(instrumentId, execution);
	}
	public void changeOrderBookStatus(Integer instrumentId,OrderBookStatusType orderBookStatusType) {
		orderBookService.changeOrderBookStatus(instrumentId, orderBookStatusType);
	}

	public OrderBookSummaryStats getOrderBookStats(Integer instrumentId) {
		return orderBookService.getOrderBookStats(instrumentId);
	}
	
	public OrderBookValidityStats getOrderBookValidityStats(Integer instrumentId) {
		return orderBookService.getOrderBookValidityStats(instrumentId);
	}
	public OrderBookService getOrderBookService() {
		return orderBookService;
	}

	public void setOrderBookService(OrderBookService orderBookService) {
		this.orderBookService = orderBookService;
	}
	
}

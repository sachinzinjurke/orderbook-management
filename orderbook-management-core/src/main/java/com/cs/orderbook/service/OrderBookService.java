package com.cs.orderbook.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cs.constants.OrderBookStatusType;
import com.cs.core.Execution;
import com.cs.core.Order;
import com.cs.orderbook.dao.OrderBookDaoImpl;
import com.cs.orderbook.helper.OrderBookHelper;
import com.cs.orderbook.stats.OrderBookSummaryStats;
import com.cs.orderbook.stats.OrderBookValidityStats;

public class OrderBookService {
	
	private static final Logger logger = LoggerFactory.getLogger(OrderBookService.class.getName());
	
	private OrderBookDaoImpl orderBookDao;
	private OrderBookHelper orderBookHelper;
	
	public void createOrderBook(Integer instrumentId) {
		orderBookDao.createOrderBook(instrumentId);
	}
	
	public void addOrderToOrderBook(Integer instrumentId, Order order) {
		orderBookDao.addOrderToOrderBook(instrumentId, order);
	}
	
	public void changeOrderBookStatus(Integer instrumentId,OrderBookStatusType orderBookStatusType) {
		orderBookDao.changeOrderBookStatus(instrumentId, orderBookStatusType);
	}

	public void addOrderBookForExecution(Integer instrumentId, Execution execution) {
		orderBookHelper.addOrderBookForExecution(instrumentId, execution);
	}

	public OrderBookSummaryStats getOrderBookStats(Integer instrumentId) {
		return orderBookHelper.getOrderBookStats(instrumentId);
	}
	
	public OrderBookDaoImpl getOrderBookDao() {
		return orderBookDao;
	}

	public void setOrderBookDao(OrderBookDaoImpl orderBookDao) {
		this.orderBookDao = orderBookDao;
	}

	public OrderBookHelper getOrderBookHelper() {
		return orderBookHelper;
	}

	public void setOrderBookHelper(OrderBookHelper orderBookHelper) {
		this.orderBookHelper = orderBookHelper;
	}

	public OrderBookValidityStats getOrderBookValidityStats(Integer instrumentId) {
		return orderBookHelper.getOrderBookValidityStats(instrumentId);
	}
	

}

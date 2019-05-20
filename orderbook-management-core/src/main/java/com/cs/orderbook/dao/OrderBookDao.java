package com.cs.orderbook.dao;

import com.cs.core.Order;

public interface OrderBookDao {
	

	public void createOrderBook(Integer instrumentId);
	public void addOrderToOrderBook(Integer instrumentId, Order order);

}

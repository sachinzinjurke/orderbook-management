package com.cs.core;

import java.util.Date;

import com.cs.constants.OrderType;

public class MarketOrder extends Order{
	private OrderType orderType;
	public MarketOrder(Integer orderId, Integer quantity, Date entryDate, Integer instrumentId, Double price) {
		super(orderId, quantity, entryDate, instrumentId, price);
		this.orderType=OrderType.MARKET;
	}
	@Override
	public String toString() {
		return "MarketOrder [orderType=" + orderType + ", OrderId=" + getOrderId() + ", Quantity="
				+ getQuantity() + ", EntryDate=" + getEntryDate() + ", InstrumentI=" + getInstrumentId()
				+ ", Price=" + getPrice() + ", IsValidOrder=" + getIsValidOrder() + "]";
	}
	
	public OrderType getOrderType() {
		return this.orderType;
	}
}

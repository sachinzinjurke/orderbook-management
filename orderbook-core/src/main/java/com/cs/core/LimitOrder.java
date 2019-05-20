package com.cs.core;

import java.util.Date;

import com.cs.constants.OrderType;

public class LimitOrder extends Order{
	
	private OrderType orderType;

	public LimitOrder(Integer orderId, Integer quantity, Date entryDate, Integer instrumentId, Double price) {
		super(orderId, quantity, entryDate, instrumentId, price);
		this.orderType=OrderType.LIMIT;
	}

	@Override
	public String toString() {
		return "LimitOrder [orderType=" + orderType + ", OrderId=" + getOrderId() + ", Quantity="
				+ getQuantity() + ", EntryDate=" + getEntryDate() + ", InstrumentI=" + getInstrumentId()
				+ ", Price=" + getPrice() + ", IsValidOrder=" + getIsValidOrder() + "]";
	}

	public OrderType getOrderType() {
		return this.orderType;
	}
	
}

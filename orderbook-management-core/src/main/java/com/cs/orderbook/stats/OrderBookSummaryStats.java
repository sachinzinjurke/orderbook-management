package com.cs.orderbook.stats;

import java.util.Date;

public class OrderBookSummaryStats {
	
	private Long totalOrderBookQuantityCount;
	private Integer smallestOrderInBook;
	private Integer biggestOrderInBook;
	private Long totalOrderCount;
	private Date earliestOrderEntry;
	private Date lastOrderEntry;
	
	public Long getTotalOrderBookQuantityCount() {
		return totalOrderBookQuantityCount;
	}
	public void setTotalOrderBookQuantityCount(Long totalOrderBookQuantityCount) {
		this.totalOrderBookQuantityCount = totalOrderBookQuantityCount;
	}
	public Long getTotalOrderCount() {
		return totalOrderCount;
	}
	public void setTotalOrderCount(Long totalOrderCount) {
		this.totalOrderCount = totalOrderCount;
	}
	public Integer getSmallestOrderInBook() {
		return smallestOrderInBook;
	}
	public void setSmallestOrderInBook(Integer smallestOrderInBook) {
		this.smallestOrderInBook = smallestOrderInBook;
	}
	public Integer getBiggestOrderInBook() {
		return biggestOrderInBook;
	}
	public void setBiggestOrderInBook(Integer biggestOrderInBook) {
		this.biggestOrderInBook = biggestOrderInBook;
	}
	
	public Date getEarliestOrderEntry() {
		return earliestOrderEntry;
	}
	public void setEarliestOrderEntry(Date earliestOrderEntry) {
		this.earliestOrderEntry = earliestOrderEntry;
	}
	public Date getLastOrderEntry() {
		return lastOrderEntry;
	}
	public void setLastOrderEntry(Date lastOrderEntry) {
		this.lastOrderEntry = lastOrderEntry;
	}
	@Override
	public String toString() {
		return "OrderBookSummaryStats [totalOrderBookQuantityCount=" + totalOrderBookQuantityCount
				+ ", smallestOrderInBook=" + smallestOrderInBook + ", biggestOrderInBook=" + biggestOrderInBook
				+ ", totalOrderCount=" + totalOrderCount + ", earliestOrderEntry=" + earliestOrderEntry
				+ ", lastOrderEntry=" + lastOrderEntry + "]";
	}
	
	
	
	
}

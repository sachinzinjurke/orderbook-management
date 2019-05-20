package com.cs.orderbook.helper;

import java.util.Date;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cs.constants.OrderBookStatusType;
import com.cs.constants.OrderType;
import com.cs.core.Execution;
import com.cs.core.ExecutionContext;
import com.cs.core.Instrument;
import com.cs.core.Order;
import com.cs.orderbook.cache.InstrumentCache;
import com.cs.orderbook.constants.ExecutionConstants;
import com.cs.orderbook.rules.OrderBookRuleCollection;
import com.cs.orderbook.stats.OrderBookSummaryStats;
import com.cs.orderbook.stats.OrderBookValidityStats;

public class OrderBookHelper {
	
	private static final Logger logger = LoggerFactory.getLogger(OrderBookHelper.class.getName());

	private OrderBookRuleCollection orderBookRuleCollection;
	
	public void addOrderBookForExecution(Integer instrumentId, Execution execution) {
		if(InstrumentCache.INSTRUMENT_CACHE_MAP.containsKey(instrumentId)) {
			Instrument instrument = InstrumentCache.INSTRUMENT_CACHE_MAP.get(instrumentId);
			if(instrument.getOrdreBook().getOrderBookStatusType()==OrderBookStatusType.CLOSE) {
				ExecutionContext executionContext = new ExecutionContext();
				executionContext.setExecution(execution);
				instrument.getOrdreBook().addExecution(execution);
				executionContext.setInstrument(instrument);
				processOrderBook(executionContext);
			}else {
				logger.info("Orderbook is not in CLOSE state for excution");
			}
			
		}else {
			logger.info("Instrument does not exits in cache");
		}
	}

	public OrderBookSummaryStats getOrderBookStats(int instrumentId) {
		logger.info("Fetching order book summary stats for instrument id {}", instrumentId);
		Instrument instrument = InstrumentCache.INSTRUMENT_CACHE_MAP.get(instrumentId);
		IntSummaryStatistics statistics = instrument.getOrdreBook().getOrders().stream()
		        .mapToInt(Order::getQuantity)
		        .summaryStatistics();
		 
		int min = statistics.getMin();
		int max = statistics.getMax();
		long total=statistics.getSum();
		long count=statistics.getCount();
		
		Date maxDate = instrument.getOrdreBook().getOrders().stream().map(Order::getEntryDate).max(Date::compareTo).get();
		Date minDate = instrument.getOrdreBook().getOrders().stream().map(Order::getEntryDate).min(Date::compareTo).get();
		
		System.out.println("maxDate: " + maxDate);
		System.out.println("minDate: " + minDate);
		
		OrderBookSummaryStats stats=new OrderBookSummaryStats();
		stats.setSmallestOrderInBook(min);
		stats.setBiggestOrderInBook(max);
		stats.setTotalOrderCount(count);
		stats.setTotalOrderBookQuantityCount(total);
		stats.setEarliestOrderEntry(minDate);
		stats.setLastOrderEntry(maxDate);
		return stats;
	}
	public OrderBookValidityStats getOrderBookValidityStats(int instrumentId) {
		logger.info("Fetching valid invalid order book summary stats for instrument id {}", instrumentId);
		Instrument instrument = InstrumentCache.INSTRUMENT_CACHE_MAP.get(instrumentId);
		IntSummaryStatistics statistics = instrument.getOrdreBook().getOrders().stream()
		        .mapToInt(Order::getQuantity)
		        .summaryStatistics();
		 
		OrderBookValidityStats validInvalidStats=new OrderBookValidityStats();
		validInvalidStats.setBiggestOrderInBook(statistics.getMax());
		validInvalidStats.setSmallestOrderInBook(statistics.getMin());
		System.out.println("Executing Order book for price : " + getExecutionPrice(instrument));
		List<Order> inValidOrderList = instrument.getOrdreBook().getOrders().stream().filter((order)->{
			if(order.getOrderType()==OrderType.LIMIT) {
				if(order.getPrice() < getExecutionPrice(instrument)){
					return true;
				}else {
					return false;
				}
				
			}else{
				return false;
			}
		}
		).collect(Collectors.toList());
		
		List<Order> validOrderList = instrument.getOrdreBook().getOrders().stream().filter((order)->{
			if(order.getOrderType()==OrderType.LIMIT) {
				if(order.getPrice() > getExecutionPrice(instrument)) {
					return true;
				}else {
					return false;
				}
				
			}else{
				return false;
			}
		}
		).collect(Collectors.toList());
		
		validInvalidStats.setValidOrders(validOrderList);
		validInvalidStats.setInvalidOrders(inValidOrderList);
		
		Integer orderBookDemandQuantity=validOrderList.stream().map(order->order.getQuantity())
		.collect(Collectors.summingInt(Integer::intValue));
		
		validInvalidStats.setOrderBookDemand(orderBookDemandQuantity);
		
		logger.info("Finished valid invalid order book summary stats for instrument id {}", instrumentId);
		
	return validInvalidStats;
	}	

	private void processOrderBook(ExecutionContext executionContext) {
		orderBookRuleCollection.processRules(executionContext);
	}

	public OrderBookRuleCollection getOrderBookRuleCollection() {
		return orderBookRuleCollection;
	}

	public void setOrderBookRuleCollection(OrderBookRuleCollection orderBookRuleCollection) {
		this.orderBookRuleCollection = orderBookRuleCollection;
	}

	public Double getExecutionPrice(Instrument instrument) {
		return instrument.getOrdreBook().getExecution()!=null?instrument.getOrdreBook().getExecution().getPrice():ExecutionConstants.EXECUTION_DEFAULT_PRICE;
	}
}

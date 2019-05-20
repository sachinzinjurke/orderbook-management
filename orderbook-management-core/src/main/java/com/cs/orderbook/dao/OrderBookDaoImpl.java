package com.cs.orderbook.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cs.constants.OrderBookStatusType;
import com.cs.core.Instrument;
import com.cs.core.Order;
import com.cs.core.OrderBook;
import com.cs.orderbook.cache.InstrumentCache;

public class OrderBookDaoImpl implements OrderBookDao {

	private static final Logger logger = LoggerFactory.getLogger(OrderBookDaoImpl.class.getName());

	
	@Override
	public void addOrderToOrderBook(Integer instrumentId, Order order) {
		if(InstrumentCache.INSTRUMENT_CACHE_MAP.containsKey(instrumentId)) {
			Instrument instrument = InstrumentCache.INSTRUMENT_CACHE_MAP.get(instrumentId);
			instrument.getOrdreBook().add(order);
		}else {
			logger.info("Instrument does not exits in cache");
		}
	}
	
	public boolean changeOrderBookStatus(Integer instrumentId,OrderBookStatusType orderBookStatusType) {
		boolean canChangeStatus=false;
		if(InstrumentCache.INSTRUMENT_CACHE_MAP.containsKey(instrumentId)) {
			Instrument instrument = InstrumentCache.INSTRUMENT_CACHE_MAP.get(instrumentId);
			canChangeStatus=canChangeStatus(instrument.getOrdreBook(),orderBookStatusType);
			if(canChangeStatus) {
				instrument.getOrdreBook().setOrderBookStatusType(orderBookStatusType);
			}else {
				logger.info("Can not change orderbook status");
			}
		}else {
			logger.info("Instrument does not exits in cache");
		}
		return canChangeStatus;
	}
	
	private boolean canChangeStatus(OrderBook ordreBook, OrderBookStatusType orderBookStatusType) {
		boolean isStatusChanged=false;
		if (!(ordreBook.getOrderBookStatusType() == orderBookStatusType)) {
			if (orderBookStatusType == OrderBookStatusType.OPEN) {
				if (ordreBook.getOrderBookStatusType() == OrderBookStatusType.PENDING) {
				//	ordreBook.setOrderBookStatusType(orderBookStatusType.OPEN);
					isStatusChanged=true;
				} else {
					logger.info("can not change orderbook status from : {} to {}",ordreBook.getOrderBookStatusType(),orderBookStatusType);
				}
			} else if (orderBookStatusType == OrderBookStatusType.CLOSE) {
				if (ordreBook.getOrderBookStatusType() == OrderBookStatusType.OPEN) {
					//ordreBook.setOrderBookStatusType(orderBookStatusType.CLOSE);
					isStatusChanged=true;
				} else {
					logger.info("can not change orderbook status from : {} to {}",ordreBook.getOrderBookStatusType(),orderBookStatusType);
				}
			}
		}
		return isStatusChanged;
	}

	@Override
	public void createOrderBook(Integer instrumentId) {
		System.out.println("Creating order");
		logger.info("Creating orderbook");
		if(!InstrumentCache.INSTRUMENT_CACHE_MAP.containsKey(instrumentId)) {
			Instrument instrument=new Instrument();
			instrument.setInstrumentId(instrumentId);
			instrument.setOrdreBook(new OrderBook());
			InstrumentCache.INSTRUMENT_CACHE_MAP.put(instrumentId, instrument);
		}else {
			logger.info("Instrument already exits in cache");
		}
		
	}

}

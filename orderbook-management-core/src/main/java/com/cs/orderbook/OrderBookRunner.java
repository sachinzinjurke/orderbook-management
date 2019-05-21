package com.cs.orderbook;


import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.cs.constants.OrderBookStatusType;
import com.cs.core.Execution;
import com.cs.core.LimitOrder;
import com.cs.core.MarketOrder;
import com.cs.orderbook.facade.OrderBookManager;
import com.cs.orderbook.stats.OrderBookSummaryStats;
import com.cs.orderbook.stats.OrderBookValidityStats;

/**
 * Hello world!
 *
 */
public class OrderBookRunner {
	private static final Logger logger = LoggerFactory.getLogger(OrderBookRunner.class.getName());
    public static void main( String[] args )
    {
    	logger.info("*****************Staring application*************************");
        ApplicationContext ctx = new ClassPathXmlApplicationContext("orderbook-core-context.xml");
        OrderBookManager manager = (OrderBookManager)ctx.getBean("orderBookManager");
        logger.info("Creating OrderBook for instrument : {}",1);
        manager.createOrderBook(1);
        logger.info("Opening OrderBook for instrument : {}",1);
        manager.changeOrderBookStatus(1, OrderBookStatusType.OPEN);
        
        logger.info("Adding orders to orderbook OrderBook for instrument : {}",1);
        manager.addOrderToOrderBook(1, new LimitOrder(1, 10,new Date(), 1, 20.10));
        manager.addOrderToOrderBook(1, new LimitOrder(2, 20,new Date(), 1, 30.10));
        manager.addOrderToOrderBook(1, new LimitOrder(2, 20,new Date(), 1, 5.10));
        manager.addOrderToOrderBook(1, new MarketOrder(3, 5,new Date(), 1, 10.10));
        manager.addOrderToOrderBook(1, new MarketOrder(4, 25,new Date(), 1, 15.10));
        logger.info("Finished adding orders to orderbook OrderBook for instrument : {}",1);
        
        logger.info("Fetcing OrderBook Stats for instrument : ",1);
        OrderBookSummaryStats orderBookStats = manager.getOrderBookStats(1);
        logger.info("Fetcing OrderBook Validity Stats for instrument : {}",1);
        OrderBookValidityStats orderBookValidityStats = manager.getOrderBookValidityStats(1);
        logger.info("Markign OrderBook as CLOSED for instrument : {}",1);
        manager.changeOrderBookStatus(1, OrderBookStatusType.CLOSE);
        Execution execution=new Execution();
        execution.setPrice(15.00);
        execution.setQuantity(10);
        logger.info("Executing OrderBook for instrument : {}",1);
        manager.executeOrderBook(1,execution );
        logger.info("Fetcing OrderBook Validity Stats after execution for instrument : {}",1);
        OrderBookValidityStats stats1 = manager.getOrderBookValidityStats(1);
        
        logger.info("*****************Exiting application*************************");
      
    }
}

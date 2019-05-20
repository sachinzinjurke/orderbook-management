package com.cs.orderbook.cache;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.cs.core.Instrument;

public class InstrumentCache {

	public static Map<Integer,Instrument> INSTRUMENT_CACHE_MAP=new ConcurrentHashMap<Integer, Instrument>();
	
}

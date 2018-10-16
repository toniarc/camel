package com.redhat.processor;

import java.util.Map;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExchangePrinter implements Processor {

	static Logger log = LoggerFactory.getLogger(ExchangePrinter.class);
	
	public void process(Exchange exchange) throws Exception {
		String body = exchange.getIn().getBody(String.class);
		
		log.info("Body: " + body);

		log.info("Headers:");
		Map<String, Object> headers = exchange.getIn().getHeaders();
		for (String key : headers.keySet()) {
			log.info("Key: " + key + " | Value: " + headers.get(key));
		}
	}

}

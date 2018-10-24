package com.redhat.lab.testdemo;

import org.apache.camel.Exchange;
import org.apache.camel.Predicate;
import org.apache.commons.logging.LogFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.redhat.processor.ExchangePrinter;

public class AdminOrderFilter implements Predicate {

	static Logger log = LoggerFactory.getLogger(AdminOrderFilter.class);
	
	@Override
	public boolean matches(Exchange exchange) {
		Order order = exchange.getIn().getBody(Order.class);
		if (order != null && order.getCustomer() != null && !order.getCustomer().isAdmin()) {
			log.info("Filtering out non admin order!");
			return true;
		}
		return false;
	}

}

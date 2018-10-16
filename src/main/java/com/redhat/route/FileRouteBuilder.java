package com.redhat.route;

import org.apache.camel.builder.RouteBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.redhat.processor.HeaderProcessor;

public class FileRouteBuilder extends RouteBuilder{

	static Logger log = LoggerFactory.getLogger(FileRouteBuilder.class);
	
	@Override
	public void configure() throws Exception {
		
		from("file:orders/incoming/?include=order.*xml&noop=true").
			routeId("route1").
			filter(xpath("/order/orderItems/orderItem[not(contains(orderItemPublisherName, 'ABC Company'))]")).
			process(new HeaderProcessor()).
		to("file:orders/outgoing/?fileExist=Fail&fileName=${header.orderDate}/${header.CamelFileName}");
	}

}

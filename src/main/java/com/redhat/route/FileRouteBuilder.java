package com.redhat.route;

import org.apache.camel.builder.RouteBuilder;

public class FileRouteBuilder extends RouteBuilder{

	@Override
	public void configure() throws Exception {
		from("file:orders/incoming/?include=order.*xml&noop=true").
			routeId("route1").
			filter(xpath("/order/orderItems/orderItem[not(contains(orderItemPublisherName, 'ABC Company'))]")).
		to("file:orders/outgoing/?fileExist=Fail");
	}

}

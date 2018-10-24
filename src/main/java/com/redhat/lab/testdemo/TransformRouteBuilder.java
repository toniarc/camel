package com.redhat.lab.testdemo;

import org.apache.camel.builder.RouteBuilder;

public class TransformRouteBuilder extends RouteBuilder {

	@Override
	public void configure() throws Exception {
		from("direct:orderInput")
		 .routeId("process")
		 //TODO add filter
		 .filter(new AdminOrderFilter())
		 .to("file:orders/admin");
	}

}

package com.redhat.route;

import org.apache.camel.builder.RouteBuilder;

public class RouteBasedContentRouteBuilder extends RouteBuilder{

	@Override
	public void configure() throws Exception {
		from("file:orders/incoming/?include=order.*xml&noop=true").
			routeId("route1").
			setHeader("orderId", xpath("/order/orderId/text()")).
			setHeader("vendorName", xpath("/order/orderItems/orderItem/orderItemPublisherName/text()")).
				choice().
					when(simple("${header.vendorName} == 'ABC Company'")).
						log("Delivering order ${header.orderId} to vendor ABC").
						to("activemq:queue:abc").
					when(simple("${header.vendorName} == 'ORly'")).
						log("Delivering order ${header.orderId} to vendor ORly").
						to("activemq:queue:orly").
					when(simple("${header.vendorName} == 'Namming'")).
						log("Delivering order ${header.orderId} to vendor Namming").
						to("activemq:queue:namming").
					otherwise().
						log("Failed to deliver order: ${header.orderId} to vendor: ${header.vendorName}").
		to("file:orders/outgoing/?fileExist=Fail");
	}

}

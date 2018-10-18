package com.redhat.lab.transformdata;

import org.apache.camel.builder.RouteBuilder;

public class TransformRouteBuilder extends RouteBuilder {

	private static final String OUTPUT_FOLDER = null;

	@Override
	public void configure() throws Exception {

		from("jpa:com.redhat.training.jb421.model.Order"
				+ "?persistenceUnit=mysql"
				+ "&consumer.namedQuery=getUndeliveredOrders"
				+ "&consumer.delay=10000"
				+ "0&consumeLockEntity=false").
			wireTap("direct:updateOrder").
			marshal().jaxb().
			split(xpath("order/orderItems/orderItem")).
			aggregate(xpath("orderItem/catalogItem/id"), new ReservationAggregationStrategy()).
				completionInterval(10000).
				completeAllOnStop().
			log("${body}").
			setHeader("CatalogItemId",xpath("/reservation/catalogItemId/text()")).
			to("file:"+OUTPUT_FOLDER+"?fileName=${header.CatalogItemId}/reservation-${date:now:yyyy-MM-dd_HH-mm-ss}.xml");
		
		from("direct:updateOrder").
			process(new DeliverOrderProcessor()).
		to("jpa:com.redhat.training.jb421.model.Order?persistenceUnit=mysql");
	}

}

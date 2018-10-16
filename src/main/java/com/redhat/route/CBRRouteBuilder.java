package com.redhat.route;

import org.apache.camel.builder.RouteBuilder;

import com.redhat.processor.TestProcessor;

public class CBRRouteBuilder extends RouteBuilder{

	private static final String XPATH_ORDERID = "/order/orderId/text()";
	private static final String XPATH_VENDOR_NAME = "/order/orderItems/orderItem/orderItemPublisherName/text()";

	@Override
	public void configure() throws Exception {
		
		log.info("Iniciando processamento da rota");
		
		from("file:orders/incoming?include=order-.*.xml&noop=true").
			routeId("CBRRoute1").
			log("processando ${header.CamelFileName}").
			setHeader("orderId", xpath(XPATH_ORDERID)).
			setHeader("vendorName", xpath(XPATH_VENDOR_NAME)).
			process(new TestProcessor()).
			log("skipOrder header: ${header.skipOrder}").
			filter(simple("${header.skipOrder} != 'Y'")).
				log("processando depois do filter ${header.vendorName}").
				choice().
					when(simple("${header.vendorName} == 'ABC Company'")).
						log("Encaminhado para a pasta orders/outgoing/abc").
						to("file:orders/outgoing/abc").
					when(simple("${header.vendorName} == 'ORly'")).
						log("Encaminhado para a pasta orders/outgoing/orly").
						to("file:orders/outgoing/orly").					
					when(simple("${header.vendorName} == 'Namming'")).
						log("Encaminhado para a pasta orders/outgoing/naming").
						to("file:orders/outgoing/namming").
					otherwise().
						log("Order ${header.CamelFileName} not matching.");
						
	}

}

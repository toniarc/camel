package com.redhat.route;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.dataformat.xmljson.XmlJsonDataFormat;

public class TransformRouteBuilder extends RouteBuilder{

	@Override
	public void configure() throws Exception {
		
		XmlJsonDataFormat xmlJson = new XmlJsonDataFormat();
		
		//from("activemq:queue:orderInput?username=admin&password=admin")
		from("file:orders/incoming?noop=true&include=order.*.xml")
		 .marshal().jaxb()
		 .log("XML Body: ${body}")
		 .marshal(xmlJson)
		 .log("JSON Body: ${body}")
		 .filter().jsonpath("$[?(@.delivered !='true')]")
		 .wireTap("direct:jsonOrderLog")
		 .to("mock:fulfillmentSystem");
		 
		from("direct:jsonOrderLog")
		 .log("Order received: ${body}")
		 .to("mock:orderLog");
	}

}

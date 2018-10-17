package com.redhat.route;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.dataformat.bindy.fixed.BindyFixedLengthDataFormat;

import com.redhat.aggregation.ArrayListAggregationStrategy;
import com.redhat.model.OrderFixedLength;
import com.redhat.processor.BatchXMLProcessor;

public class AggregateRouteBuilder extends RouteBuilder{

	@Override
	public void configure() throws Exception {
		
		BindyFixedLengthDataFormat bindy = new BindyFixedLengthDataFormat(OrderFixedLength.class);
		
		from("file:data/ordersFixedLength?fileName=orders.txt&noop=true").
			split().tokenize("\n").streaming().
			unmarshal(bindy).
			aggregate(constant(true), new ArrayListAggregationStrategy()).
				completionSize(5).
				completeAllOnStop().
			process(new BatchXMLProcessor()).
			wireTap("direct:orderLogger").
		to("file:orders/outgoing?fileName=output.xml&fileExist=Append","mock:result");
		
		from("direct:orderLogger")
		 .split()
		 .tokenizeXML("order")
		 .log("${body}");
	}

}

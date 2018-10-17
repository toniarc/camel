package com.redhat.route;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.dataformat.bindy.csv.BindyCsvDataFormat;

import com.redhat.model.Insurance;

public class TransformRouteBuilder2 extends RouteBuilder{

	@Override
	public void configure() throws Exception {
		
		BindyCsvDataFormat bindy = new BindyCsvDataFormat(Insurance.class);
		
		from("file:data?fileName=insurance_sample.csv&noop=true").
			log("filename: ${header.CamelFileName}").
			unmarshal(bindy).
		to("mock:orderQueue", "direct:orderLog");
		
		from("direct:orderLog")
		 .split(body()).streaming()
		 .log("${body}")
		 .to("mock:orderLoggingSystem");
	}

}

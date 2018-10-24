package com.redhat.lab.testkit;

import org.apache.camel.builder.RouteBuilder;

public class TestKitRouteBuilder extends RouteBuilder {

	@Override
	public void configure() throws Exception {
		
		from("file:in")
		.log("${body}")
		.to("file:out");
	}

}

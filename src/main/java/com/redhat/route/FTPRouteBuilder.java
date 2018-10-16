package com.redhat.route;

import org.apache.camel.builder.RouteBuilder;

import com.redhat.processor.ExchangePrinter;

public class FTPRouteBuilder extends RouteBuilder{

	@Override
	public void configure() throws Exception {
		from("ftp://demo-user@demo.wftpserver.com/upload?password=demo-user").
		log("New file ${header.CamelFileName} picked up from ${header.CamelFileHost}").
		process(new ExchangePrinter()).
		to("file:orders/outgoing?fileExist=Fail");
	}

}

package com.redhat.processor;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.xml.XPathBuilder;

public class HeaderProcessor implements Processor{

	SimpleDateFormat sdfInput = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	SimpleDateFormat sdfOutput = new SimpleDateFormat("yyyyMMdd");
	
	@Override
	public void process(Exchange exchange) throws Exception {
		String orderXml = exchange.getIn().getBody(String.class);
		String orderDateTime = XPathBuilder.xpath("/order/orderDateTime/text()").evaluate(exchange.getContext(), orderXml);
		String formatedOrderDate = getFormattedDate(orderDateTime);
		exchange.getIn().setHeader("orderDate", formatedOrderDate);
	}

	private String getFormattedDate(String orderDateTime) throws ParseException {
		Date input = sdfInput.parse(orderDateTime);
		return sdfOutput.format(input);
	}

}

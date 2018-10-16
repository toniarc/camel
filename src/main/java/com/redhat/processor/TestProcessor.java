package com.redhat.processor;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.xml.XPathBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestProcessor implements Processor{

	static Logger log = LoggerFactory.getLogger(TestProcessor.class);
		
	@Override
	public void process(Exchange exchange) throws Exception {
		String body = exchange.getIn().getBody(String.class);
		
		String evaluate = XPathBuilder.xpath("/order/test/text()").evaluate(exchange.getContext(), body);
		if(evaluate != null && evaluate.trim().length() > 0) {
			log.info("Adding skipOrder header");
			exchange.getIn().setHeader("skipOrder", "Y");
		}
	}

}

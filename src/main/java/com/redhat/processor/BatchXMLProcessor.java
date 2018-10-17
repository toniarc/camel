package com.redhat.processor;

import java.io.StringWriter;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

import com.redhat.model.OrderFixedLength;

public class BatchXMLProcessor implements Processor {

	@Override
	public void process(Exchange exchange) throws Exception {
		// Load JAXB Context for Order
		JAXBContext jaxbContext = JAXBContext.newInstance(OrderFixedLength.class);
		
		Marshaller marshaller = jaxbContext.createMarshaller();

		// This option prevents JAXB from including the XML header
		marshaller.setProperty(Marshaller.JAXB_FRAGMENT, true);
		
		// Buffer to hold batch XML string
		StringBuilder batchXML = new StringBuilder();
		
		// Create opening tag
		batchXML.append("<batch>");
		List<OrderFixedLength> orderBatch = exchange.getIn().getBody(List.class);
		for (OrderFixedLength order : orderBatch) {
			StringWriter sw = new StringWriter();
			marshaller.marshal(order, sw);
			String orderXML = sw.toString();
			sw.close();
			batchXML.append(orderXML);
		}
		// Create closing tag
		batchXML.append("</batch>");
		// Set the result as the new exchange body
		exchange.getIn().setBody(batchXML.toString());
	}

}

package com.redhat.lab.testdemo;

import java.util.concurrent.TimeUnit;

import org.apache.camel.EndpointInject;
import org.apache.camel.Exchange;
import org.apache.camel.Produce;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.AdviceWithRouteBuilder;
import org.apache.camel.builder.NotifyBuilder;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.test.junit4.CamelTestSupport;
import org.junit.Before;
import org.junit.Test;

public class TransformRouteTest extends CamelTestSupport {

	@Produce(uri = "direct:orderInput")
	private ProducerTemplate producer;

	@EndpointInject(uri = "mock:admin")
	private MockEndpoint destination;

	// indica que o teste altera a rota e que o contexto do camel precisa ser
	// inicializado manualmente
	@Override
	public boolean isUseAdviceWith() {
		return true;
	}

	@Before
	public void before() throws Exception {
		deleteDirectory("orders");
		AdviceWithRouteBuilder mockRoute = new AdviceWithRouteBuilder() {
			@Override
			public void configure() throws Exception {
				interceptSendToEndpoint("file:orders/admin").skipSendToOriginalEndpoint().to("mock:admin");
			}
		};
		context.getRouteDefinition("process").adviceWith(context, mockRoute);
		context.start();
	}

	@Test
	public void testNonAdminOrder() throws Exception {
		NotifyBuilder builder = new NotifyBuilder(context).whenDone(1).create();
		builder.matches(2, TimeUnit.SECONDS);

		Order testNonAdminOrder = createTestOrder(false);
		
		String nonAdminXML = getExpectedXmlString(testNonAdminOrder);
		producer.sendBodyAndHeader(nonAdminXML, Exchange.FILE_NAME, "output.xml");
		destination.expectedMessageCount(1);
		assertMockEndpointsSatisfied();
	}

	private String getExpectedXmlString(Order testNonAdminOrder) {
		return "content";
	}

	private Order createTestOrder(boolean b) {
		return new Order();
	}

}

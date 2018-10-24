package com.redhat;

import org.apache.camel.Exchange;
import org.apache.camel.component.file.GenericFile;
import org.apache.camel.test.spring.CamelSpringTestSupport;
import org.junit.Test;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class PredicatesTest extends CamelSpringTestSupport {
	@Override
	protected AbstractApplicationContext createApplicationContext() {
		return new ClassPathXmlApplicationContext("META-INF/spring/camel-context.xml");
	}

	@Test
	public void testRoute() {
		super.template.sendBodyAndHeader("file:in", "file", Exchange.FILE_NAME, "testFile.txt");
		GenericFile receiveBody = (GenericFile) super.consumer.receiveBody("file:out");
		String content = receiveBody.getFileNameOnly();
		assertEquals("testFile.txt", content);
	}
}

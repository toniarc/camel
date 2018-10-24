package com.redhat;

import org.apache.camel.Exchange;
import org.apache.camel.component.file.GenericFile;
import org.apache.camel.test.spring.CamelSpringTestSupport;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class BundleContextXmlTest extends CamelSpringTestSupport {

	@Override
	protected AbstractApplicationContext createApplicationContext() {
		return new ClassPathXmlApplicationContext("META-INF/spring/camel-context.xml");
	}
	
	@Before
	public void before() {
		deleteDirectory("in");
	}

	@After
	public void after() {
		deleteDirectory("out");
	}	
	
	@Test
	public void testCamelRoute() {
		template.sendBodyAndHeader("file:in", "file", Exchange.FILE_NAME, "testFile.txt");
		GenericFile receiveBody = (GenericFile)consumer.receiveBody("file:out");
		String content = receiveBody.getFileNameOnly();
		assertEquals("testFile.txt", content);
	}

}

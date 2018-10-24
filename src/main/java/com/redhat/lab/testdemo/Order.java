package com.redhat.lab.testdemo;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Order implements Serializable {

	private Customer customer; 
	
	public Customer getCustomer() {
		return customer;
	}

}

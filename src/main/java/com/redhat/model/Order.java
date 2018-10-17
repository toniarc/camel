package com.redhat.model;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Order implements Serializable {
	private static final long serialVersionUID = 5851038813219503043L;
	
	@XmlAttribute
	private String id;
	
	@XmlAttribute
	private String description;
	
	@XmlAttribute
	private double value;
	
	@XmlAttribute
	private double tax;
}
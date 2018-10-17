package com.redhat.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.camel.dataformat.bindy.annotation.DataField;
import org.apache.camel.dataformat.bindy.annotation.FixedLengthRecord;

@FixedLengthRecord(length = 25)
@XmlRootElement(name="order")
@XmlAccessorType(XmlAccessType.FIELD)
public class OrderFixedLength implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@DataField(pos = 1, length = 6)
	@XmlAttribute
	private Integer id;
	
	@DataField(pos = 2, length = 8, pattern = "MM-dd-YY")
	@XmlAttribute
	private Date orderDate = new Date();
	
	@DataField(pos = 3, length = 5, pattern = "##.##")
	@XmlAttribute
	private BigDecimal discount;
	
	@DataField(pos = 5, length = 6)
	@XmlAttribute
	private Integer customerId;
}

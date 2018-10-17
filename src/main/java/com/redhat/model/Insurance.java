package com.redhat.model;

import java.io.Serializable;

import org.apache.camel.dataformat.bindy.annotation.CsvRecord;
import org.apache.camel.dataformat.bindy.annotation.DataField;

@CsvRecord(separator = ",", crlf="UNIX", skipFirstLine=true)
public class Insurance implements Serializable {

	private static final long serialVersionUID = 1L;

	@DataField(pos=1)
	private int policyID;
	
	@DataField(pos=2)
	private String statecode;	
	
	@DataField(pos=3)
	private String county;	
	
	@DataField(pos=4)
	private double eqSiteLimit;	
	
	@DataField(pos=5)
	private double huSiteLimit;	
	
	@DataField(pos=6)
	private double flSiteLimit;	
	
	@DataField(pos=7)
	private double frSiteLimit;	
	
	@DataField(pos=8)
	private double tiv2011;
	
	@DataField(pos=9)
	private double tiv2012;	
	
	@DataField(pos=10)
	private double eqSiteDeductible;	
	
	@DataField(pos=11)
	private double huSiteDeductible;	
	
	@DataField(pos=12)
	private double flSiteDeductible;	
	
	@DataField(pos=13)
	private double frSiteDeductible;	
	
	@DataField(pos=14)
	private double pointLatitude;	
	
	@DataField(pos=15)
	private double pointLongitude;	
	
	@DataField(pos=16)
	private String line;	
	
	@DataField(pos=17)
	private String construction;	
	
	@DataField(pos=18)
	private String pointGranularity;	
	
}
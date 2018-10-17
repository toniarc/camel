package com.redhat.converter;

import org.apache.camel.Converter;

import com.google.gson.Gson;
import com.redhat.model.Insurance;

@Converter
public class InsuranceJSONTypeConverter {

	@Converter
	public String converToJson(Insurance insurance) {
		Gson gson = new Gson();
		return gson.toJson(insurance);
	}
}

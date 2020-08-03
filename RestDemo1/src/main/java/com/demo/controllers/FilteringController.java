package com.demo.controllers;

import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.common.SampleBean;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

@RestController
public class FilteringController {

    @GetMapping("/filtering")
    SampleBean getBean() {
	return new SampleBean("v1", "v2", "v3");
    }

    @GetMapping("/filtering-dy")
    MappingJacksonValue getBeanDy() {
	SampleBean bean = new SampleBean("v1", "v2", "v3");
	SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("v1", "v2");
	FilterProvider filters = new SimpleFilterProvider().addFilter("SampleFilter", filter);
	MappingJacksonValue mapping = new MappingJacksonValue(bean);
	mapping.setFilters(filters);
	return mapping;
    }

}

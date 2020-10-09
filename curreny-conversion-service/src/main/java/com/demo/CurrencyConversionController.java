package com.demo;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class CurrencyConversionController {

    Logger logger = LoggerFactory.getLogger(CurrencyConversionController.class);

    @Autowired
    private CurrencyExchangeClient exchangeClient;

    @GetMapping("currency-converter/from/{from}/to/{to}/quantity/{quantity}")
    public CurrencyConversionBean convertCurrency(@PathVariable String from, @PathVariable String to,
	    @PathVariable BigDecimal quantity) {

	Map<String, String> uriVariables = new HashMap<>();
	uriVariables.put("from", from);
	uriVariables.put("to", to);
	ResponseEntity<CurrencyConversionBean> response = new RestTemplate().getForEntity(
		"http://localhost:8000/currency-exchange/from/{from}/to/{to}", CurrencyConversionBean.class,
		uriVariables);

	CurrencyConversionBean obj = response.getBody();
	BigDecimal rate = obj.getConversionMultiple();
	return new CurrencyConversionBean(1L, from, to, rate, quantity, quantity.multiply(rate), obj.getPort());
    }

    @GetMapping("currency-converter-feign/from/{from}/to/{to}/quantity/{quantity}")
    public CurrencyConversionBean convertCurrencyFeign(@PathVariable String from, @PathVariable String to,
	    @PathVariable BigDecimal quantity) {
	CurrencyConversionBean obj = exchangeClient.getExchangeValue(to, from);
	logger.info("conversion bean: {}", obj);
	BigDecimal rate = obj.getConversionMultiple();
	return new CurrencyConversionBean(1L, from, to, rate, quantity, quantity.multiply(rate), obj.getPort());
    }
}

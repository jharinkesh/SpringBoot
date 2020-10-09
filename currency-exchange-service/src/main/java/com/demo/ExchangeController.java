package com.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExchangeController {

    Logger logger = LoggerFactory.getLogger(ExchangeController.class);

    @Autowired
    Environment env;

    @Autowired
    ExchangeValueRepository repositroy;

    @GetMapping("currency-exchange/from/{from}/to/{to}")
    public ExchangeValue getExchangeValue(@PathVariable String to, @PathVariable String from) {
//	ExchangeValue ev = new ExchangeValue(from, to, BigDecimal.valueOf(34));
	ExchangeValue ev = repositroy.findByFromAndTo(from, to);
	logger.info("exchange value: {}", ev);
	ev.setPort(Integer.valueOf(env.getProperty("local.server.port")));
	return ev;
    }
}

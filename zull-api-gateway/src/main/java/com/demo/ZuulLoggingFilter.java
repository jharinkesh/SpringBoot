package com.demo;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

public class ZuulLoggingFilter extends ZuulFilter {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public boolean shouldFilter() {
	return true;
    }

    @Override
    public Object run() throws ZuulException {
	HttpServletRequest request = RequestContext.getCurrentContext().getRequest();
	logger.info("request -> {} uri -> {}", request, request.getRequestURI());
	return null;
    }

    @Override
    public String filterType() {
	return "pre"; // post //error
    }

    @Override
    public int filterOrder() {
	return 1;
    }

}

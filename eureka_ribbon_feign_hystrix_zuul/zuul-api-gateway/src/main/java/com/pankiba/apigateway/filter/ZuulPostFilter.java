package com.pankiba.apigateway.filter;

import org.springframework.stereotype.Component;

import com.netflix.zuul.ZuulFilter;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class ZuulPostFilter extends ZuulFilter {

	@Override
	public String filterType() {
		return "post";
	}

	@Override
	public int filterOrder() {
		return 1;
	}

	@Override
	public boolean shouldFilter() {
		return true;
	}

	@Override
	public Object run() {
		log.info("in ZuulPostFilter");
		return null;
	}
}

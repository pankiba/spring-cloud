package com.pankiba.zuul.filter;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class ZuulPreFilter extends ZuulFilter {

	@Override
	public boolean shouldFilter() {
		return true;
	}

	@Override
	public Object run() throws ZuulException {

		RequestContext requestContext = RequestContext.getCurrentContext();
		HttpServletRequest httpServletRequest = requestContext.getRequest();

		log.info(" getRequestURL  - {} ", httpServletRequest.getRequestURL());
		log.info(" getRemoteAddr  - {} ", httpServletRequest.getRemoteAddr());

		if (!(httpServletRequest.getRemoteAddr().equals("0:0:0:0:0:0:0:1")
				|| httpServletRequest.getRemoteAddr().equals("127.0.0.1"))) {
			requestContext.setResponseStatusCode(400);
			requestContext.setResponseBody("access denied");
			requestContext.setSendZuulResponse(false);
		}
		return null;
	}

	@Override
	public String filterType() {
		return "pre";
	}

	@Override
	public int filterOrder() {
		return 1;
	}

}

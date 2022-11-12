package com.pankiba.feignclient.proxy;

import feign.Response;
import feign.codec.ErrorDecoder;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ErrorHandler implements ErrorDecoder {

	@Override
	public Exception decode(String methodKey, Response response) {
		log.info(" feign error handling ");
		return new Exception("Generic error");
	}

}

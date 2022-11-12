package com.pankiba.feignclient.proxy;

import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Component;

import com.pankiba.feignclient.domain.Post;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class FeignClientProxyFallback implements FeignClientProxy{

	@Override
	public List<Post> getPosts() {
		log.info(" returning empty - fallback ");
		return Collections.emptyList();
	}

}

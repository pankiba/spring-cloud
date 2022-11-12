package com.pankiba.feignclient.proxy;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import com.pankiba.feignclient.domain.Post;

@FeignClient(name = "post-client", url = "https://jsonplaceholder.typicode.com", fallback = FeignClientProxyFallback.class)
public interface FeignClientProxy {

	@GetMapping("/posts")
	public List<Post> getPosts();

}

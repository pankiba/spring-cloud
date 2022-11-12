package com.pankiba.feignclient.web.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pankiba.feignclient.domain.Post;
import com.pankiba.feignclient.proxy.FeignClientProxy;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class FeignResource {

	@Autowired
	private FeignClientProxy feignClientProxy;
	
	@GetMapping("/posts")
	public ResponseEntity<List<Post>> getEmployees() {

		log.info(" get posts ");
		List<Post> posts = feignClientProxy.getPosts();

		return new ResponseEntity<List<Post>>(posts, HttpStatus.OK);
	}
	
}

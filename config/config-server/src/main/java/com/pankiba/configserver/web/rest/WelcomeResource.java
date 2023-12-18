package com.pankiba.configserver.web.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WelcomeResource {

	@GetMapping(path = "/")
	public String welcomeMessage() {
		return "Hello World, Welcome to Config Server !! ";
	}

}

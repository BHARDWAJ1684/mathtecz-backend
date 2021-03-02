package com.loginservices.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyVideosController {
	
	@GetMapping("/myvideos")
	public String fetchUserVideos(String input) {
		return "Below are Your Subscribed Videos from Math Tecz";
	}

}

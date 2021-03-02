package com.loginservices.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NotificationsController {
	
	@GetMapping("/notifications")
	public String fetchNotifications(String input) {
		return "Below are New Notofications from Math Tecz";
	}

}

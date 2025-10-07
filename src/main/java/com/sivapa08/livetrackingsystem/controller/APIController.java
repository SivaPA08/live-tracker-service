package com.sivapa08.livetrackingsystem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class APIController {
	@GetMapping("/")
	public String userPage() {
		return "index";
	}

	@GetMapping("/mapbtw")
	public String mapPage() {
		return "map";
	}
}

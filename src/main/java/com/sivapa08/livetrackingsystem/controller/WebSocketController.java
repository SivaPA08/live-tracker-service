package com.sivapa08.livetrackingsystem.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import com.sivapa08.livetrackingsystem.util.LocationDetails;

@Controller
public class WebSocketController {

	@MessageMapping("/location")
	@SendTo("/topic/location")
	public LocationDetails locationDetails(LocationDetails locationDetails) {
		System.out.println("Received location - Username: " + locationDetails.getUsername()
				+ " Lat: " + locationDetails.getLatitude()
				+ " Lon: " + locationDetails.getLongitude());
		return locationDetails;
	}
}

package com.sivapa08.livetrackingsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;
import org.springframework.context.event.EventListener;

import com.sivapa08.livetrackingsystem.util.LocationDetails;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Controller
public class WebSocketController {

	@Autowired
	private SimpMessagingTemplate messagingTemplate;

	// Track active sessions with username mapping
	private static final Map<String, String> sessionUserMap = new ConcurrentHashMap<>();

	@MessageMapping("/location")
	@SendTo("/topic/location")
	public LocationDetails locationDetails(LocationDetails locationDetails) {
		// Store session-username mapping
		sessionUserMap.put(locationDetails.getUsername(), locationDetails.getUsername());

		System.out.println("Received location - Username: " + locationDetails.getUsername()
				+ " Lat: " + locationDetails.getLatitude()
				+ " Lon: " + locationDetails.getLongitude());
		return locationDetails;
	}

	// Handle user disconnection
	@EventListener
	public void handleSessionDisconnect(SessionDisconnectEvent event) {
		String sessionId = event.getSessionId();

		// Find and remove the user from active sessions
		sessionUserMap.forEach((username, session) -> {
			if (session.equals(sessionId)) {
				// Notify all subscribers about the disconnected user
				LocationDetails disconnectMessage = new LocationDetails();
				disconnectMessage.setUsername(username);
				messagingTemplate.convertAndSend("/topic/user-disconnected", disconnectMessage);

				System.out.println("User disconnected: " + username);
				sessionUserMap.remove(username);
			}
		});
	}
}

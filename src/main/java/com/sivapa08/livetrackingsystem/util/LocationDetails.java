package com.sivapa08.livetrackingsystem.util;

public class LocationDetails {

	private String username;
	private double latitude;
	private double longitude;
	private long timestamp;

	// Default constructor
	public LocationDetails() {
		this.timestamp = System.currentTimeMillis();
	}

	// Constructor with parameters
	public LocationDetails(String username, double latitude, double longitude) {
		this.username = username;
		this.latitude = latitude;
		this.longitude = longitude;
		this.timestamp = System.currentTimeMillis();
	}

	// Getters and Setters
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}

	@Override
	public String toString() {
		return "LocationDetails{" +
				"username='" + username + '\'' +
				", latitude=" + latitude +
				", longitude=" + longitude +
				", timestamp=" + timestamp +
				'}';
	}
}

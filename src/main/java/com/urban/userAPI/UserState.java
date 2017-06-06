package com.urban.userAPI;


import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.Entity;

/**
 * Object that will be received and sent
 */
public class UserState {

	private Long userId;
	private String email;
	private String username;
	private Long highscore;

	// TRUE if platform is Android, FALSE if iOS
	private Boolean isAndroid;
	private String countryCode; // Dont know the country code format so its a String
	private String languageCode; // same ^

	public UserState(Entity q) {
		this.userId = (Long) q.getProperty("userId");
		this.email = (String) q.getProperty("email");
		this.username = (String) q.getProperty("username");
		this.highscore = (Long) q.getProperty("highscore");
		this.isAndroid = (Boolean) q.getProperty("isAndroid");
		this.countryCode = (String) q.getProperty("countryCode");
		this.languageCode = (String) q.getProperty("languageCode");
	}

	public void saveToDatastore(DatastoreService datastore) {
		Entity userState = new Entity("UserState", userId);
		userState.setProperty("email", email);
		userState.setProperty("username", username);
		userState.setProperty("highscore", highscore);
		userState.setProperty("isAndroid", isAndroid);
		userState.setProperty("countryCode", countryCode);
		userState.setProperty("languageCode", languageCode);
		datastore.put(userState);
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public long getHighscore() {
		return highscore;
	}

	public void setHighscore(long highscore) {
		this.highscore = highscore;
	}

	public boolean isAndroid() {
		return isAndroid;
	}

	public void setAndroid(Boolean android) {
		isAndroid = android;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public String getLanguageCode() {
		return languageCode;
	}

	public void setLanguageCode(String languageCode) {
		this.languageCode = languageCode;
	}
}

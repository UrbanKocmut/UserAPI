package com.urban.userAPI;


/**
 * Object that will be received and sent
 */
public class UserState {

	private Long userId;
	private String email;
	private String username;
	private Long highscore;

	// TRUE if platform is Android, FALSE if iOS
	private String platform;
	private String countryCode; // Dont know the country code format so its a String
	private String languageCode; // same ^


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

	public String getPlatform() {
		return platform;
	}

	public void setPlatform(String platform) {
		this.platform = platform;
	}
}

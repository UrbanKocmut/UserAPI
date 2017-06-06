package com.urban.userAPI;

import com.google.api.server.spi.config.*;
import com.google.api.server.spi.response.BadRequestException;
import com.google.api.server.spi.response.NotFoundException;
import com.google.appengine.api.datastore.*;


@Api(
		name = "userStateAPI",
		version = "v1",
		namespace =
		@ApiNamespace(
				ownerDomain = "userAPI.urban.com",
				ownerName = "userAPI.urban.com",
				packagePath = ""
		),
		issuers = {
				@ApiIssuer(
						name = "Urban",
						issuer = "https://securetoken.google.com/userapi-169914",
						jwksUri = "https://www.googleapis.com/robot/v1/metadata/x509/securetoken@system.gserviceaccount.com")
		}
)
public class UserStateAPI {

	private final DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();

	@ApiMethod(name = "receiveUserState")
	public void receiveUserState(UserState userState) throws BadRequestException {
		if (iskUserStateOk(userState))
			saveToDatastore(userState);
		else
			throw new BadRequestException("UserState is null!");

	}

	private void saveToDatastore(UserState userState) {
		Entity us = new Entity("UserState", userState.getUserId());
		us.setProperty("email", userState.getEmail());
		us.setProperty("username", userState.getUsername());
		us.setProperty("highscore", userState.getHighscore());
		us.setProperty("platform", userState.getPlatform());
		us.setProperty("countryCode", userState.getCountryCode());
		us.setProperty("languageCode", userState.getLanguageCode());
		datastore.put(us);
	}

	private boolean iskUserStateOk(UserState userState) {
		if (userState == null) return false;
		try {
			userState.getUserId();
			userState.getUsername();
			userState.getPlatform();
			userState.getCountryCode();
			userState.getEmail();
			userState.getLanguageCode();
			userState.getHighscore();
		} catch (NullPointerException e) {
			return false;
		}
		return true;
	}

	@ApiMethod(name = "returnUserState", httpMethod = "GET")
	public UserState returnUserState(@Named("userId") Long id) throws NotFoundException {
		Entity entity;
		try {
			entity = datastore.get(KeyFactory.createKey("UserState", id));
		} catch (EntityNotFoundException e) {
			throw new NotFoundException(e.getMessage() + "\nID: " + id);
		}
		return createUserStateFromEntity(entity);

	}

	private UserState createUserStateFromEntity(Entity entity) {
		UserState userState = new UserState();
		userState.setUserId((Long) entity.getProperty("userId"));
		userState.setEmail((String) entity.getProperty("email"));
		userState.setUsername((String) entity.getProperty("username"));
		userState.setHighscore((Long) entity.getProperty("highscore"));
		userState.setPlatform((String) entity.getProperty("platform"));
		userState.setCountryCode((String) entity.getProperty("countryCode"));
		userState.setLanguageCode((String) entity.getProperty("languageCode"));

		return userState;
	}

}

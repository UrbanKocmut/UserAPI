package com.urban.userAPI;

import com.google.api.server.spi.config.*;
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
	public void receiveUserState(UserState userState) {
		userState.saveToDatastore(datastore);
	}

	@ApiMethod(name = "returnUserState", httpMethod = "GET")
	public UserState returnUserState(@Named("userId") Long id) throws NotFoundException {
		Entity q;
		try {
			q = datastore.get(KeyFactory.createKey("UserState", id));
		} catch (EntityNotFoundException e) {
			throw new NotFoundException(e.getMessage() + "\nID: " + id);
		}
		return new UserState(q);

	}

}

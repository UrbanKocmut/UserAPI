package com.urban.userAPI;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiIssuer;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.google.appengine.api.datastore.*;

import java.util.List;

@Api(
		name = "rankingAPI",
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
public class RankingAPI {

	private final DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();

	@ApiMethod(name = "returnHighscores", httpMethod = "GET")
	public List<Entity> returnHighscores() {
		Query q = new Query("UserState").addSort("highscore", Query.SortDirection.DESCENDING);
		q.addProjection(new PropertyProjection("highscore", Long.class));
		q.addProjection(new PropertyProjection("username", String.class));
		PreparedQuery pq = datastore.prepare(q);
		return pq.asList(FetchOptions.Builder.withLimit(100));
	}

}

# UserAPI

Moja implementacija BACKEND testa sposobnosti za Talent Camp od Outfit7.

## Obrazložitev

Naredil sem 2 API-ja RankingAPI in UserStateAPI ter pomožni razred UserState.
#### UserStateAPI
Vsebuje 2 metodi:
* receiveUserState
* returnUserState

**receiveUserState** sprejme objekt tipa UserState, ki vsebuje vse zahtevane podatke in ga shrani v
Datastore.

**returnUserState** sprejme ID in vrne UserState, ki ustreza ID-ju. Če ID ne obstaja v Datastore,
 sproži napako.

### Building the project with Maven

To build the project:

    mvn clean package

### Generating the openapi.json file

To generate the required configuration file `openapi.json`:

    mvn exec:java -DGetSwaggerDoc

### Deploying the sample API to App Engine

To deploy the sample API:

0. Invoke the `gcloud` command to deploy the API configuration file:

         gcloud service-management deploy openapi.json

0. Deploy the API implementation code by invoking:

         mvn appengine:deploy

    The first time you upload a sample app, you may be prompted to authorize the
    deployment. Follow the prompts: when you are presented with a browser window
    containing a code, copy it to the terminal window.

0. Wait for the upload to finish.

# UserAPI

Moja implementacija BACKEND testa sposobnosti za Talent Camp od Outfit7.

## Obrazložitev

Naredil sem 2 API-ja RankingAPI in UserStateAPI ter pomožni razred UserState.

#### UserStateAPI
Vsebuje 5 metod:
* receiveUserState
* returnUserState
* saveToDatastore
* iskUserStateOk
* createUserStateFromEntity

**receiveUserState** sprejme objekt tipa UserState, ga preveri s pomočjo iskUserStateOk 
in shrani z metodo saveToDatastore v Datastore.

**returnUserState** sprejme ID in vrne UserState, ki ustreza ID-ju. Če ID ne obstaja v Datastore,
 sproži napako.
 
**createUserStateFromEntity** pretvori rezultat poizvedbe tipa Entity v objekt UserState.

**saveToDatastore** shrani objekt tipa UserState v Datastore.

**iskUserStateOk** preveri, če objekt tipa UserState vsebuje vse atribute.

#### RankingAPI
Vsebuje metodo returnHighscores.

**returnHighscores** naredi poizvedbo v Datastore za 100 UserState-ov z najvišjim highscore-om ter vrne 
njihov username ter highscore. Vrne seznam Entity objektov. Če obstaja manj kot 100 elementov v
 Datastore-u, sklepam,  da vrne tiste elemente, ki obstajajo.
 
#### UserState

Razred, ki hrani vse zahtevane podatke iz navodil.

### Komentar
Zavedam se, da izvedba ni optimalna. Zaradi pomanjkanja izkušenj, časa in znanja mi ni uspelo
ustvariti boljše rešitve. 

##### Pomanjkljivosti :
* ni avtentikacije
* ni določljivega intervala sprejemanja
* preverjanje vhodnih podatkov je zelo osnovno
* povratne informacije so minimalne
* struktura projekta ni vredu
* projekt ni testiran
* dokumantacija je pomankljiva in v neustrezni obliki
* projekt se ne drži standardov

### Change project ID

1. Edit the file `backend/pom.xml`
2. For `<endpoints.project.id>`, replace the value `YOUR_PROJECT_ID` with your project ID.
3. Save your changes.

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

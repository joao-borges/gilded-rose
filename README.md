# gilded-rose
The Gilded Rose Expands by Joao Borges Caldeira

### Tech stack

* java 11
* spring-boot
* maven
* embedded Tomcat
* lombok (to avoid boilerplate code)

### Application Setup

Using the spring-boot-starter dependencies, it is easy and fast to bring up the application. 
Adding the `spring-boot-starter-web` dependency to the `pom.xml` file does most of the work.
It comes with most of the basic dependencies and spring-boot has the basic configurations embedded.

The `spring-boot-maven-plugin` enhance the application jar by adding all required dependencies inside, finding the application main class
and setting up the MANIFEST.MF file. Using that, running the application is possible just by executing `java -jar gilded-rose-1.0.jar`.

### Running the Application

Since it's built over spring-boot, it can be executed from command line by using the maven spring-boot plugin.

`mvn spring-boot:run` will run the application.

#### Requests to Endpoints

Requests can be executed from command line interfaces using cURL or, for convenience, using Postman with the `postman-collection.json` file provided.

For command line requests, here are examples:

* Token
```
curl --location --request POST 'http://localhost:8081/oauth/token' \
--header 'Content-Type: application/x-www-form-urlencoded' \
--data-urlencode 'password=secret' \
--data-urlencode 'username=user' \
--data-urlencode 'grant_type=password' \
-u buyer-client:password
```
* Get Inventory
```
curl --location --request GET 'http://localhost:8081/api/inventory'
```
* Buy Item
```
curl --location --request GET 'http://localhost:8081/api/buy/Item4' \
--header 'Authorization: Bearer <place token here>'
```

## Model

### Surge Pricing

To enable surge pricing, a simple system was created to record all inventory query requests with their timestamp.
Every minute, the current set of recorded requests is evaluated and the expired ones are removed (older than one hour). 
The decision is based if in the last hour more than 10 requests are still valid, and then surge pricing is enabled.

All requests to buy items go through the surge pricing evaluation, so if it is enabled, the price sent to the client is
increased by 10%. 

### Output data format

JSON was chosen as data format since is widely accepted and used in the industry. Most of all modern languages and devices
have native support for JSON, so this choice would make it easier for anyone consuming the API to understand and read the results.

### Authentication

A simple OAuth2 server is embedded together with the application, to provide the ability for generating tokens for buyer clients.

Every client who wants to buy an Item has to obtain an authorization token prior to that, so they can be properly authorized for that API.
Tokens expire after 3 minutes, in order to avoid long-living sessions for clients who does not complete the operation. 
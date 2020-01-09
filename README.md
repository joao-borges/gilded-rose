# gilded-rose
The Gilded Rose Expands by Joao Caldeira

### Tech stack

* java 11
* spring-boot
* maven
* embedded Tomcat
* lombok (to avoid boilerplate code)

### Setup

Using the spring-boot-starter dependencies, it is easy and fast to bring up the application. 
Adding the `spring-boot-starter-web` dependency to the `pom.xml` file does most of the work.
It comes with most of the basic dependencies and spring-boot has the basic configurations embedded.

The `spring-boot-maven-plugin` enhance the application jar by adding all required dependencies inside, finding the application main class
and setting up the MANIFEST.MF file. Using that, running the application is possible just by executing `java -jar gilded-rose-1.0.jar`.

### Surge Pricing

### Output data format

### Authentication
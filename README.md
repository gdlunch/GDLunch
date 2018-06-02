[![Build Status](https://travis-ci.org/Dominick1993/GD-lunch-Brno.svg?branch=master)](https://travis-ci.org/Dominick1993/GD-lunch-Brno)

# GD Lunch - Daily lunch menu aggregator

This project is supposed to aggregate daily menus from nearby restaurants into single web page.

This is just a spare time project.

## Why?

Because why not.

## How to build with Gradle

This project is using Gradle Wrapper.
Currently used gradle version is 4.3

```
./gradlew clean build
```

## How to run

In order to run the app you have to provide the `application.properties` in the root. [Here is just a taste of some common properties](https://docs.spring.io/spring-boot/docs/current/reference/html/common-application-properties.html)

Minimal properties that you should specify in order to successfully deploy GDLunch:

```properties
# DB properties:
spring.datasource.driver-class-name=com.whatever.db.driver
spring.datasource.url=jdbc:superDb://localhost/gdlunch
spring.datasource.username=username
spring.datasource.password=password

# Hibernate Configuration:
spring.data.jpa.repositories.enabled=true
spring.jpa.hibernate.ddl-auto=create
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.WhateverDBDialect
``` 

The build produces a single fat jar with bundled server, so the whole application is run just with:

```
java -jar web-<version>.jar
```

## Restaurant definitions

Restaurants are defined in the JSON file called `restaurants.json` that has to be present in the root folder along with the executable jar.

Here is the simple example:
```json
{
  "config": [
    {
      "parserClass": "com.labuda.gdlunch.parser.SomeParser",
      "refreshFrequency": "daily",
      "restaurants": [
        {
          "name": "restaurantName",
          "url": "https://url.to.the.restaurant.page/",
          "parserUrl": "https://url.to.the.source.of.menu/"
        }
      ]
    },
    {
      "parserClass": "com.labuda.gdlunch.parser.SomeWeeklyParser",
      "refreshFrequency": "weekly",
      "restaurants": [
        {
          "name": "restaurantName",
          "url": "https://url.to.the.restaurant.page/",
          "parserUrl": "https://url.to.the.source.of.menu/"
        }
      ]
    }
  ]
}
```

[![Build Status](https://travis-ci.org/Dominick1993/GD-lunch-Brno.svg?branch=master)](https://travis-ci.org/Dominick1993/GD-lunch-Brno)

# GD daily menu lunch aggregator

This project is supposed to aggregate daily menus from nearby restaurants into single web page.

This is just a spare time project.

## Why?

Because why not.

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

## Database backend

Project is currently tested only on locally deployed MySQL 5.7.20 Community Edition.

## How to build with Gradle

This project is using Gradle Wrapper.

Currently used gradle version is 4.3

```
./gradlew clean build
```

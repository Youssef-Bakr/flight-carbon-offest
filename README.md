# Flight Carbon Offest
_Note: This project is currently incomplete and as such the README may be out of date. Please check when this was last updated!_

[![PRs Welcome](https://img.shields.io/badge/PRs-welcome-brightgreen.svg?style=flat-square)](http://makeapullrequest.com)

## Description
To accurately calculate the amount of carbon generated from a flight requires expensive and proprietary data. This project is an attempt to offer a comparable, open-source alternative.

Currently Incomplete! Utilise crowdsourced, non-proprietary data _(from [OurAirports](https://ourairports.com/))_ to generate accurate carbon-to-be-offset amounts given an origin and destination airport pair derived from user input.

### Example _(so far)_:
![](example.gif)

## What's included?
* [**Airport.java**](https://github.com/followingell/flight_carbon_offest/blob/master/src/Airport.java): Class storing information related to all airports within [OurAirports](https://ourairports.com/) database containing getter, setter and toString methods.

* [**Utils.java**](https://github.com/followingell/flight_carbon_offest/blob/master/src/Utils.java): Helper class with generateAirports, selectAirport and getDistance methods.

## To do:
* ~Adapt data source away from [OpenFlights](https://openflights.org/) to [OurAirports](https://ourairports.com/data/) to remove licensing restrictions.~ _(Completed: 11th Jan 2020)_
* Fix issue when no IATA code is found within selectAirport method.

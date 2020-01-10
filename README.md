# Flight Carbon Offest
_Note: This project is currently incomplete and as such the README may be out of date. Please check when this was last updated!_

[![PRs Welcome](https://img.shields.io/badge/PRs-welcome-brightgreen.svg?style=flat-square)](http://makeapullrequest.com)

## Description
To accurately calculate the amount of carbon generated from a flight requires expensive and proprietary data. This project is an attempt to offer a comparable, open-source alternative.

Utilise the most up to date data from [OpenFlights](https://openflights.org/) to generate the amount of carbon to be offset given an origin and destination airport pair derived from user input.

### Example _(so far)_:
![](example.gif)

## What's included?
* [**Airport.java**](https://github.com/followingell/flight_carbon_offest/blob/master/src/Airport.java): Class storing information related to all airports within [OpenFlight's](https://openflights.org/) database containing both getter and toString methods.

* [**Utils.java**](https://github.com/followingell/flight_carbon_offest/blob/master/src/Utils.java): Helper class with generateAirports, selectAirport and getDistance methods.

## To do:
* Adapt data source away from [OpenFlights](https://openflights.org/) to [OurAirports](https://ourairports.com/data/) to remove licensing restrictions. **(In Progress)**
* Fix issue when no IATA code is found within selectAirport method.

## Licensing and disclaimer:
As per [OpenFlights](https://openflights.org/data.html#schedule):

_"The OpenFlights Airport, Airline, Plane and Route Databases are made available under the [Open Database License](http://opendatacommons.org/licenses/odbl/1.0/). Any rights in individual contents of the database are licensed under the [Database Contents License](http://opendatacommons.org/licenses/dbcl/1.0/). In short, these mean that you are welcome to use the data as you wish, if and only if you both acknowledge the source and and license any derived works made available to the public with a free license as well."_

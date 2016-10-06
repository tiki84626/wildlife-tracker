# _Hair Salon_

#### _Hair Salon is a web app that allows hair salons to keep track of stylists and their clients, 10/02/16_

#### By _**Andrew Malkin**_

## Description

_Hair Salon tracks clients appointment times which stylis they use and which salon they attend. It does all this using psql databases with Spakr and Velocity for the interface._

## Setup/Installation Requirements

* _Clone the hair-salon repository [here](https://github.com/tiki84626/hair-salon)._
* _Set up necessary Database and Tables:_
    * _make sure Postgres Server is running:_ `postgres`,
    * _open new terminal tab/window and connect to Postgres:_ `psql`,
    * _create database hair_salon (in sql):_ `CREATE DATABASE hair_salon;`,
    * _navigate to project directory in terminal and load tables:_ `psql hair_salon < hair_salon.sql`.
* _Brew or Scoop install gradle._
* _Navigate to project directory compile and run:_ `gradle run`.

## Known Bugs

_There are no known bugs._

## Technologies Used

_Hair Salon was created using Java, PostgreSQL, Spark, Velocity, and Gradle._

### License

*This software is licensed under MIT license.*

Copyright (c) 2016 **_Andrew P. Malkin_**

# VirtualTravel
[![Compiling](https://github.com/K-Amir/virtualtravel/actions/workflows/maven.yml/badge.svg)](https://github.com/K-Amir/virtualtravel/actions/workflows/maven.yml)

<br />
<div align="center">
  <a href="#">
    <img src="https://sc04.alicdn.com/kf/UTB8VHyqoL2JXKJkSanr7613lVXaw.png" width="180" alt="Logo" >
  </a>
  <h3 align="center">VIRTUALTRAVEL</h3>
  <p align="center">
    An awesome BackEnd for booking buses I guess
    <br />
    <a href="#"><strong>Explore the docs »</strong></a>
    <br />
    <br />
  </p>
</div>


## Getting Started

To run the application follow the next steps

### Running the containers

Execute the following docker compose command to run the app in containers
  ```sh
  docker compose up -d
  ```
After it you will have  access to the app throught the gateway in the port 8080, and you will have two instances of backweb and backempresa

## Usage

Those are the following endpoints of the application:

## Web - Endpoints

| Method   | URL                                      | Description                              |
| -------- | ---------------------------------------- | ---------------------------------------- |
| `POST`    | `/api/v0/bookings`                      | Create a new booking.                    |
#### Body
```
{
    "city" : "Madrid",
    "name" : "Jhon Doe",
    "phone" : "000000000",
    "email" : "jhon@doe.boot",
    "date" : "30-04-2022",
    "hour" : "19:30"
}
```
<br />
<br />
<br />
<br />

----

| Method   | URL                                      | Description                              |
| -------- | ---------------------------------------- | ---------------------------------------- |
| `GET`    | `api/v0/bookings/available/{city}?loweDate=30042022&upperDate=31042022&lowerHour=1900&upperHour=2000`| Get available bookings for a certain city|

| Param   | Description                               |
| -------- | ---------------------------------------- |
| `loweDate`    | The earliest date of the booking ( required ) |
| `upperDate`    | The  latest of the booking |
| `lowerHour`    | The earliest hour of the booking |
| `upperHour`    | The latest hour of the booking |


_Note: lowerDate param is required the request are optional_
#### Sample response
```
[
  {
    "destinationCity" : "Madrid",
    "departmentDate" : "30-04-2022",
    "departmentHour" : "19:30",
    "seatsAvailable" : 40
  },
  {
  "destinationCity" : "Madrid",
  "departmentDate" : "30-04-2022",
  "departmentHour" : "19:30",
  "seatsAvailable" : 40
}
]
```
<br />
<br />
<br />
<br />

----
| Method   | URL                                      | Description                              |
| -------- | ---------------------------------------- | ---------------------------------------- |
| `GET`    | `/api/v0/bookings/booked/{city}`         | Get the history of the bookings.         |

| Param   | Description                               |
| -------- | ---------------------------------------- |
| `loweDate`    | The earliest date of the booking ( required ) |
| `upperDate`    | The  latest of the booking |
| `lowerHour`    | The earliest hour of the booking |
| `upperHour`    | The latest hour of the booking |

| Headers   | Description                               |
| -------- | ---------------------------------------- |
| `Authorization`    | Add the Jwt token followed the Bearer prefix |

#### Sample response
```
[
  {
    "city" : "Madrid",
    "name" : "Jhon",
    "phone" : "00000000",
    "email" : "jhon@doe.boot"
    "date" : "30-04-2022",
    "hour" : "19:30",
  },
]
```
<br />
<br />
<br />
<br />

## Empresa - Endpoints


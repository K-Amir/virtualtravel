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

<br />
<br />

## Web - Endpoints
----
#### Web Endpoint - 1

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
#### Web Endpoint - 2

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
#### Web Endpoint - 3
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
----
#### Empresa Auth Endpoint - 1
| Method   | URL                                      | Description                              |
| -------- | ---------------------------------------- | ---------------------------------------- |
| `POST`    | `empresa/v0/auth`         | Register a new admin user         |

#### Sample body
```
{
    "email" : "admin@admin.admin",
    "password" : "secret",
}
```


<br />
<br />
<br />
<br />

----
#### Empresa Auth Endpoint - 2
| Method   | URL                                      | Description                              |
| -------- | ---------------------------------------- | ---------------------------------------- |
| `GET`    | `empresa/v0/auth/token`         | Returns the JWT Token after a correct log in         |

| Headers   | Description                               |
| -------- | ---------------------------------------- |
| `user`    | Specifies the username of the admin user |
| `password`    |  Add the password for the admin user |

#### Sample response
```
eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c
```

<br />
<br />
<br />
<br />

----
#### Empresa Auth Endpoint - 3
| Method   | URL                                      | Description                              |
| -------- | ---------------------------------------- | ---------------------------------------- |
| `GET`    | `empresa/v0/auth/token/{token}`         | Returns 200 OK if the token is correct         |




<br />
<br />
<br />
<br />

### The following endpoints all require this headers 
| Headers   | Description                               |
| -------- | ---------------------------------------- |
| `Authorization`    | The JWT token followed by Bearer prefix |

<br />
<br />


----
#### Empresa Bus Endpoint - 4


| Method   | URL                                      | Description                              |
| -------- | ---------------------------------------- | ---------------------------------------- |
| `POST`    | `empresa/v0/buses`         | Creates a bus for a travel         |

#### Sample body
```
{
    "city" : "Madrid",
    "date" : "30-04-2022",
    "hour" : "19:30",
    "availableSeats: 40
}
```


<br />
<br />
<br />
<br />

----
#### Empresa Bus Endpoint - 5
| Method   | URL                                      | Description                              |
| -------- | ---------------------------------------- | ---------------------------------------- |
| `GET`    | `empresa/v0/buses`         | Finds all the buses         |

#### Sample response
````
[
  {
    "id" : 1,
    "hour": "19:30",
    "date" : "03-05-2022",
    "city" : "Madrid",
    "availableSeats": 40,
  },
  {
    "id" : 2,
    "hour": "19:30",
    "date" : "03-05-2022",
    "city" : "Barcelona",
    "availableSeats": 15,
  },
  
]
````


<br />
<br />
<br />
<br />

----
#### Empresa Bus Endpoint - 6
| Method   | URL                                      | Description                              |
| -------- | ---------------------------------------- | ---------------------------------------- |
| `GET`    | `empresa/v0/buses/{id}`         | Finds certain bus by id         |

#### Sample response
````
{
  "id" : 1,
  "hour": "19:30",
  "date" : "03-05-2022",
  "city" : "Madrid",
  "availableSeats": 40,
}
````


<br />
<br />
<br />
<br />

----
#### Empresa Bus Endpoint - 7
| Method   | URL                                      | Description                              |
| -------- | ---------------------------------------- | ---------------------------------------- |
| `DELETE`    | `empresa/v0/buses/{id}`         | Delete bus by certain id ( not recommended )   |

_Method due to lack of implementation ( if there's users who booked for this bus no cancellation email will be sent )_


<br />
<br />
<br />
<br />

----
#### Empresa Booking Endpoint - 8
| Method   | URL                                      | Description                              |
| -------- | ---------------------------------------- | ---------------------------------------- |
| `POST`    | `empresa/v0/bookings`         | Created a booking    |

#### Same body as Web Booking Endpoint


<br />
<br />
<br />
<br />

----
#### Empresa Incidence Endpoint - 9
| Method   | URL                                      | Description                              |
| -------- | ---------------------------------------- | ---------------------------------------- |
| `POST`    | `empresa/v0/incidences`         | Created an incidence    |

_Note: If empresa does not work, use api/v0/incidences instead._

#### Sample body
```
{
    "bus_id" : 1,
    "reason" : "Bad weather conditions",
}
```

<br />
<br />
<br />
<br />

----
#### Empresa Incidence Endpoint - 10
| Method   | URL                                      | Description                              |
| -------- | ---------------------------------------- | ---------------------------------------- |
| `GET`    | `empresa/v0/incidences`         | Returns all the incidences    |
| `GET`    | `empresa/v0/incidences/{id}`         | Returns specified incidence by id    |
| `DELETE`    | `empresa/v0/incidences/{id}`         | Deletes incidence by id    |


#### Sample response
````
[
  {
    "id" : 1,
     "busId" 1,
    "reason" : "Bad weather conditions",
  },
  {
    "id" : 2,
    "busId" 2,
    "reason" : "Driver missing",
  },
  
]
````









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

### Web

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
----

| Method   | URL                                      | Description                              |
| -------- | ---------------------------------------- | ---------------------------------------- |
| `GET`    | `/available/{city}?loweDate=30042022&upperDate=31042022&lowerHour=1900&upperHour=2000`| Get available bookings for a certain city|
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
----



| `GET`    | `/api/posts/28`                          | Retrieve post #28.                       |
| `PATCH`  | `/api/posts/28`                          | Update data in post #28.                 |
| `POST`   | `/api/posts/28/comments`                 | Add comment to post #28.                 |
| `GET`    | `/api/posts/28/comments?status=approved&limit=10&page=4` | Retrieve page 4 of the comments for post #28 which are approved, with 10 comments per page. |
| `DELETE` | `/api/posts/28/comments/1987` or `/api/comments/1987` | Delete comment #1987.                    |
| `GET`    | `/api/users?active=true&sort=username&direction=asc&search=nodes` | Search for "nodes" in active users, sorted  by username ascendingly. |


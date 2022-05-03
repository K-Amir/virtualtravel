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

ojo
| `POST`   | `/api/posts`                             | Create a new post.                       |
| `GET`    | `/api/posts/28`                          | Retrieve post #28.                       |
| `PATCH`  | `/api/posts/28`                          | Update data in post #28.                 |
| `POST`   | `/api/posts/28/comments`                 | Add comment to post #28.                 |
| `GET`    | `/api/posts/28/comments?status=approved&limit=10&page=4` | Retrieve page 4 of the comments for post #28 which are approved, with 10 comments per page. |
| `DELETE` | `/api/posts/28/comments/1987` or `/api/comments/1987` | Delete comment #1987.                    |
| `GET`    | `/api/users?active=true&sort=username&direction=asc&search=nodes` | Search for "nodes" in active users, sorted  by username ascendingly. |


# HikeOn Assessment Application

## Overview

This repository contains the source code for the HikeOn Assessment application, which is a Java Spring Boot application built with Maven and MongoDB. The application provides an API for user registration and includes validation for user details.

## Table of Contents

- [Features](#features)
- [Technologies Used](#technologies-used)
- [Getting Started](#getting-started)
    - [Prerequisites](#prerequisites)
    - [Installation](#installation)
- [Usage](#usage)
- [API Endpoints](#api-endpoints)
- [Error Handling](#error-handling)


## Features

- User registration with validation checks (age, duplicate email, etc.).
- Spring Boot RESTful API.
- Integration with MongoDB for data storage.
- Exception handling for various scenarios.

## Technologies Used

- Java
- Spring Boot
- Maven
- MongoDB

## Getting Started

### Prerequisites

Ensure you have the following installed:

- Java (JDK 17)
- Maven
- MongoDB

### Installation


1. Clone the repository:

    ```bash
    git clone https://github.com/Gaurang135/HikeOn-Assessment.git
    ```

2. Navigate to the project directory:

    ```bash
    cd HikeOn-Assessment
    ```

3. Build the project using Maven:

    ```bash
    mvn clean install
    ```
4. Run the application
    ```bash
    java -jar target/hikeOn-0.0.1-SNAPSHOT.jar
    ```
### Usage
The application runs on port 8081. You can access the API using the base URL: 
    ```
    http://localhost:8081/api
    ```
## API Endpoints

### Register
* Endpoint: "/api/Register"
* Method: POST
* Request Body:
```json
{
  "name": "John Doe",
  "email": "john.doe@example.com",
  "dateOfBirth": "1990-01-01",
  "occupation": "SOFTWARE_ENGINEER"
}
```
### Error Handling
The application handles the following exceptions:
* UserUnderageException: When the user is underage.
* RegisteredMailException: When the email is already registered.
* DuplicateUserException: When a duplicate user is detected.

In case of an error, the API responds with an HTTP 400 Bad Request status and an error message.

## Contribution

Contributions are welcome! Feel free to raise issues or submit pull requests for any improvements or additional features.




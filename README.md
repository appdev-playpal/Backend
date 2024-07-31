Backend API - PlayPal
===========

Overview
--------

This is a Java-based backend API that provides functionality for managing hobbies. The API consists of two main message handlers:

### HandleHobbyMessage

This message handler is responsible for adding a new hobby to the repository.

### HandleHobbyListMessage

This message handler is responsible for sending the entire list of hobbies to the client.

API Endpoints
-------------

### Add Hobby

* **Endpoint:** `/addHobby`
* **Method:** `POST`
* **Request Body:** `Hobby` object (JSON)

### Get Hobby List

* **Endpoint:** `/getHobbyList`
* **Method:** `GET`

Message Handlers
----------------

### HandleHobbyMessage

This message handler is responsible for adding a new hobby to the repository. It takes a `Hobby` object as input and adds it to the repository.

### HandleHobbyListMessage

This message handler is responsible for sending the entire list of hobbies to the client. It retrieves the list of hobbies from the repository and sends it as a JSON array.

Technology Stack
----------------

* **Programming Language:** Java
* **Framework:** SpringBoot

Development
-----------

To develop and test the backend API, follow these steps:

1. Clone the repository
2. Install the required dependencies
3. Start the backend server
4. Use a tool like Postman or cURL to test the API endpoints

Contributing
------------

Contributions to the backend API are welcome! If you'd like to contribute, please follow these steps:

1. Fork the repository
2. Make your changes
3. Submit a pull request

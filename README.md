# Spring boot api

For downloading and uploading pdf files. Please see below limitations and comments.

## Table of Contents

- [Getting Started](#getting-started)
    - [Prerequisites](#prerequisites)
    - [Installation](#installation)
- [API Endpoints](#api-endpoints)
- [Comments](#comments)

## Getting Started

### Prerequisites

- Java 17
- Maven
- Spring boot 3.2.5

### Installation

1. Build the project: `mvn clean install`
1. Run the application: `mvn clean spring-boot:run`

## API Endpoints

### Upload a PDF File

- **Endpoint**: `POST /upload`
- **Description**: Uploads a PDF file to the in-memory storage.
- **Request Body**: `multipart/form-data`
    - `file`: PDF file to upload

#### Example Request
```bash
curl -X POST -F "file=@/path/to/your-file.pdf" http://localhost:8080/upload
```

### Download a PDF File

- **Endpoint**: `GET /download`
- **Description**: Downloads a PDF file from the in-memory storage.
- **Query parameters**: 
  - `fileName`: Name of the PDF file to download
  - `filePath`: Path to folder to download PDF to

#### Example Request
```bash
curl -X GET -F fileName="myfile.pdf" -F filePath="/path/for/download" http://localhost:8080/download 
```

### Get all pdfs

- **Endpoint**: `GET /pdfs`
- **Description**: Get all pdfs currently uploaded, by name.

#### Example Request
```bash
curl -X GET http://localhost:8080/pdfs
```


### Comments and limitations
Because of time restraints, Spring boot security is not added yet. It is important to keep the API safe.
Unit tests are not added yet either.

DELETE and UPDATE requests are not implemented.

Filenames with " " (space) cannot be downloaded with curl as of now, but can be through Postman. 
# Spring Boot Microservices

This repository contains a set of Spring Boot microservices that are part of an e-commerce platform. The project includes the following services:

- **Basket Service**: Manages user shopping baskets.
- **Catalog Service**: Provides product catalogs for users.
- **Order Service**: Manages customer orders.
- **Common Library**: Shared library for common functionality across services.

## Services Overview

### Basket Service
This service handles the user’s shopping basket. It supports adding, removing, and listing items in the user's basket.

**Path**: `basket-service/`

### Catalog Service
The catalog service manages product details and categories. It exposes APIs to retrieve product information and categories.

**Path**: `catalog-service/`

### Order Service
Order service handles the creation and management of customer orders. It is responsible for processing and tracking orders.

**Path**: `order-service/`

### Common Library
The common library provides shared utilities and code used across the various microservices, such as error handling, logging, and utility methods.

**Path**: `common-lib/`

## Getting Started

### Prerequisites

- **Java 17** or higher
- **Maven**
- **Docker** (optional, for running with Docker Compose)

### Running the Services Locally

1. Clone the repository:

    ```bash
    git clone <your-repository-url>
    cd <your-repository-directory>
    ```

2. Navigate to each microservice directory and build the project using Maven:

    ```bash
    cd basket-service
    mvn clean install
    cd ../catalog-service
    mvn clean install
    cd ../order-service
    mvn clean install
    ```

3. Alternatively, use Docker Compose to run all services together:

    ```bash
    docker-compose up --build
    ```

   This will start all the microservices in Docker containers as specified in the `docker-compose.yml` file.

### Accessing the Services

- **Basket Service**: `http://localhost:8081`
- **Catalog Service**: `http://localhost:8080`
- **Order Service**: `http://localhost:8082`

### Docker Compose

The `docker-compose.yml` file in the root of the repository allows you to run the services as Docker containers. It will automatically build and start the services when you use the following command:

```bash
docker-compose up --build

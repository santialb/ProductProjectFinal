# Final Products API

This is a Products API built with Spring Boot. It provides CRUD functionality for managing products and categories with JWT-based authentication. 
The application includes features like caching, profanity filtering, comprehensive error handling, and automated unit testing for key services.

## Table of Contents

- [Technologies Used](#technologies-used)
- [Project Structure](#project-structure)
- [Endpoints](#endpoints)
- [Database Configuration](#database-configuration)
- [Unit Testing](#unit-testing)

## Technologies Used
- Java 17
- Spring Boot 3.3.5
- Spring Data JPA
- Hibernate (JPA implementation)
- MySQL
- JWT (for authentication)
- Spring Cache (with ConcurrentMapCacheManager)
- Maven
- Lombok
- JUnit and Mockito (for testing)

## Project Structure 
```
├── pom.xml                                 # Project dependencies and build configurations
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/
│   │   │       └── example/
│   │   │           └── finalExam/
│   │   │               ├── FinalExamApplication.java   # Main application entry point
│   │   │               ├── cache/                      # Caching configurations
│   │   │               ├── configuration/              # API key reader configuration
│   │   │               ├── controllers/                # REST controllers for product and category management
│   │   │               ├── exceptions/                 # Custom exception classes
│   │   │               ├── miscellaneous/              # Utility classes and query models
│   │   │               ├── models/                     # Domain models and DTOs
│   │   │               ├── repositories/               # Repository interfaces
│   │   │               ├── security/                   # Security and JWT configurations
│   │   │               ├── services/                   # Business logic for product management
│   │   │               └── validators/                 # Validation logic including profanity check
│   │   └── resources/
│   │       └── application.properties                 # Application configuration (e.g., database connection)
│   └── test/
│       └── java/
│           └── com/
│               └── example/
│                   └── finalExam/
│                       ├── DeleteProductServiceTest.java
│                       ├── FinalExamApplicationTests.java
│                       ├── GetCategoriesServiceTest.java
│                       ├── GetProductServiceTest.java
│                       └── GetProductsServiceTest.java
└── target/

```

## Endpoints
Here are the main REST API endpoints:

| Method | URL              | Description                  |
|--------|-------------------|------------------------------|
| POST   | `/login`         | Authenticate and get JWT     |
| POST   | `/createNewUser` | Register a new user          |
| GET    | `/products`      | Retrieve all products        |
| GET    | `/product/{id}`  | Retrieve a product by ID     |
| POST   | `/product`       | Create a new product         |
| PUT    | `/product/{id}`  | Update an existing product   |
| DELETE | `/product/{id}`  | Delete a product by ID       |
| GET    | `/categories`    | List all categories          |

### Example of Request/Response
- **POST /product**
  - Request Body:
    ```json
    {
      "name": "Example Product",
      "description": "A sample product",
      "price": 29.99,
      "manufacturer": "Example Corp",
      "category": "Electronics",
      "region": "US"
    }
    ```
  - Response Body:
    ```json
    {
      "id": "1",
      "name": "Example Product",
      "description": "A sample product",
      "price": 29.99,
      "manufacturer": "Example Corp",
      "category": "Electronics"
    }
    ```

- **GET /products**
  - Response Body:
    ```json
    [
      {
        "id": "1",
        "name": "Example Product",
        "description": "A sample product",
        "price": 29.99,
        "manufacturer": "Example Corp",
        "category": "Electronics"
      },
      {
        "id": "2",
        "name": "Another Product",
        "description": "Another example product",
        "price": 19.99,
        "manufacturer": "Another Corp",
        "category": "Home Goods"
      }
    ]
    ```

## Database Configuration
Make sure to set up your MySQL database and update the `application.properties` file with the correct credentials:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/your_database
spring.datasource.username=your_username
spring.datasource.password=your_password
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
  ```
## Unit Testing

Unit tests are implemented for the service layer using Mockito. Tests include creating, updating, deleting, and retrieving products.

Here are the test classes:

- DeleteProductServiceTest.java
- GetCategoriesServiceTest.java
- GetProductServiceTest.java
- GetProductsServiceTest.java
To run the tests, use the following Maven command:
```
 bash $ mvn test
```

🍽 Recipe Management API
Production-Ready REST Backend using Spring Boot
📌 Project Summary

The Recipe Management API is a RESTful backend application built using Spring Boot, Spring Data JPA, and MySQL.

The system automatically ingests recipe data from a structured JSON file at application startup and exposes well-designed REST endpoints to retrieve, sort, and manage recipe data.

This project demonstrates strong backend fundamentals including:

REST API design

Layered architecture

ORM-based database interaction

JSON parsing and deserialization

Query optimization using sorting and pagination

Exception handling and clean response formatting

🚀 Core Functionalities
✔ Data Ingestion

Parses a JSON dataset using Jackson ObjectMapper

Converts JSON into domain entities

Persists data using saveAll() via JPA

Executes automatically using CommandLineRunner

✔ Recipe Retrieval

Retrieve all recipes

Retrieve recipe by ID

Retrieve top-rated recipes with configurable limit

Results sorted using database-level ordering (ORDER BY rating DESC)

✔ Recipe Management

Create new recipes

Delete recipes

Server-side validation for required fields

✔ Structured API Response

All responses follow a consistent format:

{
  "data": [ ... ]
}

This ensures extensibility for adding metadata in the future (pagination, status codes, etc.).

🛠 Technology Stack
Layer	Technology
Language	Java 23
Framework	Spring Boot
ORM	Hibernate (JPA)
Database	MySQL 8
JSON Processing	Jackson
Build Tool	Maven
Testing	Postman
🏗 Architecture Overview

The project follows a clean layered architecture:

Controller → Service → Repository → Database
🔹 Controller Layer

Handles HTTP requests and response formatting.

🔹 Service Layer

Contains business logic:

Validation

Sorting logic

Limit handling

Total time calculation

🔹 Repository Layer

Uses Spring Data JPA to:

Auto-generate SQL queries

Handle pagination and sorting

Perform CRUD operations

🔹 Database Layer

MySQL stores structured recipe data including:

Rating

Cooking times

Nutritional information (JSON type)

This separation ensures:

Maintainability

Testability

Scalability

Clean code organization

📂 API Endpoints
1️⃣ Get All Recipes
GET /recipes
2️⃣ Get Top Rated Recipes
GET /recipes/top?limit=5

Logic:

Sort by rating in descending order

Apply dynamic limit using PageRequest

Default limit: 5

Internally generates SQL:

SELECT * FROM recipes
ORDER BY rating DESC
LIMIT 5;
3️⃣ Get Recipe By ID
GET /recipes/{id}
4️⃣ Create Recipe
POST /recipes

Validates required fields before persisting.

5️⃣ Delete Recipe
DELETE /recipes/{id}
🔄 JSON Parsing Strategy

JSON file stored in resources/

Read using ClassPathResource

Deserialized using:

ObjectMapper.readValue(InputStream, new TypeReference<List<Recipe>>() {})

Persisted using repository.saveAll(recipes)

Why TypeReference?

Because Java uses type erasure, and Jackson requires explicit generic type information to correctly deserialize List<Recipe>.

⚙️ Configuration
spring.datasource.url=jdbc:mysql://127.0.0.1:3308/recipe_db
spring.jpa.hibernate.ddl-auto=update
spring.jackson.property-naming-strategy=SNAKE_CASE
🧠 Engineering Decisions

✔ Used derived query methods for clean and readable repository logic
✔ Applied PageRequest for scalable query limiting
✔ Implemented global exception handling for structured error responses
✔ Maintained separation of concerns across layers
✔ Ensured startup data loading is idempotent-ready

🧪 Testing Strategy

Tested all endpoints using Postman

Verified SQL queries using Hibernate logs

Validated sorting and limiting logic

Handled edge cases (invalid ID, missing fields)

📈 Scalability & Improvements

Future enhancements could include:

Pagination metadata (total count, page number)

DTO layer to decouple entity exposure

JWT-based authentication

Caching (Redis)

API documentation via Swagger/OpenAPI

Docker containerization

Cloud deployment (AWS / GCP / Azure)

💡 What This Project Demonstrates

Strong understanding of Spring Boot ecosystem

Ability to design RESTful services

Experience with ORM and relational databases

Debugging production-level issues (port mismatch, dialect detection, authentication errors)

Clean coding practices and structured backend design

👨‍💻 Developer

Karan Kumar D
B.Tech – Information Technology
Backend & Full Stack Developer

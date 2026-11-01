# Financial Market Risk Assessment and Capital Allocation Platform (FMR-CAP)

## Project Information

- **Student Name:** IRAKIZA Fils Ben
- **Student ID:** 23224
- **Course:** INSY 8322 – Web Technology and Internet
- **Academic Year:** 2026–2027
- **Project Type:** Individual Mid-Term Project

## Project Description

The Financial Market Risk Assessment and Capital Allocation Platform (FMR-CAP) is a Spring Boot REST API application designed to manage financial market risk assessments, risk analysts, investors, trading desks, asset classes, and capital allocations.

The application uses PostgreSQL for data persistence and Spring Data JPA for database operations.

## Technologies Used

- Java 21
- Spring Boot
- Spring Data JPA
- PostgreSQL
- Maven
- REST API
- Postman
- Git and GitHub

## Project Structure

```text
src/main/java/com/ytfa/fmrcap/
├── controller/
├── entity/
├── repository/
└── service/
```

## Database Configuration

The application uses PostgreSQL.

Database name:

`fmrcap_db`

Configure the following environment variables before running the application:

- `DB_URL`
- `DB_USERNAME`
- `DB_PASSWORD`

Never publish your actual database password.

## How to Run the Project

1. Install Java 21.
2. Install PostgreSQL.
3. Create the database `fmrcap_db`.
4. Configure the database environment variables.
5. Open the project directory in a terminal.
6. Run:

```bash
mvn clean install
```

7. Start the application:

```bash
mvn spring-boot:run
```

The application runs on:

`http://localhost:8080`

## API Testing

API endpoints are tested using Postman.

The Postman collection is included in the project root directory.

## Developer

**IRAKIZA Fils Ben**

Student ID: 23224

Project: Financial Market Risk Assessment and Capital Allocation Platform (FMR-CAP)
# Finance Tracker

A personal finance tracking REST API built with Spring Boot. Inspired by a personal Excel spreadsheet used to track daily expenses and income, this application brings those records into a structured, multi-user backend with full CRUD support.

> **Status:** MVP — core tables and CRUD APIs are functional.

---

## Features

- **User authentication** — Register, login, and token refresh via JWT
- **Expense tracking** — Record expenses with amount, item name, description, date, category, and source
- **Income tracking** — Record income entries with amount, name, date, and category
- **Expense categories** — User-defined categories for organizing expenses
- **Income categories** — User-defined categories for organizing income
- **Expense sources** — Track which payment method or account was used (e.g. wallet, credit card)
- **User isolation** — All data is scoped to the authenticated user
- **Soft deletes** — Records are marked as deleted rather than removed
- **Audit fields** — Automatic `created_at` and `updated_at` timestamps
- **Internationalization** — Validation and error messages in English and Turkish

---

## Tech Stack

| Layer | Technology |
|---|---|
| Language | Java 21 |
| Framework | Spring Boot 4.0.3 |
| Security | Spring Security + JWT (jjwt 0.11.5) |
| Persistence | Spring Data JPA + Hibernate |
| Database | PostgreSQL |
| Migrations | Flyway |
| Mapping | ModelMapper 3.2.6 |
| Validation | Jakarta Bean Validation |
| Build | Maven |
| Boilerplate | Lombok |

---

## Prerequisites

- Java 21
- PostgreSQL
- Maven 3.6+ (or use the included `./mvnw` wrapper)

---

## Getting Started

### 1. Clone the repository

```bash
git clone https://github.com/orhangazibarak/finance-tracker.git
cd finance-tracker
```

### 2. Create the database

```bash
createdb finance_tracker
```

### 3. Set environment variables

The application reads all sensitive configuration from environment variables.

```bash
export DB_URL=jdbc:postgresql://localhost:5432/finance_tracker
export DB_USERNAME=postgres
export DB_PASSWORD=your_password
export JWT_SECRET=your_base64_encoded_secret_key
export JWT_EXPIRATION=3600000          # Access token TTL in ms (e.g. 1 hour)
export JWT_REFRESH_EXPIRATION=604800000  # Refresh token TTL in ms (e.g. 7 days)
```

### 4. Build and run

```bash
./mvnw spring-boot:run
```

Flyway will automatically apply all database migrations on startup. The server starts on `http://localhost:8080`.

---

## API Reference

All endpoints under `/expense`, `/income`, `/category`, `/income_category`, and `/expense-source` require a valid JWT:

```
Authorization: Bearer <access_token>
```

### Authentication

| Method | Endpoint | Description |
|---|---|---|
| POST | `/users/register` | Create a new user account |
| POST | `/users/login` | Login and receive access + refresh tokens |
| POST | `/users/refresh` | Exchange refresh token for new access token |

### Expenses

| Method | Endpoint | Description |
|---|---|---|
| GET | `/expense` | List all expenses for the authenticated user |
| GET | `/expense/{id}` | Get a single expense |
| POST | `/expense` | Create or update an expense |
| DELETE | `/expense/{id}` | Delete an expense |

### Income

| Method | Endpoint | Description |
|---|---|---|
| GET | `/income` | List all income records |
| GET | `/income/{id}` | Get a single income record |
| POST | `/income` | Create or update an income record |
| DELETE | `/income/{id}` | Delete an income record |

### Expense Categories

| Method | Endpoint | Description |
|---|---|---|
| GET | `/category` | List all expense categories |
| GET | `/category/{id}` | Get a single expense category |
| POST | `/category` | Create an expense category |
| DELETE | `/category/{id}` | Delete an expense category |

### Income Categories

| Method | Endpoint | Description |
|---|---|---|
| GET | `/income_category` | List all income categories |
| GET | `/income_category/{id}` | Get a single income category |
| POST | `/income_category` | Create an income category |
| DELETE | `/income_category/{id}` | Delete an income category |

### Expense Sources

| Method | Endpoint | Description |
|---|---|---|
| GET | `/expense-source` | List all expense sources |
| GET | `/expense-source/{id}` | Get a single expense source |
| POST | `/expense-source` | Create an expense source |
| DELETE | `/expense-source/{id}` | Delete an expense source |

---

## Database Schema

```
users
  id, full_name, email (unique), password, created_at, updated_at, is_deleted

expense_categories
  id, name, user_id → users, created_at, updated_at, is_deleted

income_categories
  id, name, user_id → users, created_at, updated_at, is_deleted

sources (expense sources)
  id, name, user_id → users, created_at, updated_at, is_deleted

expenses
  id, amount, good (item name), description, expense_date,
  user_id → users, category_id → expense_categories, source_id → sources,
  created_at, updated_at, is_deleted

incomes
  id, amount, name, income_date,
  user_id → users, category_id → income_categories,
  created_at, updated_at, is_deleted
```

Schema migrations are versioned under `src/main/resources/db/migration/` and managed by Flyway.

---

## Project Structure

```
src/main/java/com/example/finance_tracker/
├── controller/       REST controllers
├── service/          Service interfaces and implementations
├── entity/           JPA entities (BaseEntity with auditing)
├── repository/       Spring Data JPA repositories
├── dto/              Request/response DTOs
├── config/           Security, JWT, and application config
└── exception/        Global exception handler and custom exceptions

src/main/resources/
├── application.properties
├── messages.properties       (English)
├── messages_tr.properties    (Turkish)
└── db/migration/             Flyway SQL migrations
```

---

## Quick Example

```bash
# Register
curl -X POST http://localhost:8080/users/register \
  -H "Content-Type: application/json" \
  -d '{"fullName":"Jane Doe","email":"jane@example.com","password":"secret123"}'

# Login
curl -X POST http://localhost:8080/users/login \
  -H "Content-Type: application/json" \
  -d '{"email":"jane@example.com","password":"secret123"}'

# Create an expense (use the token from login)
curl -X POST http://localhost:8080/expense \
  -H "Authorization: Bearer <access_token>" \
  -H "Content-Type: application/json" \
  -d '{"amount":12.50,"good":"Coffee","expenseDate":"2026-03-29","categoryId":1,"sourceId":1}'
```
# CRUD-system (Auth Module) 🚀

A Java-based authentication module built with **Clean Architecture**, **Domain-Driven Design (DDD)**, and **SOLID** principles. This backend handles user authentication using **JWT access** and **refresh tokens**. It's the foundation for a full-featured CRUD system (next steps: Post entity).

## 🛠️ What It Is

- **Layered architecture** with clear boundaries:
  - `adapter` layer: framework-specific components (e.g., web controllers, PostgreSQL adapters)
  - `domain` layer: core entities and business logic
  - `usecase` layer: application workflows (e.g., authentication, token handling)
- **Authentication flow**:
  - User signs up and logs in with credentials
  - System issues a **short-lived JWT access token** + **long-lived refresh token**
  - Refresh token endpoint allows renewing access tokens without re-login
- **Security design**:
  - Tokens are validated and stored securely via adapters
  - Clean separation ensures no business logic leaks into infrastructure

## 🔧 Technologies & Stack

- **Java 17**
- **Spring Boot**
- **PostgreSQL**
- **JSON Web Tokens (JWT)**
- **Maven** for dependency management and build

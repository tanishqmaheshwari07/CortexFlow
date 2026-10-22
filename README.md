# AutoFlow — Distributed Workflow Automation Platform

AutoFlow is a multi-tenant workflow automation platform designed to manage workflows, approval processes, and background jobs. It uses Java and Spring Boot for backend development, PostgreSQL for persistent storage, and RabbitMQ for job-queue infrastructure.

## Features

- **Multi-tenant architecture:** Organize workflows and related resources by tenant.
- **Workflow management:** Create and retrieve workflows.
- **Workflow steps:** Manage steps associated with workflows.
- **Approval management:** Create approval requests, perform approval actions, and view approval history.
- **Job management:** Create and retrieve jobs and inspect queued jobs.
- **Database migrations:** Manage schema changes using Flyway.
- **Message queue infrastructure:** RabbitMQ queue and exchange configuration for background-job processing.

## Tech Stack

| Technology | Purpose |
|---|---|
| Java 21 | Backend programming language |
| Spring Boot | Application framework |
| Spring Data JPA / Hibernate | Database persistence |
| PostgreSQL | Relational database |
| Flyway | Database migrations |
| RabbitMQ | Message broker and queue infrastructure |
| Maven Wrapper | Build and dependency management |
| Docker | Running RabbitMQ locally |

## Architecture Overview

```text
Client
  |
  v
Spring Boot REST API
  |
  +---- Workflow Management
  |
  +---- Approval Management
  |
  +---- Job Management
  |
  +---- Spring Data JPA / Hibernate
  |                |
  |                v
  |            PostgreSQL
  |
  +---- RabbitMQ Configuration
                   |
                   v
          autoflow.jobs.exchange
                   |
                   v
            autoflow.jobs.queue
```

**Note:** RabbitMQ queue infrastructure is configured. Asynchronous job publishing and worker-based execution are future implementation steps unless added separately.

## Prerequisites

Install the following before running AutoFlow:

- Java Development Kit (JDK) 21
- Docker Desktop
- PostgreSQL
- Git

## Getting Started

### 1. Clone the repository

```bash
git clone https://github.com/tanishqmaheshwari07/CortexFlow.git
cd CortexFlow
```

### 2. Create the PostgreSQL database

Create a PostgreSQL database named `autoflow`.

```sql
CREATE DATABASE autoflow;
```

Configure the database credentials using environment variables rather than committing passwords to the repository.

### 3. Configure application properties

In `src/main/resources/application.properties`, configure:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/autoflow
spring.datasource.username=${DB_USERNAME}
spring.datasource.password=${DB_PASSWORD}

spring.rabbitmq.host=localhost
spring.rabbitmq.port=5672
spring.rabbitmq.username=${RABBITMQ_USERNAME:guest}
spring.rabbitmq.password=${RABBITMQ_PASSWORD:guest}
```

Set the environment variables for your local environment before starting the application. Update the RabbitMQ credentials if your local broker uses different credentials.

### 4. Start RabbitMQ with Docker

```bash
docker run -d --name autoflow-rabbitmq \
  -p 5672:5672 \
  -p 15672:15672 \
  rabbitmq:4-management
```

If the container already exists but is stopped, start it instead:

```bash
docker start autoflow-rabbitmq
```

RabbitMQ Management UI:

http://localhost:15672

The default local-development credentials for a fresh RabbitMQ container are `guest` / `guest`.

### 5. Run the application

On Windows PowerShell:

```powershell
$env:DB_USERNAME = "postgres"
$env:DB_PASSWORD = "your-local-postgresql-password"
$env:RABBITMQ_USERNAME = "guest"
$env:RABBITMQ_PASSWORD = "guest"

.\mvnw.cmd spring-boot:run
```

Replace the database password with your actual local PostgreSQL password.

The application runs on:

http://localhost:8080

Flyway validates and applies available database migrations during application startup.

## Job Queue

The current RabbitMQ configuration declares:

- **Queue:** `autoflow.jobs.queue`
- **Exchange:** `autoflow.jobs.exchange`
- **Routing key:** `autoflow.jobs.routing-key`

Verify queues using:

```bash
docker exec autoflow-rabbitmq rabbitmqctl list_queues -p /
```

## Build and Test

Run the Maven verification lifecycle:

```powershell
.\mvnw.cmd clean verify
```

Run the application:

```powershell
.\mvnw.cmd spring-boot:run
```

## Project Status

Implemented areas include workflow management, workflow steps, approval management, basic job management, database migrations, and RabbitMQ queue configuration.

**Planned improvements:**
- RabbitMQ job publishing and worker-based execution
- Retry and failure-handling mechanisms
- Additional automated tests
- Further workflow execution capabilities

## Security

- Do not commit database passwords, API keys, or other secrets.
- Use environment variables or a suitable secrets manager for sensitive configuration.
- Use dedicated credentials and appropriate access controls outside local development.

## License

No license has been specified yet.

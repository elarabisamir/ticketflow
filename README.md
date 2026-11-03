# TicketFlow

**A hands-on reference project for learning Spring Cloud and microservices, built around a real-world ticket booking platform.**

TicketFlow is an open-source educational project that takes you from
"what is a microservice?" to building resilient, event-driven, observable
distributed systems with the Spring ecosystem.

Instead of yet another e-commerce demo, it tackles problems that naturally
require distributed-systems patterns: limited seat inventory, concurrent
bookings, unreliable payment providers, and asynchronous notifications.

## What you will learn

- **Service discovery & routing**: Eureka and Spring Cloud Gateway
- **Centralized configuration**: Spring Cloud Config with dynamic refresh
- **Inter-service communication**: declarative REST clients with OpenFeign
- **Resilience**: circuit breaker, retry, timeout, bulkhead and fallbacks (Resilience4J)
- **Event-driven architecture**: asynchronous messaging with Apache Kafka
- **Distributed transactions**: Saga pattern, Outbox pattern, idempotency
- **Containerization**: Docker and Docker Compose
- **AI integration** *(later)*: natural-language search and RAG assistant with Spring AI

## Architecture

| Module | Role | Key technology | Status |
|---|---|---|---|
| `customer-service` | Customer profiles (CRUD) | Spring Web, JPA | Done |
| `api-gateway` | Single entry point and routing to customer-service | Spring Cloud Gateway | Done (TP2) |
| `discovery-service` | Service registry | Eureka | Done (TP2) |
| `event-service` | Events and seats catalog | Spring Web, JPA | Planned |
| `config-server` | Centralized configuration | Spring Cloud Config | Done (TP3) |
| `event-service` | Events catalogue | Spring Web, JPA | Done (TP4) |
| `booking-service` | Verifies events before a booking | OpenFeign | Done (TP4) |
| `booking-service` | Booking and seat locking | OpenFeign, Resilience4J | Planned |
| `payment-service` | Simulated payment provider (random failures) | Resilience4J | Planned |
| `notification-service` | Confirmation emails/SMS | Kafka consumer | Planned |
| `ai-assistant-service` | Natural-language search, recommendations | Spring AI | Later |

## Roadmap

Each step has a Git tag and a lesson in `docs/lessons/`.

| Tag | Content |
|---|---|
| `step-00-setup` | Parent POM, repo structure, CI |
| `step-01-customer-service` | First business service |
| `step-02-gateway-static` | Gateway with static routes |
| `step-03-discovery` | Eureka + load-balanced routing |
| `step-04-event-service` | Second business service |
| `step-05-config` | Config Server |
| `step-06-booking-feign` | booking-service + OpenFeign |
| `step-07-resilience` | payment-service + Resilience4J |
| `step-08-kafka` | Events + notification-service |
| `step-09-saga` | Saga, Outbox, idempotency |
| `step-10-security` | Keycloak (customer-service integration) |
| `step-11-observability` | Tracing and metrics |
| `step-12-ai` | Spring AI |

To follow a lesson, check out its tag: `git checkout step-01-customer-service`.

## Tech stack

Java 21 · Spring Boot 4 · Spring Cloud 2025.1 · Resilience4J · Apache Kafka ·
PostgreSQL · Docker / Docker Compose · Spring AI (later)

## Repository structure

    ticketflow/
    ├── docs/            # lessons, ADRs, templates
    ├── infra/           # docker-compose and infrastructure config
    ├── config-repo/     # configuration served by the config server
    └── services/        # one folder per microservice, each with its own README

Every module is independently runnable and testable, and documents itself in
its own `README.md`.

## Getting started

Prerequisites: JDK 21, Maven (or the included wrapper), Docker.

    git clone https://github.com/<your-user>/ticketflow.git
    cd ticketflow
    mvn clean verify

## TP2 - Discovery and Gateway

Prerequisites: JDK 21 and an internet connection for Maven's first download.
No MySQL or PostgreSQL installation is required for this step: customer-service
uses an H2 database in memory, recreated whenever the service is stopped.

Open three terminals at the repository root and start the applications in this
order:

    .\mvnw.cmd -pl services/discovery-service spring-boot:run
    .\mvnw.cmd -pl services/customer-service spring-boot:run
    .\mvnw.cmd -pl services/gateway spring-boot:run

Verification:

- Eureka dashboard: `http://localhost:8761`
- Customer service: `http://localhost:8081/api/customers`
- Public Gateway route: `http://localhost:8080/api/customers`

The Eureka dashboard must show `CUSTOMER-SERVICE` and `API-GATEWAY` as UP.
The Gateway route uses `lb://customer-service`, so it discovers the customer
service through Eureka instead of using a fixed address.

## TP3 - Centralized configuration

The Config Server reads service configuration from a separate GitHub repository.
The exact YAML files to upload are in `config-repo-template/`.

1. Create a GitHub repository named `ticketflow-config` with a `main` branch.
2. Upload `application.yml`, `customer-service.yml` and `api-gateway.yml` from
   `config-repo-template/` to the root of that repository.
3. In the IntelliJ run configuration of `ConfigServerApplication`, set the
   environment variable `CONFIG_GIT_URI` to the repository URL ending in `.git`.

For example:

    CONFIG_GIT_URI=https://github.com/YOUR-USERNAME/ticketflow-config.git

For TP3, start the services in this order:

    ConfigServerApplication -> DiscoveryServiceApplication -> CustomerServiceApplication -> ApiGatewayApplication

Verify the centralized configuration at:

- `http://localhost:8888/customer-service/default/main`
- `http://localhost:8081/api/config/message`

## TP4 - OpenFeign

TP4 adds Event Service and Booking Service. Booking Service verifies an event
by calling Event Service through OpenFeign and Eureka. This internal call does
not use API Gateway and no fixed URL is written in the Java client.

Before running TP4, upload `event-service.yml` and `booking-service.yml` from
`config-repo-template/` to the root of `ticketflow-config` in GitHub. Replace
the GitHub version of `api-gateway.yml` with the file from the same folder so
that Gateway exposes the new routes.

Start all services in this order:

    ConfigServerApplication -> DiscoveryServiceApplication -> EventServiceApplication -> BookingServiceApplication -> CustomerServiceApplication -> ApiGatewayApplication

Create an event through Gateway:

    curl -i -X POST http://localhost:8080/api/events -H "Content-Type: application/json" -d "{\"name\":\"Spring Cloud Workshop\"}"

Then call Booking Service through Gateway with the returned event id:

    curl -i -X POST http://localhost:8080/api/bookings -H "Content-Type: application/json" -d "{\"eventId\":1}"

The response contains `EVENT_VALIDATED`. An unknown event id returns HTTP 404,
proving that a remote error is not converted into a success.

## Who is this for?

Java developers who know Spring Boot basics and want to move into
microservices, and anyone preparing for backend or architecture interviews.

<!--
## Contributing

Contributions, issues and exercises are welcome. A `CONTRIBUTING.md` will be
added soon.
-->

## License

To be defined (MIT recommended for an educational project).

## Author

**AHAIDOUS Khadija**   | [GitHub](https://github.com/kahaidous>) · [LinkedIn](https://www.linkedin.com/in/khadijaahaidous)

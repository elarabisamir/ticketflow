# event-service

Event catalogue service for TP4.

- Port: `8082`
- Eureka name: `event-service`
- Endpoints: `POST /api/events`, `GET /api/events/{id}`

Start it after Config Server and Eureka:

    .\mvnw.cmd -pl services/event-service spring-boot:run

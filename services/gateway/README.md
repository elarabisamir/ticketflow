# api-gateway

The single public HTTP entry point for TicketFlow.

- Port: `8080`
- Route: `/api/customers/**`
- Destination: `lb://customer-service` resolved through Eureka

Start Eureka and customer-service first, then run:

    .\mvnw.cmd -pl services/gateway spring-boot:run

For example, `GET http://localhost:8080/api/customers` is routed to
customer-service. Successful responses include the header
`X-Served-By: api-gateway`.

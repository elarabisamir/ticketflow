# discovery-service

Eureka Server for TicketFlow.

- Port: `8761`
- Dashboard: `http://localhost:8761`
- Role: stores the list of running microservices. It does not carry customer
  HTTP requests itself.

Run it first:

    .\mvnw.cmd -pl services/discovery-service spring-boot:run

When customer-service and api-gateway are started, the dashboard should show
both applications as `UP`.

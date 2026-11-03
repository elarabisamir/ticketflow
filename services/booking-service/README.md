# booking-service

TP4 service that validates an event through OpenFeign before continuing a
booking. It calls `event-service` using its Eureka name, not a fixed URL.

- Port: `8083`
- Eureka name: `booking-service`
- Endpoint: `POST /api/bookings` with `{ "eventId": 1 }`

Start Event Service before Booking Service:

    .\mvnw.cmd -pl services/booking-service spring-boot:run

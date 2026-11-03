# TicketFlow configuration repository template

Create a new GitHub repository named `ticketflow-config`, then upload every
YAML file in this folder to the root of that repository. Use the branch name
`main`.

Do not upload this README unless you want to keep the instructions there.

For TP4, add `event-service.yml` and `booking-service.yml`, and replace the
existing `api-gateway.yml` with this newer version: it adds the Event and
Booking routes.

After each GitHub change, restart API Gateway, Event Service and Booking
Service so they read the newest configuration from Config Server.

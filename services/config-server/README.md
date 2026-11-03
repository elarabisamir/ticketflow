# config-server

Centralized configuration server for TicketFlow.

- Port: `8888`
- Health check: `http://localhost:8888/actuator/health`
- Source: a separate GitHub repository containing the YAML files in
  `config-repo-template/`.

Before running, define the `CONFIG_GIT_URI` environment variable in the
IntelliJ run configuration. Example:

    CONFIG_GIT_URI=https://github.com/YOUR-USERNAME/ticketflow-config.git

Then start the application:

    .\mvnw.cmd -pl services/config-server spring-boot:run

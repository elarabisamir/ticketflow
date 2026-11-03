package com.ticketflow.customerservice.helper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class InstanceHelper {

    private static final Logger log =
            LoggerFactory.getLogger(InstanceHelper.class);

    @Value("${server.port:8080}")
    private int port;

    public String identifyInstance() {
        String message = "Request handled by CUSTOMER-SERVICE on port " + port;

        log.info("LOAD BALANCER TEST -> {}", message);

        return message;
    }
}
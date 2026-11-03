package com.ticketflow.bookingservice.service;

import com.ticketflow.bookingservice.clients.EventClient;
import com.ticketflow.bookingservice.dto.BookingValidationResponse;
import com.ticketflow.bookingservice.dto.EventResponse;
import org.springframework.stereotype.Service;

@Service
public class BookingService {
    private final EventClient eventClient;

    public BookingService(EventClient eventClient) {
        this.eventClient = eventClient;
    }

    public BookingValidationResponse validateEvent(Long eventId) {
        EventResponse event = eventClient.getById(eventId);
        return new BookingValidationResponse(event.id(), event.name(), "EVENT_VALIDATED");
    }
}

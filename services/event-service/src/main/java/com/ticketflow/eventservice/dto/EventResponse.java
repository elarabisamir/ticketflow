package com.ticketflow.eventservice.dto;

import com.ticketflow.eventservice.entities.Event;

public record EventResponse(Long id, String name) {
    public static EventResponse from(Event event) {
        return new EventResponse(event.getId(), event.getName());
    }
}

package com.ticketflow.bookingservice.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import com.ticketflow.bookingservice.clients.EventClient;
import com.ticketflow.bookingservice.dto.BookingValidationResponse;
import com.ticketflow.bookingservice.dto.EventResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class BookingServiceTest {
    @Mock
    private EventClient eventClient;

    @InjectMocks
    private BookingService bookingService;

    @Test
    void validatesAnEventReturnedByEventService() {
        when(eventClient.getById(1L)).thenReturn(new EventResponse(1L, "Spring Cloud Workshop"));

        BookingValidationResponse response = bookingService.validateEvent(1L);

        assertThat(response.eventId()).isEqualTo(1L);
        assertThat(response.eventName()).isEqualTo("Spring Cloud Workshop");
        assertThat(response.status()).isEqualTo("EVENT_VALIDATED");
    }
}

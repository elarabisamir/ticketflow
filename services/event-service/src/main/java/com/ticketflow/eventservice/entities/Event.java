package com.ticketflow.eventservice.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    protected Event() { }

    public Event(String name) {
        this.name = name;
    }

    public Long getId() { return id; }
    public String getName() { return name; }
}

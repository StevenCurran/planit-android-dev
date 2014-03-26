package com.planit;

import java.util.ArrayList;
import java.util.List;

public class EventWithConflicts {

    private Event planitEvent;
    private List<Event> conflictingEvents = new ArrayList<>();

    public Event getPlanitEvent() {
        return planitEvent;
    }

    public void setPlanitEvent(Event planitEvent) {
        this.planitEvent = planitEvent;
    }

    public List<Event> getConflictingEvents() {
        return conflictingEvents;
    }

    public void setConflictingEvents(List<Event> conflictingEvents) {
        this.conflictingEvents = conflictingEvents;
    }

    public EventWithConflicts(Event event) {
        this.planitEvent = event;
    }

    public void addConflict(Event event) {
        this.conflictingEvents.add(event);
    }


}
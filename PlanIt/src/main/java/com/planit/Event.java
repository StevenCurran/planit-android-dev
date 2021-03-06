package com.planit;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Gareth on 17/03/2014.
 */
public class Event implements Comparable<Event> {
    private String id;
    private String title;
    private String location;
    private Date startDate;
    private Date endDate;
    private Date preferredTime;
    private int priority;
    private ArrayList<EventTag> tags;
    private ArrayList<Participant> participants;
    private EventDuration duration;

    public EventDuration getDuration() {
        return duration;
    }

    public void setDuration(EventDuration duration) {
        this.duration = duration;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public ArrayList<EventTag> getTags() {
        return tags;
    }

    public void setTags(ArrayList<EventTag> tags) {
        this.tags = tags;
    }

    public Date getPreferredTime() {
        return preferredTime;
    }

    public void setPreferredTime(Date preferredTime) {
        this.preferredTime = preferredTime;
    }

    public ArrayList<Participant> getParticipants() {
        return participants;
    }

    public void setParticipants(ArrayList<Participant> participants) {
        this.participants = participants;
    }

    @Override
    public int compareTo(Event event) {
        if (this.getStartDate().before(event.getStartDate())) {
            return -1;
        } else if (this.getStartDate().after(event.getStartDate())) {
            return 1;
        }
        return 0;
    }
}


package com.planit;

import java.util.Date;

/**
 * Created by Gareth on 24/03/2014.
 */
public class QueryResponse {

    private Date suggestedDate;
    private int[] conflictingEvents;

    public Date getSuggestedDate() {
        return suggestedDate;
    }

    public void setSuggestedDate(Date suggestedDate) {
        this.suggestedDate = suggestedDate;
    }

    public int[] getConflictingEvents() {
        return conflictingEvents;
    }

    public void setConflictingEvents(int[] conflictingEvents) {
        this.conflictingEvents = conflictingEvents;
    }
}

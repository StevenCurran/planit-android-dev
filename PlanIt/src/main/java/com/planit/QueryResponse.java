package com.planit;

import java.util.Date;
import java.util.List;

/**
 * Created by Gareth on 24/03/2014.
 */
public class QueryResponse {

    private Date suggestedDate;
    private List<String> conflictingEvents;

    public Date getSuggestedDate() {
        return suggestedDate;
    }

    public void setSuggestedDate(Date suggestedDate) {
        this.suggestedDate = suggestedDate;
    }

    public List<String> getConflictingEvents() {
        return conflictingEvents;
    }

    public void setConflictingEvents(List<String> conflictingEvents) {
        this.conflictingEvents = conflictingEvents;
    }
}

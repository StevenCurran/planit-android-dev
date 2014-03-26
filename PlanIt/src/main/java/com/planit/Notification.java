package com.planit;

/**
 * Created by Gareth on 22/03/2014.
 */
public class Notification {

    private String id;
    private String title;
    private String details;
    private int[] conflicts;

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

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public int[] getConflicts() {
        return conflicts;
    }

    public void setConflicts(int[] conflicts) {
        this.conflicts = conflicts;
    }
}

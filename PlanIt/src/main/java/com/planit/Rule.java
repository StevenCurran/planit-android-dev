package com.planit;

/**
 * Created by Gareth on 03/03/2014.
 */
public class Rule {

    private String id;
    private String name;
    private String description;
    private Boolean active;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getActive() { return active; }

    public void setActive(Boolean active) { this.active = active; }
}

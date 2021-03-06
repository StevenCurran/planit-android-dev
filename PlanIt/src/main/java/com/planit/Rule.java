package com.planit;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Gareth on 03/03/2014.
 */
public class Rule {

    private String id;
    private String name;
    private String description;
    private Boolean active;
    private String firstPart;
    private String secondPart;
    private String thirdPart;
    private ArrayList<Object> secondPartDetail;
    private ArrayList<Date> thirdPartTime;
    private ArrayList<String> thirdPartDays;

    public ArrayList<Date> getThirdPartTime() {
        return thirdPartTime;
    }

    public void setThirdPartTime(ArrayList<Date> thirdPartTime) {
        this.thirdPartTime = thirdPartTime;
    }

    public ArrayList<String> getThirdPartDays() {
        return thirdPartDays;
    }

    public void setThirdPartDays(ArrayList<String> thirdPartDays) {
        this.thirdPartDays = thirdPartDays;
    }

    public String getFirstPart() {
        return firstPart;
    }

    public void setFirstPart(String firstPart) {
        this.firstPart = firstPart;
    }

    public String getSecondPart() {
        return secondPart;
    }

    public void setSecondPart(String secondPart) {
        this.secondPart = secondPart;
    }

    public ArrayList<Object> getSecondPartDetail() {
        return secondPartDetail;
    }

    public void setSecondPartDetail(ArrayList<Object> secondPartDetail) {
        this.secondPartDetail = secondPartDetail;
    }

    public String getThirdPart() {
        return thirdPart;
    }

    public void setThirdPart(String thirdPart) {
        this.thirdPart = thirdPart;
    }

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

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
}

package com.planit;

import android.graphics.drawable.BitmapDrawable;

import com.google.api.services.plus.model.Person;

/**
 * Created by Garf on 25/02/2014.
 */
public class User {

    private String lastName;
    private String firstName;
    private String userId; //may need to be providerId
    private String email;
    private String profileUrl;
    private String deviceId;

    private transient BitmapDrawable image;

    public User(Person person) {
        this.userId = person.getId();
        this.firstName = person.getName().getGivenName();
        this.lastName = person.getName().getFamilyName();
        this.email = person.getEmails().get(0).getValue().toString();
        this.profileUrl = person.getImage().getUrl();
    }

    public User() {

    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getImageUrl() {
        //strip out the size limit from google
        return profileUrl.substring(0, profileUrl.length() - 5);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getId() {
        return userId;
    }

    public void setId(String id) {
        this.userId= id;
    }

    public BitmapDrawable getImage() {
        return image;
    }

    public void setImage(BitmapDrawable image) {
        this.image = image;
    }

    public String getName() {
        return firstName;
    }

    public void setName(String name) {
        this.firstName = name;
    }


    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}

package com.planit;

import android.graphics.drawable.BitmapDrawable;

import com.google.api.services.plus.model.Person;

/**
 * Created by Garf on 25/02/2014.
 */
public class User {

    private String name;
    private String id;
    private String email;
    private BitmapDrawable image;
    private String imageUrl;

    public User(Person person) {
        this.id = person.getId();
        this.name = person.getDisplayName();
        this.email = person.getEmails().get(0).getValue().toString();
        this.imageUrl = person.getImage().getUrl();
    }

    public User() {

    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public BitmapDrawable getImage() {
        return image;
    }

    public void setImage(BitmapDrawable image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}

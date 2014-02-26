package com.planit;

import android.graphics.drawable.BitmapDrawable;

/**
 * Created by Garf on 25/02/2014.
 */
public class User {

    private String name;
    private String id;
    private BitmapDrawable image;

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

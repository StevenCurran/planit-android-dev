package com.planit;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Gareth on 17/03/2014.
 */
public class Participant extends User implements Parcelable{

    private Boolean attending;

    public Participant(Parcel parcel) {
        //fix this up
    }

    public Boolean getAttending() { return attending; }

    public void setAttending(Boolean attending) { this.attending = attending; }


    public static final Creator<Participant> CREATOR = new Creator<Participant>() {
        @Override
        public Participant createFromParcel(Parcel parcel) {
            return new Participant(parcel);
        }

        @Override
        public Participant[] newArray(int i) {
            return new Participant[i];
        }
    };


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeValue(attending);
        parcel.writeString(this.getEmail());
        parcel.writeString(this.getId());
        parcel.writeString(this.getImageUrl());
        parcel.writeString(this.getName());
        parcel.writeValue(this.getImage());
    }
}

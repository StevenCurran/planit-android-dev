package com.planit;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by Garf on 24/02/2014.
 */
public class ProfileActivity extends Activity {

    final Context context = this;
    private Bundle b = new Bundle();

    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile);

        //set fonts
        Typeface uilFont = Typeface.createFromAsset(getAssets(), "fonts/segoeuisl.ttf");
        Button scheduleButton = (Button) findViewById(R.id.scheduleButton);
        scheduleButton.setTypeface(uilFont);
        TextView title = (TextView) findViewById(R.id.screenTitle);
        title.setTypeface(uilFont);
        TextView userNameText = (TextView) findViewById(R.id.userNameText);
        userNameText.setTypeface(uilFont);
        TextView userIdText = (TextView) findViewById(R.id.userIDText);
        userIdText.setTypeface(uilFont);
        Button signOutButton = (Button) findViewById(R.id.signOutButton);
        signOutButton.setTypeface(uilFont);

        //set schedule button date
        scheduleButton.setText(getDayString());

        //get user details
        String[] userDetails = getUserDetails();

        //set user details in UI
        userNameText.setText(userDetails[0]);
        userIdText.setText(userDetails[1]);
    }

    public String getDayString(){
        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DATE);

        return Integer.toString(day);
    }

    public void goToSchedule(View view){
        //do fings
    }

    public void goToNotifications(View view){
        //do fings
    }

    public void doSignOut(View view){
        //do fings
    }

    public String[] getUserDetails(){
        String[] userDetails = new String[2];

        //do server things here

        userDetails[0] = "Gareth Smith";
        userDetails[1] = "gas001@gmail.com";

        return userDetails;
    }
}

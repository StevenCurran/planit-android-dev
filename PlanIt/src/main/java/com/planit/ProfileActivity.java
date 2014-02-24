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
        Button signOutButton = (Button) findViewById(R.id.signOutButton);
        signOutButton.setTypeface(uilFont);
        //set schedule button date
        scheduleButton.setText(getDayString());
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
}

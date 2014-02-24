package com.planit;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

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
        Typeface btnFont = Typeface.createFromAsset(getAssets(), "fonts/segoeuisl.ttf");
        Button scheduleButtonText = (Button) findViewById(R.id.scheduleButton);
        scheduleButtonText.setTypeface(btnFont);

    }

    public void goToSchedule(View view){
        //do fings
    }

    public void goToNotifications(View view){
        //do fings
    }
}

package com.planit;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Calendar;

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
        Typeface uiFont = Typeface.createFromAsset(getAssets(), "fonts/segoeui.ttf");

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
        Button editProfileButton = (Button) findViewById(R.id.editProfileButton);
        editProfileButton.setTypeface(uilFont);
        TextView linkedAccountsTitle = (TextView) findViewById(R.id.linkedAccountsTitle);
        linkedAccountsTitle.setTypeface(uiFont);
        TextView linkedAccount1Name = (TextView) findViewById(R.id.linkedAccount1UserName);
        linkedAccount1Name.setTypeface(uilFont);
        Button editAccount1Button = (Button) findViewById(R.id.editAccount1Button);
        editAccount1Button.setTypeface(uilFont);
        Button removeAccount1Button = (Button) findViewById(R.id.removeAccount1Button);
        removeAccount1Button.setTypeface(uilFont);

        //set schedule button date
        scheduleButton.setText(getDayString());

        //get user details
        String[] userDetails = getUserDetails();

        //set user details in UI
        userNameText.setText(userDetails[0]);
        userIdText.setText(userDetails[1]);
        ImageView userPicture = (ImageView) findViewById(R.id.userPicture);
        userPicture.setImageResource(R.drawable.default_user_photo);

        //set linked accounts stuff
        setLinkedAccounts();
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

    public void doEditProfile(View view){
        //do fings
    }

    public void doEditLinkedAccount1(View view){
        //do fings
    }

    public void doDeleteLinkedAccount1(View view){
        //do fings
    }

    public String[] getUserDetails(){
        String[] userDetails = new String[2];

        //do server things here

        userDetails[0] = "Gareth Smith";
        userDetails[1] = "gas001@gmail.com";

        return userDetails;
    }

    public void setLinkedAccounts(){

        //temp stuff
        ImageView providerIcon = (ImageView) findViewById(R.id.linkedAccount1ProviderIcon);
        providerIcon.setImageResource(R.drawable.google_logo);
    }
}

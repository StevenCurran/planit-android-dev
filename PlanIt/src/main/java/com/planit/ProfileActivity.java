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

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

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

        //set user details
        setUserDetails();

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

    public void doEditLinkedAccount(View view){
        //do fings
    }

    public void doDeleteLinkedAccount(View view){
        //do fings
    }

    public void setUserDetails(){
        TextView userNameText = (TextView) findViewById(R.id.userNameText);
        TextView userIdText = (TextView) findViewById(R.id.userIDText);

        User currentUser = getUserDetails();

        userNameText.setText(currentUser.name);
        userIdText.setText(currentUser.id);
        ImageView userPicture = (ImageView) findViewById(R.id.userPicture);
        userPicture.setImageResource(R.drawable.default_user_photo);
    }

    public User getUserDetails(){
        User currentUser = new User();

        //do server things here

        currentUser.name = "Gareth Smith";
        currentUser.id = "gas001@gmail.com";

        return currentUser;
    }

    public void setLinkedAccounts(){

        List<LinkedAccount> linkedAccounts = getLinkedAccounts();

        for(LinkedAccount la : linkedAccounts){
            TextView linkedAccount1UserName = (TextView) findViewById(R.id.linkedAccount1UserName);
            ImageView account1ProviderIcon = (ImageView) findViewById(R.id.linkedAccount1ProviderIcon);

            linkedAccount1UserName.setText(la.accountId);
            switch (la.accountProvider) {
                case "Google":
                    account1ProviderIcon.setImageResource(R.drawable.google_logo);
                    break;
                case "Outlook":
                    account1ProviderIcon.setImageResource(R.drawable.outlook_logo);
                    break;
                case "Facebook":
                    account1ProviderIcon.setImageResource(R.drawable.facebook_logo);
                    break;
            }
        }

    }

    public List<LinkedAccount> getLinkedAccounts(){

        List<LinkedAccount> linkedAccounts = new ArrayList<LinkedAccount>();

        //find all the linked accounts and add them to list
        //do le server fings and process server results

        for(int i = 0; i < 1; i++){
            LinkedAccount linkedAccount = new LinkedAccount();
            linkedAccount.accountId = "gas001@gmail.com";
            linkedAccount.accountProvider = "Google";

            linkedAccounts.add(linkedAccount);
        }

        return linkedAccounts;
    }
}

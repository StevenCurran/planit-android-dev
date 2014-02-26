package com.planit;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
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
//        TextView linkedAccount1Name = (TextView) findViewById(R.id.linkedAccount1UserName);
//        linkedAccount1Name.setTypeface(uilFont);
//        Button editAccount1Button = (Button) findViewById(R.id.editAccount1Button);
//        editAccount1Button.setTypeface(uilFont);
//        Button removeAccount1Button = (Button) findViewById(R.id.removeAccount1Button);
//        removeAccount1Button.setTypeface(uilFont);

        //set schedule button date
        scheduleButton.setText(getDayString());

        //set user details
        setUserDetails();

        //set linked accounts stuff
//        setLinkedAccounts();

        //test
        dynamicSetLinkedAccounts();
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

        //STILL NEED TO FIX IMAGES
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

//    public void setLinkedAccounts(){
//
//        List<LinkedAccount> linkedAccounts = getLinkedAccounts();
//
//        for(LinkedAccount la : linkedAccounts){
//            TextView linkedAccount1UserName = (TextView) findViewById(R.id.linkedAccount1UserName);
//            ImageView account1ProviderIcon = (ImageView) findViewById(R.id.linkedAccount1ProviderIcon);
//
//            linkedAccount1UserName.setText(la.accountId);
//            switch (la.accountProvider) {
//                case "Google":
//                    account1ProviderIcon.setImageResource(R.drawable.google_logo);
//                    break;
//                case "Outlook":
//                    account1ProviderIcon.setImageResource(R.drawable.outlook_logo);
//                    break;
//                case "Facebook":
//                    account1ProviderIcon.setImageResource(R.drawable.facebook_logo);
//                    break;
//            }
//        }
//
//    }

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

    public void dynamicSetLinkedAccounts(){

        Typeface uilFont = Typeface.createFromAsset(getAssets(), "fonts/segoeuisl.ttf");
        Typeface uiFont = Typeface.createFromAsset(getAssets(), "fonts/segoeui.ttf");

        ScrollView sv = (ScrollView) findViewById(R.id.linkedAccountScrollView);
        List<LinkedAccount> linkedAccounts = getLinkedAccounts();

        for(LinkedAccount la : linkedAccounts){

            //Create a linked account element
            LinearLayout linkedAccountElement = new LinearLayout(this);
            linkedAccountElement.setOrientation(LinearLayout.HORIZONTAL);
            linkedAccountElement.setPadding(convertDPToPixel(15),0,0,0);

            //Set the appropriate provider icon for the image view
            ImageView accountProviderIcon = new ImageView(this);
            switch (la.accountProvider) {
                case "Google":
                    accountProviderIcon.setImageResource(R.drawable.google_logo);
                    break;
                case "Outlook":
                    accountProviderIcon.setImageResource(R.drawable.outlook_logo);
                    break;
                case "Facebook":
                    accountProviderIcon.setImageResource(R.drawable.facebook_logo);
                    break;
            }
            //Add the image view to the element
            linkedAccountElement.addView(accountProviderIcon);

            //Create an account details container
            LinearLayout accountDetailsContainer = new LinearLayout(this);
            accountDetailsContainer.setOrientation(LinearLayout.VERTICAL);
            accountDetailsContainer.setPadding(convertDPToPixel(15),0,convertDPToPixel(30),0);

            //Create text view for account id
            TextView accountID = new TextView(this);
            accountID.setTextColor(Color.BLACK);
            accountID.setTextSize(18);
            accountID.setGravity(Gravity.CENTER_HORIZONTAL);
            accountID.setTypeface(uilFont);

            //Set the value for the user name
            accountID.setText(la.accountId);

            //Add account id to account details container
            accountDetailsContainer.addView(accountID);

            //Add account edit panel
            LinearLayout editPanel = new LinearLayout(this);
            editPanel.setOrientation(LinearLayout.HORIZONTAL);
            editPanel.setPadding(0,convertDPToPixel(5),0,0);

            //Create an edit button
            Button editButton = new Button(this, null, android.R.attr.borderlessButtonStyle);
            editButton.setBackgroundResource(R.drawable.small_blue_button);
            editButton.setPadding(convertDPToPixel(30), convertDPToPixel(5), convertDPToPixel(30), convertDPToPixel(5));
            editButton.setText("Edit");
            editButton.setTextColor(Color.WHITE);
            editButton.setTextSize(15);
            editButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    doEditLinkedAccount(view);
                }
            });

            //Add the edit button to the edit panel
            editPanel.addView(editButton);

            //Create a remove button
            Button removeButton = new Button(this, null, android.R.attr.borderlessButtonStyle);
            removeButton.setBackgroundResource(R.drawable.small_red_button);
            removeButton.setPadding(convertDPToPixel(20), convertDPToPixel(5), convertDPToPixel(20), convertDPToPixel(5));
            removeButton.setText("Remove");
            removeButton.setTextColor(Color.WHITE);
            removeButton.setTextSize(15);
            removeButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    doDeleteLinkedAccount(view);
                }
            });

            //Add the remove button to the edit panel
            editPanel.addView(removeButton);

            //Add the edit panel to the details container
            accountDetailsContainer.addView(editPanel);

            //Add the account details container to the element
            linkedAccountElement.addView(accountDetailsContainer);




            // Add the linked account element to the ScrollView
            sv.addView(linkedAccountElement);
        }

    }
      
    public int convertDPToPixel(int dpValue){
        float scale = getResources().getDisplayMetrics().density;
        return (int) (dpValue*scale + 0.5f);
    }

}

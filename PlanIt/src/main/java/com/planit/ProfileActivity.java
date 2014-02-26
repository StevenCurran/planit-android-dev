package com.planit;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.planit.constants.GlobalUserStore;

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
        TextView userEmailText = (TextView) findViewById(R.id.userEmailText);
        userEmailText.setTypeface(uilFont);
        Button signOutButton = (Button) findViewById(R.id.signOutButton);
        signOutButton.setTypeface(uilFont);
        Button editProfileButton = (Button) findViewById(R.id.editProfileButton);
        editProfileButton.setTypeface(uilFont);
        TextView linkedAccountsTitle = (TextView) findViewById(R.id.linkedAccountsTitle);
        linkedAccountsTitle.setTypeface(uiFont);

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

    public void changeDisplayInCalValue(View view){
        //do fings
    }

    public void setUserDetails(){
        TextView userNameText = (TextView) findViewById(R.id.userNameText);
        TextView userEmailText = (TextView) findViewById(R.id.userEmailText);

        User currentUser = GlobalUserStore.getUser();

        userNameText.setText(currentUser.getName());
        userEmailText.setText(currentUser.getEmail());

        ImageView userPicture = (ImageView) findViewById(R.id.userPicture);
        userPicture.setImageDrawable(GlobalUserStore.getUser().getImage());
        LinearLayout.LayoutParams userPictureParams= new LinearLayout.LayoutParams(120,120);
        userPicture.setLayoutParams(userPictureParams);
    }

    public List<LinkedAccount> getLinkedAccounts(){

        List<LinkedAccount> linkedAccounts = new ArrayList<LinkedAccount>();

        //find all the linked accounts and add them to list - need to add checks for no linked accounts etc.
        //do le server fings and process server results

        LinkedAccount testlinkedAccount1 = new LinkedAccount();
        testlinkedAccount1.accountId = "gas001@gmail.com";
        testlinkedAccount1.accountProvider = "Google";
        testlinkedAccount1.displayInCal = true;

        LinkedAccount testlinkedAccount2 = new LinkedAccount();
        testlinkedAccount2.accountId = "gsmith28@facebook.com";
        testlinkedAccount2.accountProvider = "Facebook";
        testlinkedAccount2.displayInCal = false;

        linkedAccounts.add(testlinkedAccount1);
        linkedAccounts.add(testlinkedAccount2);

        return linkedAccounts;
    }

    public void setLinkedAccounts(){

        Typeface uilFont = Typeface.createFromAsset(getAssets(), "fonts/segoeuisl.ttf");
        Typeface uiFont = Typeface.createFromAsset(getAssets(), "fonts/segoeui.ttf");
        LinearLayout linkedAccountsContainer = (LinearLayout) findViewById(R.id.linkedAccountsContainer);

        List<LinkedAccount> linkedAccounts = getLinkedAccounts();

        //Add all the linked accounts to the container
        for(LinkedAccount la : linkedAccounts){

            //Create a linked account element
            LinearLayout linkedAccountElement = new LinearLayout(this);
            linkedAccountElement.setOrientation(LinearLayout.HORIZONTAL);
            linkedAccountElement.setPadding(convertDPToPixel(15),0,0,convertDPToPixel(10));

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
            accountProviderIcon.setPadding(0,convertDPToPixel(5),0,0);
            //Add the image view to the element
            linkedAccountElement.addView(accountProviderIcon);

            //Create an account details container
            LinearLayout accountDetailsContainer = new LinearLayout(this);
            accountDetailsContainer.setOrientation(LinearLayout.VERTICAL);
            accountDetailsContainer.setPadding(convertDPToPixel(15), 0, 0, 0);

            //Create text view for account id
            TextView accountID = new TextView(this);
            accountID.setTextColor(Color.BLACK);
            accountID.setTextSize(16);
            accountID.setTypeface(uilFont);

            //Set the value for the user name
            accountID.setText(la.accountId);

            //Add account id to account details container
            accountDetailsContainer.addView(accountID);

            //Add account edit panel
            LinearLayout editPanel = new LinearLayout(this);
            editPanel.setOrientation(LinearLayout.HORIZONTAL);

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

            //Create layout params for edit button
            LinearLayout.LayoutParams editBtnParams = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );
            editBtnParams.setMargins(0, 0, convertDPToPixel(10), 0);

            //Set layout params on button
            editButton.setLayoutParams(editBtnParams);

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

            //Create the inCal toggle switch and set it's checked value
            ToggleButton displayInCalToggle = new ToggleButton(this);
            displayInCalToggle.setBackgroundResource(R.drawable.planit_toggle_bg);
            displayInCalToggle.setTextOn("");
            displayInCalToggle.setTextOff("");
            displayInCalToggle.setChecked(la.displayInCal);
            displayInCalToggle.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    changeDisplayInCalValue(view);
                }
            });

            //Create layout params for the toggle
            LinearLayout.LayoutParams toggleParams = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );
            toggleParams.setMargins(convertDPToPixel(25), 0, 0, 0);

            //Set layout params on toggle
            displayInCalToggle.setLayoutParams(toggleParams);

            //Add the toggle switch to the edit panel
            editPanel.addView(displayInCalToggle);

            //Add the edit panel to the details container
            accountDetailsContainer.addView(editPanel);

            //Add the account details container to the element
            linkedAccountElement.addView(accountDetailsContainer);

            //Add the linked account element to the accounts container
            linkedAccountsContainer.addView(linkedAccountElement);
        }

        //Add the "Add Account" option to the container

        //Create the add account element
        LinearLayout addAccountElement = new LinearLayout(this);
        addAccountElement.setOrientation(LinearLayout.HORIZONTAL);
        addAccountElement.setPadding(convertDPToPixel(15),0,0,convertDPToPixel(10));

        //Set the image
        ImageView addAccountIcon = new ImageView(this);
        addAccountIcon.setImageResource(R.drawable.add_account_button);
        addAccountIcon.setPadding(0,convertDPToPixel(4),0,0);
        addAccountIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, AddLinkedAccountActivity.class);
                startActivity(intent);
            }
        });

        //Add the image view to the element
        addAccountElement.addView(addAccountIcon);

        //Create the text for the add account element
        TextView addAccountMessage = new TextView(this);
        addAccountMessage.setTextColor(Color.BLACK);
        addAccountMessage.setTextSize(18);
        addAccountMessage.setTypeface(uilFont);
        addAccountMessage.setPadding(convertDPToPixel(15),convertDPToPixel(15),0,0);
        addAccountMessage.setText("Add Account");

        //Add text to the add account element
        addAccountElement.addView(addAccountMessage);

        //Add the add account element to the accounts container
        linkedAccountsContainer.addView(addAccountElement);

    }

    public int convertDPToPixel(int dpValue){
        float scale = getResources().getDisplayMetrics().density;
        return (int) (dpValue*scale + 0.5f);
    }

}

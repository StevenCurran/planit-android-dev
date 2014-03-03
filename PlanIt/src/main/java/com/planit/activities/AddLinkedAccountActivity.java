package com.planit.activities;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.planit.R;


/**
 * Created by Garf on 24/02/2014.
 */
public class AddLinkedAccountActivity extends Activity {

    final Context context = this;
    private Bundle b = new Bundle();

    public String provider = "";
    LinearLayout tagContainer;
    Button accountLinkSignInButton;

    //testing commit and push on new comp

    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_linked_account);

        tagContainer = (LinearLayout) findViewById(R.id.tagsContainer);
        accountLinkSignInButton = (Button) findViewById(R.id.accountLinkSignInButton);

        //set fonts
        Typeface uilFont = Typeface.createFromAsset(getAssets(), "fonts/segoeuisl.ttf");
        Typeface uiFont = Typeface.createFromAsset(getAssets(), "fonts/segoeui.ttf");

        TextView title = (TextView) findViewById(R.id.screenTitle);
        title.setTypeface(uilFont);
        TextView accountProviderTitle = (TextView) findViewById(R.id.accountProviderTitle);
        accountProviderTitle.setTypeface(uiFont);

        accountLinkSignInButton.setTypeface(uilFont);

        Button linkButton = (Button) findViewById(R.id.linkButton);
        linkButton.setTypeface(uilFont);


    }

    public void setProvider(View view) {
        Button googleButton = (Button) findViewById(R.id.googleProviderIcon);
        Button facebookButton = (Button) findViewById(R.id.facebookProviderIcon);

        switch (view.getId()) {
            case R.id.googleProviderIcon:
                //set button alpha values
                googleButton.setAlpha(1);
                facebookButton.setAlpha((float) 0.5);
                tagContainer.setVisibility(View.INVISIBLE);
                accountLinkSignInButton.setText("");

                //set provider value
                provider = "Google";
                break;
            case R.id.facebookProviderIcon:
                //set button alpha values
                googleButton.setAlpha((float) 0.5);
                facebookButton.setAlpha(1);
                tagContainer.setVisibility(View.INVISIBLE);
                accountLinkSignInButton.setText("");

                //set provider value
                provider = "Facebook";
                break;
        }

        setLoginButton();
    }

    public void setLoginButton() {

        switch (provider) {
            case "Google":
                accountLinkSignInButton.setBackgroundResource(R.drawable.google_login_image);
                accountLinkSignInButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        doLogin();
                    }
                });
                break;
            case "Facebook":
                accountLinkSignInButton.setBackgroundResource(R.drawable.facebook_login_image);
                accountLinkSignInButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        doLogin();
                    }
                });
                break;
        }
    }

    public void doLogin() {

        switch (provider) {
            case "Google":
                //login to google, wait for response, when we've got details back, do fings below

                accountLinkSignInButton.setBackground(null);
                accountLinkSignInButton.setText("gas001@gmail.com");
                tagContainer.setVisibility(View.VISIBLE);

                break;
            case "Facebook":
                //login to facebook, wait for response, when we've got details back, do fings below

                accountLinkSignInButton.setBackground(null);
                accountLinkSignInButton.setText("gsmith28@facebook.com");
                tagContainer.setVisibility(View.VISIBLE);
                break;
        }

    }

    public void doLink(View view) {
        //do fings
    }

}
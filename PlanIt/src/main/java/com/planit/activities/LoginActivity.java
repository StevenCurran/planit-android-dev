package com.planit.activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.planit.R;

/**
 * Created by Garf on 18/02/2014.
 */

public class LoginActivity extends Activity {

    final Context context = this;
    private Bundle b = new Bundle();

    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        //set fonts

        Typeface titleFont = Typeface.createFromAsset(getAssets(), "fonts/segoeuisl.ttf");
        TextView forgottenPasswordText = (TextView) findViewById(R.id.forgottenPasswordText);
        forgottenPasswordText.setTypeface(titleFont);
        TextView noAccountText = (TextView) findViewById(R.id.noAccountText);
        noAccountText.setTypeface(titleFont);

        Typeface loginBoxFont = Typeface.createFromAsset(getAssets(), "fonts/segoeui.ttf");
        EditText planItIDBox = (EditText) findViewById(R.id.planitIDTextBox);
        planItIDBox.setTypeface(loginBoxFont);
        EditText planItIDPassword = (EditText) findViewById(R.id.planitIDPasswordBox);
        planItIDPassword.setTypeface(loginBoxFont);


    }

    public void doGoogleLogin(View view) {
        Intent intent = new Intent(context, WebViewActivity.class);
        b.putString("login_type", "google");
        intent.putExtras(b);
        startActivity(intent);
    }

    public void doFacebookLogin(View view) {
        Intent intent = new Intent(context, WebViewActivity.class);
        b.putString("login_type", "facebook");
        intent.putExtras(b);
        startActivity(intent);
    }

}

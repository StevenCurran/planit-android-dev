package com.planit;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

/**
 * Created by Garf on 18/02/2014.
 */

public class LoginActivity extends Activity {

    final Context context = this;
    private Bundle b = new Bundle();

    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        //set font for heading2
        TextView txt = (TextView) findViewById(R.id.heading2);
        Typeface font = Typeface.createFromAsset(getAssets(), "fonts/segoeuisl.ttf");
        txt.setTypeface(font);
    }

    public void doGoogleLogin(View view){
        Intent intent = new Intent(context, WebViewActivity.class);
        b.putString("login_type", "google");
        intent.putExtras(b);
        startActivity(intent);
    }

    public void doFacebookLogin(View view){
        Intent intent = new Intent(context, WebViewActivity.class);
        b.putString("login_type", "facebook");
        intent.putExtras(b);
        startActivity(intent);
    }

}

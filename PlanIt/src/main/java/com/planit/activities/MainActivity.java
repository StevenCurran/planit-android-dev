package com.planit.activities;

/**
 * Created by Steven on 17/02/14.
 */
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.ResponseHandlerInterface;
import com.planit.R;
import com.planit.constants.GlobalCookieStore;
import com.planit.constants.UrlServerConstants;
import com.planit.gcm.DemoActivity;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;

public class MainActivity extends Activity {

    private Button button;
    private AsyncHttpClient asyncHttpClient = new AsyncHttpClient();
    final Context context = this;

    public void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        button = (Button) findViewById(R.id.buttonUrl);

        button.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(context, WebViewActivity.class);
                startActivity(intent);
            }

        });

    }

    public void goToLoginScreen(View view){
        Intent intent = new Intent(getApplicationContext(), DemoActivity.class);
        startActivity(intent);
       //Intent intent = new Intent(context, LoginActivity.class);
       //startActivity(intent);
    }

    public void goToProfileScreen(View view){
        Intent intent = new Intent(context, ProfileActivity.class);
        startActivity(intent);
    }

    public void getFacebookEvents(View view){
        asyncHttpClient.setCookieStore(GlobalCookieStore.getCookieStore());
        asyncHttpClient.get(UrlServerConstants.FACEBOOK_EVENTS, null, JSON_CALLBACK_HANDLER);

    }

    public void openCalendarEvent(View view){
        Intent intent = new Intent(Intent.ACTION_INSERT);
        intent.setType("vnd.android.cursor.item/event");
        intent.putExtra(CalendarContract.Events.TITLE, "Learn to add events");
        intent.putExtra(CalendarContract.Events.EVENT_LOCATION, "BCB lebbsss");
        intent.putExtra(CalendarContract.Events.DESCRIPTION, "This may be pretty tough to do though....");
        intent.putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, new Date());
        intent.putExtra(CalendarContract.EXTRA_EVENT_ALL_DAY, true);
        intent.setData(CalendarContract.Events.CONTENT_URI);
        startActivity(intent);
    }

    public void createEventInPhone(String name, String location, String description, Date startTime, Date endTime){
        Intent intent = new Intent(Intent.ACTION_INSERT);
        intent.setType("vnd.android.cursor.item/event");
        intent.putExtra(CalendarContract.Events.TITLE, name);
        intent.putExtra(CalendarContract.Events.EVENT_LOCATION, location);
        intent.putExtra(CalendarContract.Events.DESCRIPTION, description);
        intent.putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, new Date());
        intent.putExtra(CalendarContract.EXTRA_EVENT_ALL_DAY, true);
        intent.setData(CalendarContract.Events.CONTENT_URI);
        startActivity(intent);

    }




    private ResponseHandlerInterface JSON_CALLBACK_HANDLER = new JsonHttpResponseHandler(){

        @Override
        public void onSuccess(JSONArray events){
            try {
                JSONObject firstEvent = (JSONObject) events.get(0);
                String location = firstEvent.getString("location");
                int duration = Toast.LENGTH_LONG;
                Toast toast = Toast.makeText(context, location, duration);
                toast.show();

                createEventInPhone(firstEvent.getString("name"), firstEvent.getString("location"), firstEvent.getString("description"), null , null);

            } catch (JSONException e) {
                e.printStackTrace();
            }

        }

        @Override
        public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error)
        {
            System.out.println(statusCode);
        }
    };
}
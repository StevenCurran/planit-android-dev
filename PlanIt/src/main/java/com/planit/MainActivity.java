package com.planit;

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
import android.webkit.CookieManager;
import android.widget.Button;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.PersistentCookieStore;
import com.loopj.android.http.ResponseHandlerInterface;
import com.restfb.types.Event;

import org.apache.http.Header;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;

public class MainActivity extends Activity {

    private Button button;
    private AsyncHttpClient asyncHttpClient = new AsyncHttpClient();
    final Context context = this;
    private PersistentCookieStore cookieStore;

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


    public void getFacebookEvents(View view){

        asyncHttpClient.setCookieStore(cookieStore);
        asyncHttpClient.get("http://planit-dev.herokuapp.com/fbevents", null, JSON_CALLBACK_HANDLER);

    }

    /*
            asyncHttpClient.get("http://planit-dev.herokuapp.com/fbevents", new AsyncHttpResponseHandler(){
        @Override
        public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
            String response = new String(responseBody);
            System.out.println(response);
        }

        @Override
        public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error)
        {
            String response = new String(responseBody);
            System.out.println(response);
        }


    });
    */

    public void getFacebookEvents2(View view){

        this.cookieStore = new PersistentCookieStore(this);

        BasicClientCookie clientCookie = new BasicClientCookie("JSESSIONID", getCookie("http://planit-dev.herokuapp.com", "JSESSIONID"));
        clientCookie.setVersion(1);
        clientCookie.setDomain("planit-dev.herokuapp.com");
        clientCookie.setPath("/");
        this.cookieStore.addCookie(clientCookie);

        int duration = Toast.LENGTH_LONG;
        Toast toast = Toast.makeText(context, "Cookie Obtained!!", duration);
        toast.show();

//        Intent intent = new Intent(context, WebViewActivity2.class);
//
  //      startActivity(intent);
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




    private ResponseHandlerInterface JSON_CALLBACK_HANDLER = new JsonHttpResponseHandler(){

        @Override
        public void onSuccess(JSONArray events){
            try {
                JSONObject firstEvent = (JSONObject) events.get(0);
                String location = firstEvent.getString("location");
                int duration = Toast.LENGTH_LONG;
                Toast toast = Toast.makeText(context, location, duration);
                toast.show();

            } catch (JSONException e) {
                e.printStackTrace();
            }

        }

        @Override
        public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error)
        {
            System.out.println(statusCode);
        }

        @Override
        public void onRetry() {
            System.out.println();
        }

        @Override
        public void onFinish() {
            System.out.println("Big error...");
        }


    };

    public String getCookie(String siteName,String CookieName){
        String CookieValue = null;

        CookieManager cookieManager = CookieManager.getInstance();
        String cookies = cookieManager.getCookie(siteName);
        String[] temp=cookies.split("[;]");
        for (String ar1 : temp ){
            if(ar1.contains(CookieName)){
                String[] temp1=ar1.split("[=]");
                CookieValue = temp1[1];
            }
        }
        return CookieValue;
    }

}
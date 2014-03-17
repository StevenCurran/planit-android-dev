package com.planit.activities;

/**
 * Created by Steven on 17/02/14.
 */

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.Bundle;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.webkit.ValueCallback;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.plus.model.Person;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.BinaryHttpResponseHandler;
import com.planit.R;
import com.planit.User;
import com.planit.constants.GlobalCookieStore;
import com.planit.constants.GlobalUserStore;
import com.planit.constants.UrlServerConstants;
import com.planit.utils.WebClient;

import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.http.impl.cookie.BasicClientCookie;

import java.io.IOException;

public class WebViewActivity extends Activity {

    final Context context = this;

    final ValueCallback<String> valueCallback = new ValueCallback<String>() {
        @Override
        public void onReceiveValue(String s) {
            int duration = Toast.LENGTH_SHORT;
            if (s.contains("loading...")) {
                return;
            }
            ;

            if (s.length() > 10 && !url.contains("about:blank") && !url.contains("google.com")) {
                s = StringEscapeUtils.unescapeJson(s);
                s = s.substring(1, s.length() - 1); //remove the ""
                try {
                    Person person = gson.fromString(s, Person.class);
                    User u = new User(person);
                    GlobalUserStore.setUser(u);
//                    loadProfileImage(person.getImage().getUrl());
                    Intent intent = new Intent(context, ScheduleActivity.class);
                    startActivity(intent);

//
                    Toast toast = Toast.makeText(getApplicationContext(), "Hi " + person.getDisplayName().substring(0, person.getDisplayName().indexOf(' ')) + "!", duration);
                    toast.show();

                } catch (IOException e) {
                    //problem with the serialisation
                }
            }

        }
    };
    private String url = "";
    final ValueCallback<String> URLCALLBACK = new ValueCallback<String>() {
        @Override
        public void onReceiveValue(String s) {
            url = s;
        }
    };
    private GsonFactory gson = new GsonFactory();
    private WebView webView;
    private AsyncHttpClient client = new AsyncHttpClient();

    private void loadProfileImage(String url) {
        String[] allowedContentTypes = new String[]{"image/jpeg", "image/png"};
        WebClient.get(url.substring(0, url.length() - 5), null, new BinaryHttpResponseHandler(allowedContentTypes) {
            @Override
            public void onSuccess(byte[] fileData) {
                GlobalUserStore.getUser().setImage(new BitmapDrawable(getApplicationContext().getResources(), BitmapFactory.decodeByteArray(fileData, 0, fileData.length)));
            }

            @Override
            public void onFinish() {
                super.onFinish();
            }
        });

    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.webview);

        CookieSyncManager.createInstance(this);
        CookieSyncManager.getInstance().startSync();


        webView = (WebView) findViewById(R.id.webView1);
        webView.getSettings().setJavaScriptEnabled(true);


        webView.setWebViewClient(new WebViewClient() {

            @Override
            public void onPageStarted(WebView view, String url, Bitmap s) {
                if ((url.contains(UrlServerConstants.GOOGLE_AUTH_REDIRECT) && !url.contains(UrlServerConstants.GOOGLE_HOME)) || url.contains(UrlServerConstants.FACEBOOK_AUTH_REDIRECT)) {
                    //Should we enter here we know that the user has been validated.
                    //this should make the webview transparent, which is kinda cool.
                    webView.setBackgroundColor(0x00000000);
                    webView.setLayerType(WebView.LAYER_TYPE_SOFTWARE, null);

                    BasicClientCookie clientCookie = new BasicClientCookie("JSESSIONID", getCookie(UrlServerConstants.PLANIT_ROOT, "JSESSIONID"));
                    clientCookie.setVersion(1);
                    clientCookie.setDomain("planit-dev.herokuapp.com");
                    clientCookie.setPath("/");
                    GlobalCookieStore.getCookieStore().addCookie(clientCookie);

                    client.setCookieStore(GlobalCookieStore.getCookieStore());
                    //finish();
                    //This will stop the actvity so that the JSON result will not be displayed. This should probs change in case of error.....
                    //how ever we will still have acess to their cookie
                }
            }

            @TargetApi(Build.VERSION_CODES.KITKAT)
            @Override
            public void onPageFinished(WebView view, String url) {
                if ((url.contains(UrlServerConstants.GOOGLE_AUTH_REDIRECT) && !url.contains(UrlServerConstants.GOOGLE_HOME)) || url.contains(UrlServerConstants.FACEBOOK_AUTH_REDIRECT)) {
                    super.onPageFinished(view, url);
                    // this is where we should read in the result of what is pulled back.
                    webView.evaluateJavascript("javascript:location.href", URLCALLBACK);
                    webView.evaluateJavascript("javascript:document.documentElement.innerText", valueCallback);

                    finish();
                }
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }

        });


        if (getIntent().getExtras() != null) {
            if (getIntent().getExtras().getString("login_type").equals("google")) {
                webView.loadUrl(UrlServerConstants.GOOGLE_LOGIN);
            } else if (getIntent().getExtras().getString("login_type").equals("facebook")) {
                webView.loadUrl(UrlServerConstants.FACEBOOK_LOGIN);
            }
        } else {
            webView.loadUrl(UrlServerConstants.PLANIT_ROOT);
        }


    }

    public String getCookie(String siteName, String cookieName) {
        try {
            String CookieValue = null;

            CookieManager cookieManager = CookieManager.getInstance();
            String cookies = cookieManager.getCookie(siteName);
            String[] temp = cookies.split("[;]");
            if (temp.length > 0) {
                for (String ar1 : temp) {
                    if (ar1.contains(cookieName)) {
                        String[] temp1 = ar1.split("[=]");
                        CookieValue = temp1[1];
                    }
                }
                return CookieValue;

            }
        } catch (Exception e) {

        }
        return GlobalCookieStore.getCookieStore().getCookies().get(0).getValue();
    }

    @Override
    protected void onDestroy() {
        if (this.isFinishing()) {
            // This is called when the activity is finishing. So we need to redirect the correct place.
        }
        super.onDestroy();
    }
}
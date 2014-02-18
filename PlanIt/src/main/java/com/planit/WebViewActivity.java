package com.planit;

/**
 * Created by Steven on 17/02/14.
 */

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.webkit.CookieManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.planit.constants.GlobalCookieStore;
import com.planit.constants.UrlServerConstants;

import org.apache.http.impl.cookie.BasicClientCookie;

public class WebViewActivity extends Activity {

    private WebView webView;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.webview);


        webView = (WebView) findViewById(R.id.webView1);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient() {

            @Override
            public void onPageStarted(WebView view, String url, Bitmap s) {
                if ((url.contains(UrlServerConstants.GOOGLE_AUTH_REDIRECT) && !url.contains(UrlServerConstants.GOOGLE_HOME)) || url.contains(UrlServerConstants.FACEBOOK_AUTH_REDIRECT)) {

                    BasicClientCookie clientCookie = new BasicClientCookie("JSESSIONID", getCookie(UrlServerConstants.PLANIT_ROOT, "JSESSIONID"));
                    clientCookie.setVersion(1);
                    clientCookie.setDomain("planit-dev.herokuapp.com");
                    clientCookie.setPath("/");
                    GlobalCookieStore.getCookieStore().addCookie(clientCookie);


                    try {
                        int duration = Toast.LENGTH_SHORT;
                        String login_type = getIntent().getExtras().getString("login_type");
                        Toast toast = Toast.makeText(view.getContext(), login_type, duration);
                        toast.show();
                    } catch (Exception e) {
                        //swallow
                    }

                    finish();
                    //This will stop the actvity so that the JSON result will not be displayed. This should probs change in case of error.....
                    //how ever we will still have acess to their cookie
                }
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }

        });

        if (getIntent().getExtras() != null){
            if (getIntent().getExtras().getString("login_type").equals("google")) {
                webView.loadUrl(UrlServerConstants.GOOGLE_LOGIN);
            } else if (getIntent().getExtras().getString("login_type").equals("facebook")) {
                webView.loadUrl(UrlServerConstants.FACEBOOK_LOGIN);
            }
        }else{
            webView.loadUrl(UrlServerConstants.PLANIT_ROOT);
        }


    }

    public String getCookie(String siteName, String cookieName) {
        String CookieValue = null;

        CookieManager cookieManager = CookieManager.getInstance();
        String cookies = cookieManager.getCookie(siteName);
        String[] temp = cookies.split("[;]");
        for (String ar1 : temp) {
            if (ar1.contains(cookieName)) {
                String[] temp1 = ar1.split("[=]");
                CookieValue = temp1[1];
            }
        }
        return CookieValue;
    }

}
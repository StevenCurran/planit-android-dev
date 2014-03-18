package com.planit.utils;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.planit.constants.GlobalCookieStore;

import org.apache.http.Header;

/**
 * Created by Steven on 26/02/14.
 */
public class WebClient {

    private static AsyncHttpClient client = new AsyncHttpClient();

    public static void get(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        client.setCookieStore(GlobalCookieStore.getCookieStore());
        client.get(url, params, responseHandler);
    }

    public static void post(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        client.post(url, params, responseHandler);
    }

    public static void post(String url, RequestParams params, AsyncHttpResponseHandler responseHandler, Header[] headers) {

        for (Header header : headers) {
            client.addHeader(header.getName(), header.getValue());
        }

        client.post(null, url, headers ,params, null, responseHandler);

        for (Header header : headers) {
            client.removeHeader(header.getName());
        }

    }

}

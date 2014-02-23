package com.planit.constants;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpException;
import org.apache.http.HttpRequest;
import org.apache.http.HttpRequestInterceptor;
import org.apache.http.HttpResponse;
import org.apache.http.RequestLine;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HttpContext;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by Steven on 23/02/14.
 */
public class LoginRunnable implements Runnable {

    private String url = "";
    private HttpRequestInterceptor INTERCEPT = new HttpRequestInterceptor() {

        @Override
        public void process(HttpRequest httpRequest, HttpContext httpContext) throws HttpException, IOException {
            RequestLine requestLine = httpRequest.getRequestLine();
            System.out.println(requestLine.getUri());
            HttpParams params = httpRequest.getParams();
            System.out.println(params);
            Header[] allHeaders = httpRequest.getAllHeaders();
            for (Header allHeader : allHeaders) {
                System.out.println(allHeader);
            }


        }
    };

    private static String convertStreamToString(InputStream is) {
    /*
     * To convert the InputStream to String we use the BufferedReader.readLine()
     * method. We iterate until the BufferedReader return null which means
     * there's no more data to read. Each line will appended to a StringBuilder
     * and returned as String.
     */
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();

        String line = null;
        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public void run() {

        try {

            DefaultHttpClient client = new DefaultHttpClient();
            client.addRequestInterceptor(INTERCEPT);


            HttpGet getRed = new HttpGet(url);
            HttpResponse response = client.execute(getRed);
            HttpEntity entity = response.getEntity();
            Header[] allHeaders = response.getAllHeaders();
            InputStream content = entity.getContent();
            String result = convertStreamToString(content);
            System.out.println(result);


        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}

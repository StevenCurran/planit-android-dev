package com.planit.tasks;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.util.Log;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.gcm.GoogleCloudMessaging;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.planit.User;
import com.planit.constants.GlobalUserStore;
import com.planit.constants.UrlServerConstants;
import com.planit.utils.WebClient;

import org.apache.http.Header;
import org.apache.http.message.BasicHeader;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Steven on 15/03/14.
 */
public class AsyncDeviceRegistrationTask implements Runnable {

    public static final String EXTRA_MESSAGE = "message";
    public static final String PROPERTY_REG_ID = "registration_id";
    static final String TAG = "GCM Demo";
    private static final String PROPERTY_APP_VERSION = "appVersion";
    private static final int PLAY_SERVICES_RESOLUTION_REQUEST = 9000;
    String SENDER_ID = "115023261213";
    //TextView mDisplay;
    GoogleCloudMessaging gcm;
    AtomicInteger msgId = new AtomicInteger();
    Context context;
    Activity activity;
    String regid = "";

    public AsyncDeviceRegistrationTask(Context context, Activity activity) {
        this.context = context;
        this.activity = activity;
    }

    private static int getAppVersion(Context context) {
        try {
            PackageInfo packageInfo = context.getPackageManager()
                    .getPackageInfo(context.getPackageName(), 0);
            return packageInfo.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            // should never happen
            throw new RuntimeException("Could not get package name: " + e);
        }
    }

    @Override
    public void run() {
        if (checkPlayServices()) {
            gcm = GoogleCloudMessaging.getInstance(activity);
            //regid = getRegistrationId(context);
            if (regid.isEmpty()) {
                //registerInBackground();

                String msg = "";
                try {
                    if (gcm == null) {
                        gcm = GoogleCloudMessaging.getInstance(context);
                    }
                    regid = gcm.register(SENDER_ID);
                    msg = "Device registered, registration ID=" + regid;
                    sendRegistrationIdToBackend();
//Turn this off for a bit to save usage.
                    // For this demo: we don't need to send it because the device will send

                } catch (IOException ex) {
                    System.out.println(ex.toString());
                }


            }
        } else {
            Log.i(TAG, "No valid Google Play Services APK found.");
        }


    }

    private boolean checkPlayServices() {
        int resultCode = GooglePlayServicesUtil.isGooglePlayServicesAvailable(activity);
        if (resultCode != ConnectionResult.SUCCESS) {
            if (GooglePlayServicesUtil.isUserRecoverableError(resultCode)) {
                GooglePlayServicesUtil.getErrorDialog(resultCode, activity,
                        PLAY_SERVICES_RESOLUTION_REQUEST).show();
            } else {
                Log.i(TAG, "This device is not supported.");

            }
            return false;
        }
        return true;
    }

    private void sendRegistrationIdToBackend() {
        User user = GlobalUserStore.getUser();
        String providerId = user.getId();
        RequestParams params = new RequestParams();
        params.put("providerid", providerId);
        params.put("deviceid", this.regid);

        Header h = new BasicHeader("providerid", providerId);

        WebClient.post(UrlServerConstants.DEVICE_GCM_REG, params, new AsyncHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                System.out.println();
                super.onSuccess(statusCode, headers, responseBody);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                System.out.println();
                super.onFailure(statusCode, headers, responseBody, error);
            }
        }, new Header[]{
                new BasicHeader("providerid", providerId),
                new BasicHeader("deviceid", this.regid)
        });

    }


}

package com.planit.constants;

import android.app.Application;
import android.content.Context;

/**
 * Created by Steven on 18/02/14.
 */
public class GlobalApplicationContext extends Application {

    private static Context context;

    public void onCreate(){
        super.onCreate();
        GlobalApplicationContext.context = getApplicationContext();
    }

    public static Context getAppContext() {
        return GlobalApplicationContext.context;
    }
}

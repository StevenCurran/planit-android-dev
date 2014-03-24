package com.planit.constants;

import com.loopj.android.http.PersistentCookieStore;

/**
 * Created by Steven on 18/02/14.
 */
public enum GlobalCookieStore {
    INSTANCE;

    private PersistentCookieStore cookieStore;

    private GlobalCookieStore() {
        this.cookieStore = new PersistentCookieStore(GlobalApplicationContext.getAppContext());
    }

    public static PersistentCookieStore getCookieStore() {
        return GlobalCookieStore.INSTANCE.cookieStore;
    }
}

package com.planit.constants;

import com.planit.User;

/**
 * Created by Steven on 26/02/14.
 */
public enum GlobalUserStore {

    INSTANCE;

    private User user;

    private GlobalUserStore(){
        this.user = new User();
    }

    public static User getCookieStore() {
        return GlobalUserStore.INSTANCE.user;
    }
}

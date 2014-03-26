package com.planit.constants;

/**
 * Created by Steven on 18/02/14.
 */
public class UrlServerConstants {

    public static final String PLANIT_ROOT = "http://planit-dev.herokuapp.com";

    //login urls
    public static final String FACEBOOK_LOGIN = PLANIT_ROOT + "/socialauth?id=facebook";
    public static final String GOOGLE_LOGIN = PLANIT_ROOT + "/googlelogin/login";


    //redirects
    public static final String GOOGLE_AUTH_REDIRECT = PLANIT_ROOT + "/googlelogin/oauthCallback";
    public static final String FACEBOOK_AUTH_REDIRECT = PLANIT_ROOT + "/authSuccess";
    //end points
    public static final String FACEBOOK_EVENTS = PLANIT_ROOT + "/fbevents";
    public static final String GOOGLE_EVENTS = PLANIT_ROOT + "/googlelogin/gcalEvents";
    public static final String DEVICE_GCM_REG = PLANIT_ROOT + "/googlelogin/deviceregistration";
    public static final String ATTENDEES = PLANIT_ROOT + "/googlelogin/getAttendees";
    //public urls
    public static final String GOOGLE_HOME = "google.com";
    public static final String PLANIT = PLANIT_ROOT + "/events/planit";
    public static final String ADD_EVENT = PLANIT_ROOT + "/events/addevent";
    public static final String GET_EVENT = PLANIT_ROOT + "/events/getevent";
    public static final String PENDING_EVENTS = PLANIT_ROOT + "/events/getpendingevents";
}

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
    public static final String GOOGLE_AUTH_REDIRECT  = PLANIT_ROOT + "/googlelogin/oauthCallback";
    public static final String FACEBOOK_AUTH_REDIRECT  = PLANIT_ROOT + "/authSuccess";



    //public urls
    public static final String GOOGLE_HOME = "google.com";


    //end points
    public static final String FACEBOOK_EVENTS = PLANIT_ROOT + "/fbevents";




}

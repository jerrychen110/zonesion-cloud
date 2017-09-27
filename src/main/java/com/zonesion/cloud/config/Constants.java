package com.zonesion.cloud.config;

/**
 * Application constants.
 */
public final class Constants {

    //Regex for acceptable logins
    public static final String LOGIN_REGEX = "^[_'.@A-Za-z0-9-]*$";

    public static final String SYSTEM_ACCOUNT = "system";
    
    public static final String ATTACHEMENT_TYPE_COURSE = "course";
    
    public static final String ATTACHEMENT_TYPE_LESSON = "lesson";
    
    public static final String COURSE_QUERY_RECOMMENDED = "recommended";
    
    public static final String COURSE_QUERY_NEWEST = "newest";
    
    public static final String COURSE_QUERY_HOT = "hot";
    
    public static final String COURSE_QUERY_MAJOR = "major";
    
    private Constants() {
    }
}

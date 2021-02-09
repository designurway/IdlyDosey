package com.designurway.idlidosa.a.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class PreferenceManager {
    private static SharedPreferences customerSharedPreferences;
    private static SharedPreferences countSharedPreferences;
    private static final String PREFERENCE="restaurant";
    private static final String ID="id";
    private static final String NAME="name";
    private static final String EMAIL="email";
    private static final String PHONE="phone";
    private static final String PASSWORD="password";
    private static final String REFERRAL_CODE="referral_code";
    private static final String REFERRED_FROM="referred_from";
    private static final String CHECK_LOGGED_IN = "logged_in";
    private static final String REMEMBER="remember_me";
    public static final String PREF_TOTAL_KEY = "pref_total_key";
    public static final String PREF_NAME = "pref_total_key";
    public static final String PREF_COUNT_NAME = "pref_total_key";

    public static void initialize(Context context) {
        customerSharedPreferences = context.getSharedPreferences(PREFERENCE, Context.MODE_PRIVATE);

    }

    public static void initializeCount(Context context) {
        countSharedPreferences = context.getSharedPreferences(PREF_COUNT_NAME, Context.MODE_PRIVATE);

    }

    public static void saveCustomer(String customer_id, String name, String email_id, String phone, String password,String code) {
        SharedPreferences.Editor editor=customerSharedPreferences.edit();

        editor.putString(ID,customer_id);
        editor.putString(NAME,name);
        editor.putString(EMAIL,email_id);
        editor.putString(PHONE,phone);
        editor.putString(PASSWORD,password);
        editor.putString(REFERRAL_CODE,code);


        editor.apply();


    }


    public static boolean saveCustomerLogin(String id, String email_id, String password, String name, String phone,
                                            String refCode, boolean remeber) {
        SharedPreferences.Editor editor = customerSharedPreferences.edit();

        editor.putString(ID, id);
        editor.putString(EMAIL, email_id);
        editor.putString(PASSWORD, password);
        editor.putString(NAME, name);
        editor.putString(PHONE, phone);
        editor.putString(REFERRAL_CODE, refCode);
        editor.putBoolean(REMEMBER, remeber);

        editor.apply();

        return false;
    }


/*    public static void saveCustomerLogin(String id,String email_id, String password, String name,String phone) {
        SharedPreferences.Editor editor=customerSharedPreferences.edit();

        editor.putString(ID,id);
        editor.putString(EMAIL,email_id);
        editor.putString(PASSWORD,password);
        editor.putString(NAME,name);
        editor.putString(PHONE,phone);

        editor.apply();


    }*/

    public static void saveCustomerReferred(String referredFrom) {
        SharedPreferences.Editor editor=customerSharedPreferences.edit();

        editor.putString(REFERRED_FROM,referredFrom);


        editor.apply();


    }



    public static String getCustomerEmail() {

      return   customerSharedPreferences.getString(EMAIL,"kruthika@gmail.com");
    }


    public static String getCustomerName() {

        return   customerSharedPreferences.getString(NAME,"");

    }

    public static String getCustomerPhone() {

        return   customerSharedPreferences.getString(PHONE,"");

    }

    public static String getCustomerId() {

        return   customerSharedPreferences.getString(ID,"");

    }

    public static String getCustomeReferenceCode() {

        return   customerSharedPreferences.getString(REFERRAL_CODE,"ZXCV");

    }

    public static String getCustomerReferred() {

        return   customerSharedPreferences.getString(REFERRED_FROM,"ZXCV");

    }

    public static void checkUserLoggedIn(boolean cust) {
        SharedPreferences.Editor editor=customerSharedPreferences.edit();
        editor.putBoolean(CHECK_LOGGED_IN,cust);
        editor.apply();

    }

    public static boolean getIsUserLoggedIn() {
        return customerSharedPreferences.getBoolean(CHECK_LOGGED_IN, false);
    }

    public static void clearCustomer(){
        SharedPreferences.Editor editor=customerSharedPreferences.edit();
        editor.clear();
        editor.apply();
    }


    public static void clearCount(){
        SharedPreferences.Editor countEditor=countSharedPreferences.edit();
        countEditor.clear();
        countEditor.apply();
    }


    public static void SaveTotalKey(Context context, int total) {
        SharedPreferences preferences = context.getSharedPreferences(PREF_COUNT_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor countEditor = preferences.edit();
        countEditor.putInt(PREF_TOTAL_KEY, total);
        countEditor.apply();
    }

    public static int loadFrompref(Context context) {
        SharedPreferences rpref = context.getSharedPreferences(PREF_COUNT_NAME, Context.MODE_PRIVATE);
        return rpref.getInt(PREF_TOTAL_KEY, 0);

    }

    public static void registerPrif(Context context, SharedPreferences.OnSharedPreferenceChangeListener lisner) {
        SharedPreferences rpref = context.getSharedPreferences(PREF_COUNT_NAME, Context.MODE_PRIVATE);
        rpref.registerOnSharedPreferenceChangeListener(lisner);
    }

    public static void unregisterPref(Context context, SharedPreferences.OnSharedPreferenceChangeListener lisner){
        SharedPreferences rpref = context.getSharedPreferences(PREF_COUNT_NAME, Context.MODE_PRIVATE);
        rpref.unregisterOnSharedPreferenceChangeListener(lisner);
    }

}

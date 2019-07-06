package com.asifahmedsohan.superoffer.usersession;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

public class UserSession {

    // Shared Preferences
    SharedPreferences pref;

    // Editor for Shared preferences
    SharedPreferences.Editor editor;

    // Context
    Context context;



    // Shared pref mode
    int PRIVATE_MODE = 0;

    // Sharedpref file name
    private static final String PREF_NAME = "UserSessionPref";

    // First time logic Check
    public static final String FIRST_TIME = "firsttime";


    public static final String KEY_WISHLIST = "wishlistvalue";
    public static final String IS_FIRST_TIME_LAUNCH = "IsFirstTimeLaunch";

    // Constructor
    public UserSession(Context context){
        this.context = context;
        pref = context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    public int getWishlistValue(){
        return pref.getInt(KEY_WISHLIST,0);
    }

    public Boolean  getFirstTime() {
        return pref.getBoolean(FIRST_TIME, true);
    }

    public void setFirstTime(Boolean n){
        editor.putBoolean(FIRST_TIME,n);
        editor.commit();
    }



    public void increaseWishlistValue(){
        int val = getWishlistValue()+1;
        editor.putInt(KEY_WISHLIST,val);
        editor.commit();
        //Log.e("Cart Value PE", "Var value : "+val+"Cart Value :"+getCartValue()+" ");
    }

    public void decreaseWishlistValue(){
        int val = getWishlistValue()-1;
        editor.putInt(KEY_WISHLIST,val);
        editor.commit();
       // Log.e("Cart Value PE", "Var value : "+val+"Cart Value :"+getCartValue()+" ");
    }

    public void setWishlistValue(int count){
        editor.putInt(KEY_WISHLIST,count);
        editor.commit();
    }

    public void setFirstTimeLaunch(boolean isFirstTime) {
        editor.putBoolean(IS_FIRST_TIME_LAUNCH, isFirstTime);
        editor.commit();
    }

    public boolean isFirstTimeLaunch() {
        return pref.getBoolean(IS_FIRST_TIME_LAUNCH, true);
    }

}

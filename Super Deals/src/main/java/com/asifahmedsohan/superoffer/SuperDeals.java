package com.asifahmedsohan.superoffer;

import android.app.Application;

import com.google.firebase.database.FirebaseDatabase;

public class SuperDeals extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        FirebaseDatabase.getInstance().setPersistenceEnabled(true);
    }
}

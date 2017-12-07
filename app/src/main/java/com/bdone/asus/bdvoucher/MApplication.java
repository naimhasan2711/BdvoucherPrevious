package com.bdone.asus.bdvoucher;

import android.support.multidex.MultiDexApplication;

import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by Rasel on 5/21/2017.
 */

public class MApplication extends MultiDexApplication {
    @Override
    public void onCreate() {
        super.onCreate();
        FirebaseDatabase.getInstance().setPersistenceEnabled(true);
    }
}

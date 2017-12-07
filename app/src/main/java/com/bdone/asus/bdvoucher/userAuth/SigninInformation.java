package com.bdone.asus.bdvoucher.userAuth;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.HashMap;

/**
 * Created by mitu on 8/3/16.
 */
public class SigninInformation {
    private SharedPreferences mPref;
    private SharedPreferences.Editor mEditor;
    private Context mContext;
    private int PRIVATE_MODE = 0;
    private static final String PREF_SIZE = "Ajkerdealprefsignin";
    public static final String AJKERDEAL_SIGNIN = "Ajkerdealisignin";
    public static final String FACEBOOK = "facebook";
    public static final String GOOGLE = "google";


    public SigninInformation(Context context) {
        this.mContext = context;
        mPref = mContext.getSharedPreferences(PREF_SIZE, PRIVATE_MODE);
        mEditor = mPref.edit();
    }

    public void storeSignin(int ad, int facebook, int google) {

        mEditor.putInt(AJKERDEAL_SIGNIN, ad);
        mEditor.putInt(FACEBOOK, facebook);
        mEditor.putInt(GOOGLE, google);
        mEditor.commit();
    }

    public void clearSignin(Context context) {
        // Clearing all data from Shared Preferences
        if (mContext != null) {

            mEditor.clear();
            mEditor.commit();
        }
    }

    public HashMap<String, Integer> getSigninInformation() {
        HashMap<String, Integer> SignInformation = new HashMap<>();

        SignInformation.put(AJKERDEAL_SIGNIN, mPref.getInt(AJKERDEAL_SIGNIN, -1));
        SignInformation.put(FACEBOOK, mPref.getInt(FACEBOOK, -1));
        SignInformation.put(GOOGLE, mPref.getInt(GOOGLE, -1));

        return SignInformation;
    }
}

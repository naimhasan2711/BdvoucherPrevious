package com.bdone.asus.bdvoucher.userAuth;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.HashMap;

/**
 * Created by mitu on 6/14/16.
 */
public class SessionManager {
    private SharedPreferences pref;
    private SharedPreferences.Editor editor;
    private Context _context;
    private int PRIVATE_MODE = 0;
    private static final String PREF_NAME = "Ajkerdealpref";
    private static final String IS_LOGIN = "IsLoggedIn";
    public static final String KEY_PASSWORD = "password";
    public static final String KEY_EMAIL = "email";
    public static final String UserIdKey="userIdKey";
    public static final String PhoneKey="phoneKey";
    public static final String UserName="user";
    public SessionManager(Context context){
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();

    }

    public void createLoginSession(String name,String password, String email, String userMobile, int userLoginId){
        // Storing login value as TRUE
        editor.putBoolean(IS_LOGIN, true);
        editor.putString(UserName,name);

        // Storing password in pref
        editor.putString(KEY_PASSWORD, password);

        // Storing email in pref
        editor.putString(KEY_EMAIL, email);

        editor.putString(PhoneKey,userMobile);

        editor.putInt(UserIdKey,userLoginId);

        // commit changes
        editor.commit();
    }



    public HashMap<String, String> getUserDetails(){
        HashMap<String, String> user = new HashMap<String, String>();
        // user password
        user.put(KEY_PASSWORD, pref.getString(KEY_PASSWORD, null));

        // user email id
        user.put(KEY_EMAIL, pref.getString(KEY_EMAIL, null));

        user.put(PhoneKey, pref.getString(PhoneKey, null));
        user.put(UserName,pref.getString(UserName,null));

        // return user
        return user;
    }


    public HashMap<String, Integer> getUserID(){
        HashMap<String, Integer> user = new HashMap<>();
        user.put(UserIdKey,pref.getInt(UserIdKey,-1));
        return  user;
    }


    public void logoutUser(Context context){
        // Clearing all data from Shared Preferences
        if (context != null) {

            editor.clear();
            editor.commit();
        }

//        // After logout redirect user to Loing Activity
//        Intent i = new Intent(_context, LoginActivity.class);
//        // Closing all the Activities
//        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//
//        // Add new Flag to start new Activity
//        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//
//        // Staring Login Activity
//        _context.startActivity(i);


    }

    public boolean isLoggedIn(){
        return pref.getBoolean(IS_LOGIN, false);
    }
}

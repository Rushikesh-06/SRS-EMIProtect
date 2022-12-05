package com.example.gexemi;

import android.content.Context;
import android.content.SharedPreferences;

public class SessionManage {
    SharedPreferences preferences;
    SharedPreferences.Editor editor;

    public SessionManage(Context context) {
        preferences = context.getSharedPreferences("VendorDetails", Context.MODE_PRIVATE);
        editor = preferences.edit();
    }

    public boolean getLoginStatus() {
        return preferences.getBoolean("Is_login", false);
    }

    public void addLoginStatus( boolean status) {
        editor.putBoolean("Is_login", status);
        editor.commit();
    }
}

package com.example.festafimdeano.Data;

import android.content.Context;
import android.content.SharedPreferences;

public class SecurityPreferences {

    private SharedPreferences mSharedPreferences;

    public SecurityPreferences(Context mContext){
        String nome = "Festa";
        this.mSharedPreferences = (SharedPreferences) mContext.getSharedPreferences(nome, Context.MODE_PRIVATE);
    }

    public void storeString(String key, String Value){
        this.mSharedPreferences.edit().putString(key, Value).apply();
    }

   public String getStoredString( String key){
        return mSharedPreferences.getString(key, "None");
   }
}

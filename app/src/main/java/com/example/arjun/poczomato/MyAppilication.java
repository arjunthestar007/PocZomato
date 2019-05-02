package com.example.arjun.poczomato;

import android.app.Application;
import android.content.res.Configuration;
import android.widget.Toast;

public class MyAppilication extends Application {

    private  String Username="arjun";


    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }


    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        Toast.makeText(this, ""+newConfig, Toast.LENGTH_SHORT).show();
    }
}

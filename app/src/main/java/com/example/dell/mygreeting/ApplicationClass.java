package com.example.dell.mygreeting;

import android.app.Application;

/**
 * Created by DELL on 1/17/2017.
 */

public class ApplicationClass extends Application {

    public static ApplicationClass instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }

    public static ApplicationClass getGreetingApp(){
        return instance;
    }
}

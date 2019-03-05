package com.chohee.common;

import android.app.Application;

import com.beardedhen.androidbootstrap.TypefaceProvider;

/**
 * Created by amrs on 2017-05-16.
 */

public class MyBootstrap extends Application{
    @Override public void onCreate(){
        super.onCreate();
        TypefaceProvider.registerDefaultIconSets();
    }
}

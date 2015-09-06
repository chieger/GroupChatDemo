package com.example.charleshieger.groupchatdemo;

import android.app.Application;

import com.parse.Parse;

/**
 * Created by charleshieger on 8/24/15.
 */
public class myApplication extends Application {

    public static final String YOUR_APPLICATION_ID = "38gxBWoTm8MgBdaLaoBSAlH5b2GXn3lTOeDJP6zJ";

    public static final String YOUR_CLIENT_ID = "9MQ8Dumly4WNM0wWTRHHixc9Ga0G2DJoZzxtmN1L";

    @Override
    public void onCreate() {
        super.onCreate();

        Parse.enableLocalDatastore(this);
        Parse.initialize(this, YOUR_APPLICATION_ID, YOUR_CLIENT_ID);
    }
}

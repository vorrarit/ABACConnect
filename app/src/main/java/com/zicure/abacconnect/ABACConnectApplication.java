package com.zicure.abacconnect;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseInstallation;

/**
 * Created by mid on 11/13/2015.
 */
public class ABACConnectApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Parse.initialize(this, "XxU12W3sWJ7PDKb18T5FtBOOnV9tpwjsLRqlGZ5o", "IWUkdfXCCi0srHG5ptFRRCoU05aImzTbbrN3oWph");
        ParseInstallation.getCurrentInstallation().saveInBackground();
    }

}

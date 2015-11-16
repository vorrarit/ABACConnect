package com.zicure.abacconnect;

import android.content.Context;

/**
 * Created by DUMP129 on 9/24/2015.
 */
public class ApplicationContext {
    private static ApplicationContext appContext = null;

    private Context context = null;

    private ApplicationContext() {
    }

    public static ApplicationContext getInstance() {
        if (appContext == null) {
            appContext = new ApplicationContext();
        }
        return appContext;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public Context getContext() {
        return this.context;
    }
}
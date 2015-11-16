package com.zicure.abacconnect.api;


import java.util.Objects;

/**
 * Created by DUMP129 on 10/2/2015.
 */
public interface AsyncTaskListener {
    public void onTaskComplete(String action, Object result);
}

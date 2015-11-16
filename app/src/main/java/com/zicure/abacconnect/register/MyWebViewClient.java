package com.zicure.abacconnect.register;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebView;

public class MyWebViewClient extends WebChromeClient {
	private Context myApp = null;
	
    public MyWebViewClient(Context context) {
		super();
		this.myApp = context;
	}

	@Override
    public boolean onJsConfirm(WebView view, String url, String message, final JsResult result) {
        new AlertDialog.Builder(myApp)
        .setTitle("LTC+ Live Connect")
        .setMessage(message)
        .setPositiveButton(android.R.string.ok,
                new DialogInterface.OnClickListener()
        {
            public void onClick(DialogInterface dialog, int which)
            {
                result.confirm();
            }
        })
        .setNegativeButton(android.R.string.cancel,
                new DialogInterface.OnClickListener()
        {
            public void onClick(DialogInterface dialog, int which)
            {
                result.cancel();
            }
        })
        .create()
        .show();

        return true;
    }
}

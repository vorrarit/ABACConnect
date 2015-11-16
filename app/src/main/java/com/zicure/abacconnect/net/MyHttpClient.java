package com.zicure.abacconnect.net;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.util.Log;
import android.webkit.WebSettings;
import android.webkit.WebView;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.protocol.ClientContext;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.util.List;

public class MyHttpClient extends DefaultHttpClient {

    private static MyHttpClient me = null;
    private BasicCookieStore cookieStore = null;
    private HttpContext localContext = null;
    private String userAgent = "";
    int timeoutConnection = 5000;
    int timeoutSocket = 5000;

    public static MyHttpClient getInstance(Context context) {
        if (me == null) me = new MyHttpClient(context);
        return me;
    }

    @SuppressLint("NewApi")
    private MyHttpClient(Context context) {
        super();

        HttpParams httpParams = new BasicHttpParams();
        HttpConnectionParams.setConnectionTimeout(httpParams, timeoutConnection);
        HttpConnectionParams.setSoTimeout(httpParams, timeoutSocket);

        setParams(httpParams);
        cookieStore = new BasicCookieStore();
        localContext = new BasicHttpContext();
        localContext.setAttribute(ClientContext.COOKIE_STORE, cookieStore);

        if (Build.VERSION.SDK_INT >= 17) {
            userAgent = WebSettings.getDefaultUserAgent(context);
        } else {
            try {
                Constructor<WebSettings> constructor = WebSettings.class.getDeclaredConstructor(Context.class, WebView.class);
                constructor.setAccessible(true);
                try {
                    WebSettings settings = constructor.newInstance(context, null);
                    userAgent = settings.getUserAgentString();
                    constructor.setAccessible(false);
                } catch (Exception e) {
                    userAgent = new WebView(context).getSettings().getUserAgentString();
                } finally {
                    constructor.setAccessible(false);
                }
            } catch (NoSuchMethodException e) {
                userAgent = "Mozilla/5.0 (Linux; U; Android 4.1.1; en-us; Google Galaxy Nexus - 4.1.1 - API 16 - 720x1280 Build/JRO03S) AppleWebKit/534.30 (KHTML, like Gecko) Version/4.0 Mobile Safari/534.30";
            }

        }
    }

    public HttpResponse executeWithContext(HttpUriRequest request) throws ClientProtocolException, IOException {
        Log.d("LTCP", request.getURI().toString());
        request.setHeader("User-Agent", userAgent);

        HttpResponse response = super.execute(request, localContext);
        Log.d("LTCP", "MyHttpClient Request Headers");
        Header[] headers = request.getAllHeaders();
        for (int i = 0; i < headers.length; i++) {
            Log.d("LTCP", headers[i].getName() + " " + headers[i].getValue());
        }

        Log.d("LTCP", "MyHttpClient Response Headers");
        headers = response.getAllHeaders();
        for (int i = 0; i < headers.length; i++) {
            Log.d("LTCP", headers[i].getName() + " " + headers[i].getValue());
        }

        Log.d("LTCP", "MyHttpClient Cookies");
        List<Cookie> cookies = cookieStore.getCookies();
        for (Cookie cookie : cookies) {
            Log.d("LTCP", "Domain=" + cookie.getDomain() + " Path=" + cookie.getPath() + " CookieName=" + cookie.getName() + " CookieValue=" + cookie.getValue());
        }

        return response;
    }

    public BasicCookieStore getMyCookieStore() {
        return cookieStore;
    }
}

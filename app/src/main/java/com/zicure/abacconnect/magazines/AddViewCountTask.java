package com.zicure.abacconnect.magazines;

import android.os.AsyncTask;

import com.zicure.abacconnect.ApplicationContext;
import com.zicure.abacconnect.api.AsyncTaskListener;
import com.zicure.abacconnect.magazines.Magazine;
import com.zicure.abacconnect.api.ApiConfig;
import com.zicure.abacconnect.net.MyHttpClient;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by DUMP129 on 10/12/2015.
 */
public class AddViewCountTask extends AsyncTask<String, Integer, String> {
    private String responseString = "", url = ApiConfig.API_URL, apiCurrent = "";
    private AsyncTaskListener asyncTaskListener = null;

    public void setAsyncTaskListener(AsyncTaskListener asyncTaskListener) {
        this.asyncTaskListener = asyncTaskListener;
    }

    /**
     * This method is get url.json and read json.
     *
     * @return responseString
     */
    @Override
    protected String doInBackground(String... params) {

        for (int i = 0; i < params.length; i++) {
            url += "/" + params[i];
            if (i == 0) {
                apiCurrent = params[i];
            }
        }

        url += ".json";

        MyHttpClient client = MyHttpClient.getInstance(ApplicationContext.getInstance().getContext());
        HttpGet httpGet = new HttpGet(url);
        StringBuilder str = new StringBuilder();
        try {
            HttpResponse response = client.execute(httpGet);
            HttpEntity entity = response.getEntity();
            InputStream stream = entity.getContent();
            BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
            String line;
            while ((line = reader.readLine()) != null) {
                str.append(line);
            }
            responseString = str.toString();
        } catch (ClientProtocolException cpe) {
            responseString = cpe.getMessage();
            cpe.printStackTrace();
        } catch (IOException e) {
            responseString = e.getMessage();
            e.printStackTrace();
        } catch (Exception e) {
            responseString = e.getMessage();
            e.printStackTrace();
        }

        return responseString;
    }

    /**
     * This method is decode JSONObject to JSOnArray
     * When finish call asyncTaskListener.onTaskComplete then send specific url and yearStr
     *
     * @return void
     */
    @Override
    protected void onPostExecute(String result) {
        if ("addViewCount".equals(apiCurrent)) {
            Magazine magazine = new Magazine();
            List<Magazine> magazineList = new ArrayList<Magazine>();
            JSONObject jsonResult = null;
            try {
                jsonResult = new JSONObject(result);
                jsonResult = jsonResult.getJSONObject("result");
                if ("OK".equals(jsonResult.getString("Success"))) {
                    jsonResult = jsonResult.getJSONObject("Data");

                    JSONObject jsonObject = new JSONObject();
                    jsonObject = jsonResult.getJSONObject("Magazine");
                    magazine.id = Integer.parseInt(jsonObject.getString("id"));
                    magazine.view_count = jsonObject.getString("view_count");
                    magazineList.add(magazine);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            asyncTaskListener.onTaskComplete("addViewCount", magazine.view_count);
        }
        super.onPostExecute(result);
    }
}

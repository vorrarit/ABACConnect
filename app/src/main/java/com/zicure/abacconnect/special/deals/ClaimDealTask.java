package com.zicure.abacconnect.special.deals;

import android.os.AsyncTask;
import android.widget.Toast;

import com.zicure.abacconnect.ApplicationContext;
import com.zicure.abacconnect.api.ApiConfig;
import com.zicure.abacconnect.api.AsyncTaskListener;
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

/**
 * Created by DUMP129 on 11/6/2015.
 */
public class ClaimDealTask extends AsyncTask<String, Void, String> {
    private String responseString = "", url = ApiConfig.API_URL, apiCurrent = "";
    private AsyncTaskListener asyncTaskListener = null;

    public void setAsyncTaskListener(AsyncTaskListener asyncTaskListener) {
        this.asyncTaskListener = asyncTaskListener;
    }

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

    @Override
    protected void onPostExecute(String result) {
        if ("claimDeal".equals(apiCurrent)) {
            JSONObject jsonResult = null;
            try {
                jsonResult = new JSONObject(result);
                jsonResult = jsonResult.getJSONObject("result");
                if ("OK".equals(jsonResult.getString("Success"))) {
                    Toast.makeText(ApplicationContext.getInstance().getContext(), "Claim was successfully", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(ApplicationContext.getInstance().getContext(), "Can't claim", Toast.LENGTH_LONG).show();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        super.onPostExecute(result);
    }
}
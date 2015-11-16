package com.zicure.abacconnect.business.connect;

import android.app.ProgressDialog;
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
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by DUMP129 on 11/9/2015.
 */
public class FetchBusinessConnectTask extends AsyncTask<String, Void, String> {
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
        if ("fetchBusiness".equals(apiCurrent)) {
            List<BusinessConnections> businessConnectionsList = new ArrayList<>();
            JSONObject jsonResult = null;
            try {
                jsonResult = new JSONObject(result);
                jsonResult = jsonResult.getJSONObject("result");
                if ("OK".equals(jsonResult.getString("Success"))) {
                    jsonResult = jsonResult.getJSONObject("Data");

                    JSONObject jsonObject = new JSONObject();
                    JSONArray jsonArray = new JSONArray();

                    jsonArray = jsonResult.getJSONArray("Business");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        BusinessConnections businessConnections = new BusinessConnections();
                        businessConnections.id = Integer.parseInt(jsonArray.getJSONObject(i).getString("id"));
                        businessConnections.company_name = jsonArray.getJSONObject(i).getString("company_name");
                        businessConnections.short_description = jsonArray.getJSONObject(i).getString("short_description");
                        businessConnections.street_address = jsonArray.getJSONObject(i).getString("street_address");
                        businessConnections.province_id = Integer.parseInt(jsonArray.getJSONObject(i).getString("province_id"));
                        businessConnections.district_id = Integer.parseInt(jsonArray.getJSONObject(i).getString("district_id"));
                        businessConnections.locality_id = Integer.parseInt(jsonArray.getJSONObject(i).getString("locality_id"));
                        businessConnections.zipcode = jsonArray.getJSONObject(i).getString("zipcode");
                        businessConnections.long_description = jsonArray.getJSONObject(i).getString("long_description");
                        businessConnections.contact_person = jsonArray.getJSONObject(i).getString("contact_person");
                        businessConnections.contact_phone = jsonArray.getJSONObject(i).getString("contact_phone");
                        businessConnections.contact_email = jsonArray.getJSONObject(i).getString("contact_email");
                        /*  businessConnections.province_name = jsonArray.getJSONObject(i).getString("province_name");
                        businessConnections.province_name_eng = jsonArray.getJSONObject(i).getString("province_name_eng");
                        businessConnections.district_name = jsonArray.getJSONObject(i).getString("district_name");
                        businessConnections.district_name_eng = jsonArray.getJSONObject(i).getString("district_name_eng");
                        businessConnections.locality_name = jsonArray.getJSONObject(i).getString("locality_name");
                        businessConnections.locality_name_eng = jsonArray.getJSONObject(i).getString("locality_name_eng");
                        businessConnections.contact_thumbnail = jsonArray.getJSONObject(i).getString("contact_thumbnail");
                        businessConnections.contact_position = jsonArray.getJSONObject(i).getString("contact_position");
                        businessConnections.business_thumbnail = jsonArray.getJSONObject(i).getString("business_thumbnail");
                        businessConnections.created = jsonArray.getJSONObject(i).getString("created");
                        businessConnections.modified = jsonArray.getJSONObject(i).getString("modified");
                        businessConnections.notify_date = jsonArray.getJSONObject(i).getString("notify_date");
                        businessConnections.geo_id = jsonArray.getJSONObject(i).getString("geo_id");*/


                        businessConnectionsList.add(businessConnections);
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            } catch (Exception e) {
                Toast.makeText(ApplicationContext.getInstance().getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
            }
            asyncTaskListener.onTaskComplete("fetchBusiness", businessConnectionsList);
        }
        super.onPostExecute(result);
    }
}

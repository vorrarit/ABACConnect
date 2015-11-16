package com.zicure.abacconnect.alumni.search;

import android.os.AsyncTask;
import android.widget.Toast;

import com.zicure.abacconnect.ApplicationContext;
import com.zicure.abacconnect.api.ApiConfig;
import com.zicure.abacconnect.api.AsyncTaskListener;
import com.zicure.abacconnect.business.connect.BusinessConnections;
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
public class AlumniSearchTask extends AsyncTask<String, Void, String> {
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
        if ("fetchAlumni".equals(apiCurrent)) {
            List<Alumni> alumniList = new ArrayList<>();

            JSONObject jsonResult = null;
            try {
                jsonResult = new JSONObject(result);
                jsonResult = jsonResult.getJSONObject("result");
                if ("OK".equals(jsonResult.getString("Success"))) {
                    JSONArray jsonArray = new JSONArray();
                    jsonArray = jsonResult.getJSONArray("Data");
                    JSONObject jsonObjectUser = new JSONObject();

                    for (int i = 0; i < jsonArray.length(); i++) {
                        Alumni alumni = new Alumni();
                        jsonObjectUser = jsonArray.getJSONObject(i).getJSONObject("User");
                        alumni.id = Integer.parseInt(jsonObjectUser.getString("id"));
                        alumni.user_username = jsonObjectUser.getString("user_username");
                        alumni.user_firstname = jsonObjectUser.getString("user_firstname");
                        alumni.user_lastname = jsonObjectUser.getString("user_lastname");
                        alumni.user_email = jsonObjectUser.getString("user_email");


                        JSONArray jsonArrayBusiness = new JSONArray();
                        JSONObject jsonObjectBusiness = new JSONObject();
                        jsonArrayBusiness = jsonArray.getJSONObject(i).getJSONArray("Business");
                        for (int j = 0; j < jsonArrayBusiness.length(); j++) {
                            jsonObjectBusiness = jsonArrayBusiness.getJSONObject(j);

                            alumni.id = Integer.parseInt(jsonObjectBusiness.getString("id"));
                            alumni.company_name = jsonObjectBusiness.getString("company_name");
                            alumni.short_description = jsonObjectBusiness.getString("short_description");
                            alumni.contact_person = jsonObjectBusiness.getString("contact_person");
                            alumni.contact_phone = jsonObjectBusiness.getString("contact_phone");
                            alumni.contact_email = jsonObjectBusiness.getString("contact_email");
                          //  alumni.contact_position = jsonObjectUser.getString("contact_position");
                        }

                        JSONArray jsonArrayWork = new JSONArray();
                        JSONObject jsonObjectWork = new JSONObject();
                        jsonArrayWork = jsonArray.getJSONObject(i).getJSONArray("Work");
                        for (int k = 0; k < jsonArrayWork.length(); k++) {
                            jsonObjectWork = jsonArrayWork.getJSONObject(k);

                            alumni.id = Integer.parseInt(jsonObjectWork.getString("id"));
                            alumni.work_from = jsonObjectWork.getString("work_from");
                            alumni.work_to = jsonObjectWork.getString("work_to");
                            alumni.company_name = jsonObjectWork.getString("company_name");
                            alumni.work_position = jsonObjectWork.getString("work_position");
                        }

                        alumniList.add(alumni);
                    }
                }

                asyncTaskListener.onTaskComplete("fetchAlumni", alumniList);
            } catch (JSONException e) {
                asyncTaskListener.onTaskComplete("fetchAlumni", alumniList);
                e.printStackTrace();
            } catch (Exception e) {
                asyncTaskListener.onTaskComplete("fetchAlumni", alumniList);
                Toast.makeText(ApplicationContext.getInstance().getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
            }

            super.onPostExecute(result);
        }
    }
}
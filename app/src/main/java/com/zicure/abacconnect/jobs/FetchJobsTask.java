package com.zicure.abacconnect.jobs;

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
import java.util.ArrayList;
import java.util.List;

/**
 * Created by DUMP129 on 11/11/2015.
 */
public class FetchJobsTask extends AsyncTask<String, Void, String> {
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
        if ("fetchJobs".equals(apiCurrent)) {
            List<Jobss> jobssList = new ArrayList<>();
            JSONObject jsonResult = null;
            try {
                jsonResult = new JSONObject(result);
                jsonResult = jsonResult.getJSONObject("result");
                if ("OK".equals(jsonResult.getString("Success"))) {
                    jsonResult = jsonResult.getJSONObject("Data");

                    JSONObject jsonObject = new JSONObject();
                    jsonObject = jsonResult.getJSONObject("Jobs");
                    Jobss jobss = new Jobss();
                    jobss.id = Integer.parseInt(jsonObject.getString("id"));
                    jobss.job_name = jsonObject.getString("job_name");
                    jobss.job_position = jsonObject.getString("job_position");
                    jobss.job_addr = jsonObject.getString("job_addr");
                    jobss.created = jsonObject.getString("created");
                    jobss.modified = jsonObject.getString("modified");
                    jobss.job_num = jsonObject.getString("job_num");
                    jobss.job_tel = jsonObject.getString("job_tel");
                    jobss.job_responsibility = jsonObject.getString("job_responsibility");
                    jobss.job_qualification = jsonObject.getString("job_qualification");
//                    jobss.job_group_id = Integer.parseInt(jsonObject.getString("job_group_id"));
               //     jobss.student_id = Integer.parseInt(jsonObject.getString("student_id"));
               //     jobss.job_expiry_date = jsonObject.getString("job_expiry_date");
               //     jobss.is_active = jsonObject.getString("is_active");

                    jobssList.add(jobss);

                }
            } catch (JSONException e) {
                e.printStackTrace();
            } catch (Exception e) {
                Toast.makeText(ApplicationContext.getInstance().getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
            }
            asyncTaskListener.onTaskComplete("fetchJobs", jobssList);
        }
        super.onPostExecute(result);
    }
}
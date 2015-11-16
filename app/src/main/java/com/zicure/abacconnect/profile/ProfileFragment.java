package com.zicure.abacconnect.profile;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.zicure.abacconnect.R;
import com.zicure.abacconnect.api.ApiConfig;
import com.zicure.abacconnect.main.MainActivity;
import com.zicure.abacconnect.model.Users;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.ByteArrayEntity;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class ProfileFragment extends Fragment {
    public TextView tvStudentId, tvProfileFirstName, tvProfileLastName, tvProfileEmail, tvProfileWork, tvProfilePosition, tvName, tvLastName;
    private Users users;
    private ImageView imgViewProfile;

    public ProfileFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        imgViewProfile = (ImageView) view.findViewById(R.id.imgViewProfile);
        tvName = (TextView) view.findViewById(R.id.tvName);
        tvLastName = (TextView) view.findViewById(R.id.tvLastName);
        tvStudentId = (TextView) view.findViewById(R.id.tvProfileStudentId);
        tvProfileFirstName = (TextView) view.findViewById(R.id.tvProfileFirstName);
        tvProfileLastName = (TextView) view.findViewById(R.id.tvProfileLastName);
        tvProfilePosition = (TextView) view.findViewById(R.id.tvProfilePosition);
        tvProfileEmail = (TextView) view.findViewById(R.id.tvProfileEmail);
        tvProfileWork = (TextView) view.findViewById(R.id.tvProfileWork);
        Users users = new Users();
        SharedPreferences pref = getActivity().getSharedPreferences("ABACLogin", Context.MODE_PRIVATE);
        String username = pref.getString("username", "");
        users.user_username = username;
        Gson gson = new Gson();

        //String imgUrl = ApiConfig.IMG_URL + ""

       /* Glide.with(getActivity())
                .load()*/

        String sUserName = gson.toJson(users);
        ProfileTask profileTask = new ProfileTask(getActivity());
        profileTask.setRegister(sUserName);
        profileTask.execute("profile");
    }

    public void setProfile(Users users) {

        this.users = users;
        String imgUrl;


            if (users.user_pic!=null) {
                imgUrl = ApiConfig.IMG_URL + users.user_pic.toString();
                Glide.with(getActivity())
                        .load(imgUrl)
                        .centerCrop()
                        .into(imgViewProfile);
            } else {
                imgUrl = "";
            }

        String studentId = users.user_username.toString();
        if (studentId != null) {
            tvStudentId.setText(studentId);
        } else {
            studentId = "";
        }

        String firstName = users.user_firstname.toString();
        if (firstName != null) {
            tvProfileFirstName.setText(firstName);
            tvName.setText(firstName);
        } else {
            firstName = "";
        }

        String lastName = users.user_lastname.toString();
        if (lastName != null) {
            tvProfileLastName.setText(lastName);
            tvLastName.setText(lastName);
        } else {
            lastName = "";
        }

        String email = users.user_email.toString();
        if (email != null) {
            tvProfileEmail.setText(email);
        } else {
            email = "";
        }

        String ProfileWork = users.company_name.toString();

        if (!ProfileWork.equals("null")) {
            ProfileWork = users.company_name.toString();
        } else {
            ProfileWork = "";
        }
        tvProfileWork.setText(ProfileWork);

        String company;
        if (!users.work_position.toString().equals("null")) {
            company = users.work_position.toString();
        } else {
            company = "";
        }
        tvProfilePosition.setText(company);
    }

    public class ProfileTask extends AsyncTask<String, Integer, String> {

        private String method = "GET";
        private String rawPostData = "";
        private String currentApi = "";
        private Context context;

        public void setRegister(String sRegister) {
            this.rawPostData = sRegister;
            this.method = "POST";
        }

        public ProfileTask(Context context) {
            this.context = context;
        }

        @Override
        protected String doInBackground(String... params) {
            String responseString = "";

            String url = ApiConfig.API_URL;
            for (int i = 0; i < params.length; i++) {
                url += "/" + params[i];

                if (i == 0) {
                    currentApi = params[0];
                }
            }
            url += ".json";
            Log.d("ABAC", url);
            com.zicure.abacconnect.net.MyHttpClient client = com.zicure.abacconnect.net.MyHttpClient.getInstance(context);
            HttpUriRequest httpget = null;

            if (this.method.equals("GET")) {
                httpget = new HttpGet(url);
            } else {
                httpget = new HttpPost(url);
                ((HttpPost) httpget).setEntity(new ByteArrayEntity(rawPostData.getBytes()));
            }
            try {

                StringBuilder str = new StringBuilder();
                HttpResponse response = client.executeWithContext(httpget);

                HttpEntity entity = response.getEntity();
                InputStream content = entity.getContent();
                BufferedReader reader = new BufferedReader(new InputStreamReader(content));
                String line;
                while ((line = reader.readLine()) != null) {
                    str.append(line);
                }

                responseString = str.toString();

            } catch (ClientProtocolException e) {
                responseString = e.getMessage();
                e.printStackTrace();
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

            try {
                if (currentApi.equals("profile")) {
                    JSONObject jResult = new JSONObject(result);
                    Log.d("ABAC result", result);
                    if ("OK".equals(jResult.getJSONObject("result").getString("Success"))) {
                        JSONObject studentFile = jResult.getJSONObject("result").getJSONObject("Data").getJSONObject("User");
                        JSONArray jsonArray = jResult.getJSONObject("result").getJSONObject("Data").getJSONArray("Work");
                        if (jsonArray.length() == 0) {
                            studentFile.put("company_name", "");
                            studentFile.put("work_position", "");
                        } else {
                            for (int i = 0; i < jsonArray.length(); i++) {
                                studentFile.put("company_name", jsonArray.getJSONObject(i).getString("company_name"));
                                studentFile.put("work_position", jsonArray.getJSONObject(i).getString("work_position"));
                            }
                        }

                        String fStudentFile = studentFile.toString();
                        Gson gson = new Gson();
                        Users users = gson.fromJson(fStudentFile, Users.class);
                        setProfile(users);
                        Log.d("ABAC Student", fStudentFile);
                    } else {
                        new AlertDialog.Builder(context)
                                .setTitle("Profile Student")
                                .setMessage("Can not to Find")
                                .setPositiveButton("Cancel", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {

                                        dialog.cancel();

                                    }
                                })
                                .setNegativeButton("Back to Main", new DialogInterface.OnClickListener() {

                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        Intent intent = new Intent(context, MainActivity.class);
                                        startActivity(intent);
                                        dialog.cancel();
                                    }
                                })
                                .setIcon(android.R.drawable.ic_dialog_alert)
                                .show();
                    }


                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            super.onPostExecute(result);
        }
    }
}
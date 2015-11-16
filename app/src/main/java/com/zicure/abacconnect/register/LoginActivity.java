package com.zicure.abacconnect.register;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.zicure.abacconnect.main.MainActivity;
import com.zicure.abacconnect.R;
import com.zicure.abacconnect.api.ApiConfig;
import com.zicure.abacconnect.net.MyHttpClient;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.ByteArrayEntity;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;


public class LoginActivity extends AppCompatActivity {
    private static final String TAG = "LoginActivity";
    private LoginTask StatusTask = null;
    private EditText txtUsername, txtPassword;
    private Button btnLogin;
    private TextView register;
    private View mProgress;
    private final String PRE_NAME = "PRE_NAME";
    private final String USERNAME = "USERNAME";
    private final String PASSWORD = "PASSWORD";

    private boolean doubleBackToExitPressedOnce = false;

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;


    // private View mloginfrom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        txtUsername = (EditText) findViewById(R.id.txtUserName);
        txtPassword = (EditText) findViewById(R.id.txtPassword);

        sharedPreferences = getSharedPreferences(PRE_NAME, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();

        txtUsername.setText(sharedPreferences.getString(USERNAME, ""));
        txtPassword.setText(sharedPreferences.getString(PASSWORD, ""));

        txtUsername.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s) {
                editor.putString(USERNAME, s.toString());
                editor.commit();
            }
        });

        txtPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                editor.putString(PASSWORD, s.toString());
                editor.commit();
            }
        });

        btnLogin = (Button) findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                temptLogin();
            }
        });
        register = (TextView) findViewById(R.id.txtRegister);
        register.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);

            }
        });
        mProgress = findViewById(R.id.login_progress);

    }

    protected void temptLogin() {
        if (StatusTask != null) {
            return;
        }
        txtUsername.setError(null);
        txtPassword.setError(null);

        String user_name = txtUsername.getText().toString();
        String user_pass = txtPassword.getText().toString();

        boolean cancel = false;
        View focusView = null;

        if (!TextUtils.isEmpty(user_pass) && !isPasswordValid(user_pass)) {
            txtPassword.setError("This password is too short");
            focusView = txtPassword;
            cancel = true;
        }
        if (TextUtils.isEmpty(user_name)) {
            txtUsername.setError("This field is required");
            focusView = txtUsername;
            cancel = true;
        } else if (!isEmailValid(user_name)) {
            txtUsername.setError("This username address is invalid");
            focusView = txtUsername;
            cancel = true;
        }
        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView.requestFocus();
        } else {
            // Show a progress spinner, and kick off a background task to
            // perform the user login attempt.
            showProgress(true);
            StatusTask = new LoginTask(user_name, user_pass);
            StatusTask.execute("login", user_name, user_pass);
        }

    }

    private boolean isPasswordValid(String password) {
        // TODO: Replace this with your own logic
        return password.length() > 4;
    }

    private boolean isEmailValid(String email) {
        // TODO: Replace this with your own logic
//		return email.contains("@");
        return true;
    }

    @SuppressLint("NewApi")
    public void showProgress(final boolean show) {
        // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
        // for very easy animations. If available, use these APIs to fade-in
        // the progress spinner.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            int shortAnimTime = getResources().getInteger(
                    android.R.integer.config_shortAnimTime);

            mProgress.setVisibility(show ? View.VISIBLE : View.GONE);
            mProgress.animate().setDuration(shortAnimTime)
                    .alpha(show ? 1 : 0)
                    .setListener(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationEnd(Animator animation) {
                            mProgress.setVisibility(show ? View.VISIBLE
                                    : View.GONE);
                        }
                    });
        } else {
            // The ViewPropertyAnimator APIs are not available, so simply show
            // and hide the relevant UI components.
            mProgress.setVisibility(show ? View.VISIBLE : View.GONE);
        }
    }

    public class LoginTask extends AsyncTask<String, Integer, String> {

        private String method = "GET";
        private String rawPostData = "";
        private String currentApi = "";

        private final String mEmail;
        private final String mPassword;

        LoginTask(String username, String password) {
            mEmail = username;
            mPassword = password;
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
            Log.d("ABAC_URL", url);
            MyHttpClient client = MyHttpClient.getInstance(LoginActivity.this);
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
                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(content));
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
            StatusTask = null;
            showProgress(false);
            Log.d("ABAC", result);
            try {
                JSONObject jsonResult = new JSONObject(result);
                if ("OK".equals(jsonResult.getJSONObject("result").getString("Success"))) {
                    String user_id = jsonResult.getJSONObject("result").getJSONObject("Data").getJSONObject("User").getString("id");
                    String user_name = jsonResult.getJSONObject("result").getJSONObject("Data").getJSONObject("User").getString("user_username");
                    String user_firstname = jsonResult.getJSONObject("result").getJSONObject("Data").getJSONObject("User").getString("user_firstname");
                    String user_lastname = jsonResult.getJSONObject("result").getJSONObject("Data").getJSONObject("User").getString("user_lastname");
                    //String user_rolename=jsonResult.getJSONObject("result").getJSONObject("Data").getJSONObject("User").getString("rold_name");

                    SharedPreferences pref = getApplicationContext().getSharedPreferences("ABACLogin", Context.MODE_PRIVATE);
                    SharedPreferences.Editor prefEdit = pref.edit();

                    prefEdit.putString("userId", user_id);
                    prefEdit.putString("username", user_name);
                    prefEdit.putString("firstName", user_firstname);
                    prefEdit.putString("lastName", user_lastname);
                    //prefEdit.putString("roleName", user_rolename);

                    prefEdit.commit();
                    Log.d("User login", jsonResult.toString());
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                } else {
                    Log.d("ABAC point", "Error");
                    Toast.makeText(LoginActivity.this, "Cannot login with this user", Toast.LENGTH_SHORT).show();
                }
            } catch (JSONException e) {
                e.printStackTrace();
                Toast.makeText(LoginActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
            }
            super.onPostExecute(result);
            Log.d("ABAC point", result);
        }

        protected void onCancelled() {
            StatusTask = null;
            showProgress(false);
        }

    }

    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce = false;
            }
        }, 2000);
    }
}

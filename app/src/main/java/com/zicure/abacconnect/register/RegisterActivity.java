package com.zicure.abacconnect.register;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import com.google.gson.Gson;
import com.zicure.abacconnect.R;
import com.zicure.abacconnect.api.ApiConfig;

import com.zicure.abacconnect.model.StudentProfile;
import com.zicure.abacconnect.model.UserClass;
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

public class RegisterActivity extends Activity {

    private EditText txtStudentId, txtFirstName, txtLastName, ettype, txtCitizenPassport, txtDateOfBirth, txtRegPassword, txtRegRePassword, txtEmail, txtUserName;
    private Button btnRegister, btnUploadImage;
    public RegisTask registask;
    public Spinner spinner;
    private String picturePath;
    private ImageView imgViewRegister;
    private static int RESULT_LOAD_IMG = 1;
    private Bitmap bitmap;
    private StudentProfile studentClass;
    public int status =1;
    private String citizent;
    private Integer userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        txtStudentId = (EditText) findViewById(R.id.tvProfileStudentId);
        txtDateOfBirth = (EditText) findViewById(R.id.txtDateOfBirth);

        txtFirstName = (EditText) findViewById(R.id.txtFirstName);
        txtFirstName.setVisibility(View.GONE);
        txtLastName = (EditText) findViewById(R.id.txtLastName);
        txtLastName.setVisibility(View.GONE);

        txtCitizenPassport = (EditText) findViewById(R.id.txtCitizenPassport);
        txtCitizenPassport.setVisibility(View.GONE);
        txtRegPassword = (EditText) findViewById(R.id.txtRegPassword);
        txtRegPassword.setVisibility(View.GONE);
        txtRegRePassword = (EditText) findViewById(R.id.txtRegRePassword);
        txtRegRePassword.setVisibility(View.GONE);
        txtEmail = (EditText) findViewById(R.id.txtEmail);
        txtEmail.setVisibility(View.GONE);
        txtUserName = (EditText) findViewById(R.id.txtUserName);
        txtUserName.setVisibility(View.GONE);
        imgViewRegister = (ImageView) findViewById(R.id.imgViewRegister);

        btnRegister = (Button) findViewById(R.id.btnRegister);
        btnRegister.setText("Synce Data");
        btnUploadImage = (Button) findViewById(R.id.btnUploadImage);

        btnRegister.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    createRegis();
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });

        btnUploadImage.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                intent.setType("image/*");
                startActivityForResult(intent, RESULT_LOAD_IMG);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RESULT_LOAD_IMG && resultCode == RESULT_OK && data != null) {
            Uri selectImage = data.getData();
            String[] filePathCol = {MediaStore.Images.Media.DATA};

            // Move cursor follow select image.
            Cursor cursor = getContentResolver().query(selectImage, filePathCol, null, null, null);
            cursor.moveToFirst();

            // Get path from gallery.
            int colIndex = cursor.getColumnIndex(filePathCol[0]);
            picturePath = cursor.getString(colIndex);
            cursor.close();

            // Set ImageView.
            imgViewRegister.setImageBitmap(BitmapFactory.decodeFile(picturePath));
            bitmap = BitmapFactory.decodeFile(picturePath);
        }
    }

    protected void createRegis() throws JSONException {
        if(status==1){
            RegisterClass jsonfrom = new RegisterClass();
            jsonfrom.register = new UserClass();
            jsonfrom.register.Student_id = txtStudentId.getText().toString();
            jsonfrom.register.birth_date = txtDateOfBirth.getText().toString();
            Gson gRegsiter = new Gson();
            String sRegister = gRegsiter.toJson(jsonfrom);
            registask = new RegisTask();
            registask.setRegister(sRegister);
            registask.execute("registersyn");
            Log.d("ABAC", sRegister);
        }else{
            RegisterClass jsonfrom = new RegisterClass();
            jsonfrom.register = new UserClass();
            jsonfrom.register.Student_id = txtStudentId.getText().toString();
            jsonfrom.register.user_username = txtUserName.getText().toString();
            jsonfrom.register.user_firstname = txtFirstName.getText().toString();
            jsonfrom.register.user_lastname = txtLastName.getText().toString();
            jsonfrom.register.citizen_id=citizent;
            jsonfrom.register.id=userID;
            jsonfrom.register.passport = txtCitizenPassport.getText().toString();
            jsonfrom.register.user_email = txtEmail.getText().toString();
            jsonfrom.register.birth_date = txtDateOfBirth.getText().toString();
            jsonfrom.register.password = txtRegPassword.getText().toString();
            Gson gRegsiter = new Gson();
            String sRegister = gRegsiter.toJson(jsonfrom);
            Log.d("ABAC REGISTER", sRegister);
            registask = new RegisTask();
            registask.setRegister(sRegister);
            registask.execute("register");
        }

    }

    public class RegisTask extends AsyncTask<String, Integer, String> {

        private String method = "GET";
        private String rawPostData = "";
        private String currentApi = "";

        public void setRegister(String sRegister) {
            this.rawPostData = sRegister;
            this.method = "POST";
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
            MyHttpClient client = MyHttpClient.getInstance(RegisterActivity.this);
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
                // TODO Auto-generated catch block
                responseString = e.getMessage();
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
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
                if (currentApi.equals("registersyn")) {
                    JSONObject jresult = new JSONObject(result);
                    Log.d("ABAC result", result);
                    if ("OK".equals(jresult.getJSONObject("result").getString("Success"))) {
                        JSONObject studentfile = jresult.getJSONObject("result").getJSONObject("Data").getJSONObject("StudentProfile");
                        String fstudentfile = studentfile.toString();
                        Log.d("ABAC Student",fstudentfile);
                        Gson gson = new Gson();
                        StudentProfile studentClass = gson.fromJson(fstudentfile,StudentProfile.class);
                        setStudentProfile(studentClass);

//
                    } else {
                        new AlertDialog.Builder(RegisterActivity.this)
                                .setTitle("Register")
                                .setMessage("Can not to register")
                                .setPositiveButton("Cancel", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {

                                        dialog.cancel();

                                    }
                                })
                                .setNegativeButton("Back to login", new DialogInterface.OnClickListener() {

                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                                        startActivity(intent);
                                        dialog.cancel();
                                    }
                                })
                                .setIcon(android.R.drawable.ic_dialog_alert)
                                .show();
                    }

                }else if(currentApi.equals("register")){
                    JSONObject jresult = new JSONObject(result);
                    Log.d("Register",result.toString());
                    if ("OK".equals(jresult.getJSONObject("result").getString("Success"))) {
                        new AlertDialog.Builder(RegisterActivity.this)
                                .setTitle("Register")
                                .setMessage("Success to register")
                                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                                        startActivity(intent);
                                        dialog.cancel();

                                    }
                                })

                                .setIcon(android.R.drawable.ic_dialog_alert)
                                .show();
                    }else{
                        new AlertDialog.Builder(RegisterActivity.this)
                                .setTitle("Register")
                                .setMessage("Can not to register")
                                .setNegativeButton("Back to login", new DialogInterface.OnClickListener() {

                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                                        startActivity(intent);
                                        dialog.cancel();
                                    }
                                })
                                .setIcon(android.R.drawable.ic_dialog_alert)
                                .show();
                    }
                }
            } catch (JSONException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();


            }
            super.onPostExecute(result);
        }

    }

    private void setStudentProfile(StudentProfile studentClass) {
        this.studentClass=studentClass;
        txtFirstName.setVisibility(View.VISIBLE);
        txtFirstName.setEnabled(false);
        txtLastName.setVisibility(View.VISIBLE);
        txtLastName.setEnabled(false);
        txtCitizenPassport.setVisibility(View.VISIBLE);
        txtRegPassword.setVisibility(View.VISIBLE);
        txtRegRePassword.setVisibility(View.VISIBLE);
        txtEmail.setVisibility(View.VISIBLE);
        txtUserName.setVisibility(View.VISIBLE);
        txtUserName.setEnabled(false);
        txtUserName.setText(studentClass.student_id);
        citizent = studentClass.citizen_passport_no;
        userID = studentClass.id;
        txtFirstName.setText(studentClass.first_name);
        txtLastName.setText(studentClass.last_name);
        txtCitizenPassport.setText(studentClass.citizen_passport_no);
        btnRegister.setText("Register");
        status=2;


    }
}

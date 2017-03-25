package com.example.ani.digitalcoast;

import android.app.AlertDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by Ani on 12-03-2017.
 */

public class ChangePassword extends AppCompatActivity{

    EditText etopass,etpass,etcpass;
    String opass,pass,cpass,mail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Change Password");
        setContentView(R.layout.changepasswordlayout);
        Bundle bundle = getIntent().getExtras();
        mail = bundle.getString("key");
        etopass = (EditText) findViewById(R.id.editText7);
        etpass = (EditText) findViewById(R.id.editText8);
        etcpass = (EditText) findViewById(R.id.editText9);
        chView();
    }
    public void chanPass(View view) {
        opass = etopass.getText().toString();
        pass = etpass.getText().toString();
        cpass = etcpass.getText().toString();
        if(checkValidation()) {
            String method = "changePassword";
            BackgroundTask2 backgroundTask2 = new ChangePassword.BackgroundTask2(this);
            backgroundTask2.execute(method, mail, opass, pass);
        }
        else
        {
            Toast.makeText(ChangePassword.this, "Please enter valid details", Toast.LENGTH_LONG).show();
        }
    }
    private void chView() {

        etopass.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {
                validationRegistration.hasText(etopass);
            }
            public void beforeTextChanged(CharSequence s, int start, int count, int after){}
            public void onTextChanged(CharSequence s, int start, int before, int count){}
        });

        etpass.addTextChangedListener(new TextWatcher() {
            // after every change has been made to this editText, we would like to check validity
            public void afterTextChanged(Editable s) {
                validationRegistration.isPassword(etpass, true);
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            public void onTextChanged(CharSequence s, int start, int before, int count) {}
        });

        etcpass.addTextChangedListener(new TextWatcher() {
            // after every change has been made to this editText, we would like to check validity
            public void afterTextChanged(Editable s) {
                validationRegistration.isCPassword(etcpass, etpass, true);
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            public void onTextChanged(CharSequence s, int start, int before, int count) {}
        });
    }
    class BackgroundTask2 extends AsyncTask<String,Void, String>
    {
        Context ctx;
        AlertDialog alertDialog;
        BackgroundTask2(Context ctx)
        {
            this.ctx=ctx;
        }
        @Override
        protected void onPreExecute() {
            alertDialog = new AlertDialog.Builder(ctx).create();
            alertDialog.setTitle("Error Information");
        }

        @Override
        protected String doInBackground(String... voids) {
            //String chanpass_url = "http://192.168.43.24/php/pretest/chanpass.php";
            //String chanpass_url = "http://192.168.2.4/php/pretest/chanpass.php";
            String chanpass_url = "http://digitalcoast.000webhostapp.com/chanpass.php";
            String method= voids[0];
            if(method.equals("changePassword")) {
                String mail = voids[1];
                String opass = voids[2];
                String pass = voids[3];
                try {
                    URL url = new URL(chanpass_url);
                    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                    httpURLConnection.setRequestMethod("POST");
                    httpURLConnection.setDoInput(true);
                    httpURLConnection.setDoInput(true);
                    OutputStream OS = httpURLConnection.getOutputStream();
                    BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(OS, "UTF-8"));
                    String data = URLEncoder.encode("mail", "UTF-8") + "=" + URLEncoder.encode(mail, "UTF-8") + "&" +
                            URLEncoder.encode("opass", "UTF-8") + "=" + URLEncoder.encode(opass, "UTF-8") + "&" +
                            URLEncoder.encode("pass", "UTF-8") + "=" + URLEncoder.encode(pass, "UTF-8");
                    bufferedWriter.write(data);
                    bufferedWriter.flush();
                    bufferedWriter.close();
                    OS.close();
                    InputStream IS = httpURLConnection.getInputStream();
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(IS, "iso-8859-1"));
                    String response = "";
                    String line = "";
                    while ((line = bufferedReader.readLine()) != null) {
                        response += line;
                    }
                    bufferedReader.close();
                    IS.close();
                    //httpURLConnection.disconnect();
                    return response;
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            return null;
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(String result) {
                    if(result.equals("Password Changed Successfully")){
                    Toast.makeText(ctx, result, Toast.LENGTH_LONG).show();
                    finish();
                    }
                    else
                    {
                        alertDialog.setMessage(result);
                        alertDialog.show();
                    }
        }
}
    private boolean checkValidation() {
        boolean ret = true;

        if(!validationRegistration.hasText(etopass)) ret=false;
        if(!validationRegistration.isPassword(etpass,true)) ret=false;
        if(!validationRegistration.isCPassword(etcpass, etpass, true)) ret = false;

        return ret;
    }
}

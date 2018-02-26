package com.example.ani.digitalcoast;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
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
 * Created by Ani on 05-03-2017.
 */

public class LoginActivity extends AppCompatActivity {

    EditText etName, etPass;
    String loginName, loginPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("LOGIN");
        setContentView(R.layout.loginactivity);
        /*Button button1 = (Button) findViewById(R.id.button1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(LoginActivity.this,BuildingData.class);
                startActivity(intent1);
            }
        });*/
        etName = (EditText) findViewById(R.id.editText1);
        etPass = (EditText) findViewById(R.id.editText2);
        TextView tv1 = (TextView) findViewById(R.id.textView1);
        tv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2 = new Intent(LoginActivity.this, Register.class);
                startActivity(intent2);
            }
        });
        final TextView tv2 = (TextView) findViewById(R.id.editText1);
        final TextView tv3 = (TextView) findViewById(R.id.editText2);
        Button button2 = (Button) findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tv2.setText("");
                tv3.setText("");
            }
        });
    }
        public void userLogin(View view) {
            loginName = etName.getText().toString();
            loginPass = etPass.getText().toString();
            final TextView tv2 = (TextView) findViewById(R.id.editText1);
            final TextView tv3 = (TextView) findViewById(R.id.editText2);
            tv2.setText("");
            tv3.setText("");
            String method = "login";
            BackgroundTask1 backgroundTask1 = new BackgroundTask1(this);
            backgroundTask1.execute(method, loginName, loginPass);
        }
    class BackgroundTask1 extends AsyncTask<String,Void, String>

    {
        AlertDialog alertDialog;
        String val;
        Context ctx;
        BackgroundTask1(Context ctx)
        {
            this.ctx=ctx;
        }
        @Override
        protected void onPreExecute() {
        alertDialog = new AlertDialog.Builder(ctx).create();
        alertDialog.setTitle("Login Information");
    }

        @Override
        protected String doInBackground(String... voids) {
        //String login_url = "http://192.168.43.24/php/pretest/login.php";
            //String login_url = "http://192.168.2.4/php/pretest/login.php";
            //git testing
            String login_url = "http://digitalcoast.000webhostapp.com/login.php";
        String method= voids[0];
                if(method.equals("login")) {
            String loginName = val = voids[1];
            String loginPass = voids[2];
            try {
                URL url = new URL(login_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoInput(true);
                httpURLConnection.setDoInput(true);
                OutputStream OS = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(OS, "UTF-8"));
                String data = URLEncoder.encode("loginName", "UTF-8") + "=" + URLEncoder.encode(loginName, "UTF-8") + "&" +
                        URLEncoder.encode("loginPass", "UTF-8") + "=" + URLEncoder.encode(loginPass, "UTF-8");
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
                httpURLConnection.disconnect();
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
            if(result.equals("Login Failed"))
            {
               alertDialog.setMessage("Check your MailID/Password");
               alertDialog.show();
            }
            else {
                Toast.makeText(ctx, result, Toast.LENGTH_LONG).show();
                Intent i = new Intent(ctx, BuildingData.class);
                i.putExtra("key", val);
                ctx.startActivity(i);
            }
    }


    }
}



package com.example.ani.digitalcoast;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
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
 * Created by Ani on 11-03-2017.
 */

public class BackgroundTask extends AsyncTask<String,Void, String>{
    Context ctx;
    BackgroundTask(Context ctx)
    {
        this.ctx=ctx;
    }
    @Override
    protected void onPreExecute() {
    }

    @Override
    protected String doInBackground(String... voids) {
        //String reg_url = "http://192.168.43.24/php/pretest/register.php";
        //String sub_url = "http://192.168.43.24/php/pretest/submit.php";
        //String reg_url = "http://192.168.2.4/php/pretest/register.php";
        //String sub_url = "http://192.168.2.4/php/pretest/submit.php";
        String reg_url = "http://digitalcoast.000webhostapp.com/register.php";
        String sub_url = "http://digitalcoast.000webhostapp.com/submit.php";
        String method= voids[0];
        if(method.equals("register"))
        {
            String name = voids[1];
            String username = voids[2];
            String password = voids[3];
            String aadhaar = voids[4];
            String number = voids[5];
            String dob = voids[6];
            try {
                URL url = new URL(reg_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                OutputStream OS = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(OS,"UTF-8"));
                String data = URLEncoder.encode("name","UTF-8") +"="+URLEncoder.encode(name,"UTF-8")+"&"+
                        URLEncoder.encode("mailID","UTF-8") +"="+URLEncoder.encode(username,"UTF-8")+"&"+
                        URLEncoder.encode("pass","UTF-8") +"="+URLEncoder.encode(password,"UTF-8")+"&"+
                        URLEncoder.encode("aadhaar","UTF-8") +"="+URLEncoder.encode(aadhaar,"UTF-8")+"&"+
                        URLEncoder.encode("number","UTF-8") +"="+URLEncoder.encode(number,"UTF-8")+"&"+
                        URLEncoder.encode("dob","UTF-8") +"="+URLEncoder.encode(dob,"UTF-8");
                bufferedWriter.write(data);
                bufferedWriter.flush();
                bufferedWriter.close();
                OS.close();
                InputStream IS = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(IS, "iso-8859-1"));
                String response = "";
                String line = "";
                while ((line = bufferedReader.readLine())!= null) {
                    response += line;
                }
                bufferedReader.close();
                IS.close();
                return response;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
         else if(method.equals("submit")){
            String name = voids[1];
            String addr = voids[2];
            String lat = voids[3];
            String lon = voids[4];
            String cap = voids[5];
            String dis = voids[6];
            String mail = voids[7];
            try {
                URL url = new URL(sub_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                OutputStream OS = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(OS,"UTF-8"));
                String data = URLEncoder.encode("name","UTF-8") +"="+URLEncoder.encode(name,"UTF-8")+"&"+
                        URLEncoder.encode("addr","UTF-8") +"="+URLEncoder.encode(addr,"UTF-8")+"&"+
                        URLEncoder.encode("lat","UTF-8") +"="+URLEncoder.encode(lat,"UTF-8")+"&"+
                        URLEncoder.encode("lon","UTF-8") +"="+URLEncoder.encode(lon,"UTF-8")+"&"+
                        URLEncoder.encode("cap","UTF-8") +"="+URLEncoder.encode(cap,"UTF-8")+"&"+
                        URLEncoder.encode("dis","UTF-8") +"="+URLEncoder.encode(dis,"UTF-8")+"&"+
                        URLEncoder.encode("mail","UTF-8") +"="+URLEncoder.encode(mail,"UTF-8");
                bufferedWriter.write(data);
                bufferedWriter.flush();
                bufferedWriter.close();
                OS.close();
                InputStream IS = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(IS, "iso-8859-1"));
                String response = "";
                String line = "";
                while ((line = bufferedReader.readLine())!= null) {
                    response += line;
                }
                bufferedReader.close();
                IS.close();
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
        if (result.equals("Successfully Registered")) {
            Toast.makeText(ctx, result, Toast.LENGTH_LONG).show();
        }
        else if (result.contains("Successfully Submitted")) {
            Toast.makeText(ctx, result, Toast.LENGTH_LONG).show();
        }
        else if(result.equals("Sorry!!!E-mail ID already registered!!!"))
        {
            Toast.makeText(ctx, result, Toast.LENGTH_LONG).show();
        }
        else {
            Toast.makeText(ctx, result, Toast.LENGTH_LONG).show();
            Intent i = new Intent(ctx, BuildingData.class);
            ctx.startActivity(i);
        }
    }


}

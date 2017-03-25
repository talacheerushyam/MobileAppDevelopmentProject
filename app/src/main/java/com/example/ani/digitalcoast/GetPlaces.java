package com.example.ani.digitalcoast;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

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
import java.util.Locale;

/**
 * Created by Ani on 13-03-2017.
 */

public class GetPlaces extends AppCompatActivity {

    Double lon,lat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Nearby Shelters");
        setContentView(R.layout.getplaceslayout);
        Bundle b = getIntent().getExtras();
        lat=b.getDouble("lat");
        lon=b.getDouble("lon");
        String l1=String.valueOf(lat);
        String l2=String.valueOf(lon);
        BackgroundTask3 backgroundTask3 = new BackgroundTask3(this);
        backgroundTask3.execute(l1, l2);

    }
    class BackgroundTask3 extends AsyncTask<String,Void, String>

    {
        AlertDialog alertDialog;
        Context ctx;
        BackgroundTask3(Context ctx)
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
            //String getplaces_url = "http://192.168.43.24/php/pretest/buildingdata.php";
            //String getplaces_url = "http://192.168.2.4/php/pretest/buildingdata.php";
            String getplaces_url = "http://digitalcoast.000webhostapp.com/buildingdata.php";
                String lat = voids[0];
                String lon = voids[1];
                try {
                    URL url = new URL(getplaces_url);
                    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                    httpURLConnection.setRequestMethod("POST");
                    httpURLConnection.setDoInput(true);
                    httpURLConnection.setDoInput(true);
                    OutputStream OS = httpURLConnection.getOutputStream();
                    BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(OS, "UTF-8"));
                    String data = URLEncoder.encode("lat", "UTF-8") + "=" + URLEncoder.encode(lat, "UTF-8") + "&" +
                            URLEncoder.encode("lon", "UTF-8") + "=" + URLEncoder.encode(lon, "UTF-8");
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

            return null;
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(String result) {
            if(result.equals("GPS Error"))
            {
                alertDialog.setMessage("Check your GPS Setting! Turn on Location");
                alertDialog.show();
            }
            else {
                    String[] temp;
                    String[] msg;
                    int j=0;
                    String delimeter = "@";
                    temp = result.split(delimeter);
                    for(int i=0;i<temp.length;i++)
                       {

                        String delimiter1 = "!";
                        msg = temp[i].split(delimiter1);

                        if(j==0)
                          {
                              TextView tv1 = (TextView) findViewById(R.id.textView1);
                              tv1.setText("Click here to go to "+msg[0]+" which is at the distance of "+msg[3]+" kilometers from shore"+" having capacity "+msg[4]);
                              final String lat = msg[1];
                              final String lon = msg[2];
                              tv1.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    String uri = String.format(Locale.ENGLISH, "http://maps.google.com/maps?daddr="+lat+","+lon);
                                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
                                    intent.setClassName("com.google.android.apps.maps", "com.google.android.maps.MapsActivity");
                                    startActivity(intent);
                                  }
                              });
                          }
                        if(j==1)
                            {
                                TextView tv2 = (TextView) findViewById(R.id.textView2);
                                tv2.setText("Click here to go to "+msg[0]+" which is at the distance of "+msg[3]+" kilometers from shore"+" having capacity "+msg[4]);
                                final String lat = msg[1];
                                final String lon = msg[2];
                                tv2.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        String uri = String.format(Locale.ENGLISH, "http://maps.google.com/maps?daddr="+lat+","+lon);
                                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
                                        intent.setClassName("com.google.android.apps.maps", "com.google.android.maps.MapsActivity");
                                        startActivity(intent);
                                    }
                                });
                            }
                        if(j==2) {
                            TextView tv3 = (TextView) findViewById(R.id.textView3);
                            tv3.setText("Click here to go to "+msg[0]+" which is at the distance of "+msg[3]+" kilometers from shore"+" having capacity "+msg[4]);
                            final String lat = msg[1];
                            final String lon = msg[2];
                            tv3.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    String uri = String.format(Locale.ENGLISH, "http://maps.google.com/maps?daddr="+lat+","+lon);
                                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
                                    intent.setClassName("com.google.android.apps.maps", "com.google.android.maps.MapsActivity");
                                    startActivity(intent);
                                }
                            });
                           }
                          if(j==3)
                            {
                                TextView tv4 = (TextView) findViewById(R.id.textView4);
                                tv4.setText("Click here to go to "+msg[0]+" which is at the distance of "+msg[3]+" kilometers from shore"+" having capacity "+msg[4]);
                                final String lat = msg[1];
                                final String lon = msg[2];
                                tv4.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        String uri = String.format(Locale.ENGLISH, "http://maps.google.com/maps?daddr="+lat+","+lon);
                                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
                                        intent.setClassName("com.google.android.apps.maps", "com.google.android.maps.MapsActivity");
                                        startActivity(intent);
                                    }
                                });
                            }
                          if(j==4)
                            {
                                TextView tv5 = (TextView) findViewById(R.id.textView5);
                                tv5.setText("Click here to go to "+msg[0]+" which is at the distance of "+msg[3]+" kilometers from shore"+" having capacity "+msg[4]);
                                final String lat = msg[1];
                                final String lon = msg[2];
                                tv5.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        String uri = String.format(Locale.ENGLISH, "http://maps.google.com/maps?daddr="+lat+","+lon);
                                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
                                        intent.setClassName("com.google.android.apps.maps", "com.google.android.maps.MapsActivity");
                                        startActivity(intent);
                                    }
                                });
                            }
                            j++;
                }
                //TextView tv0 = (TextView) findViewById(R.id.textView1);
                //tv0.setText(temp[2]);
            }
        }


    }

}

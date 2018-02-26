package com.example.ani.digitalcoast;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

/**
 * Created by Ani on 04-03-2017.
 */

public class SecondActivity extends AppCompatActivity {

    Double lon,lat;
    gpsTracker currLoc=null;
    String msg;
    String dum_msg="Here, there is a natural disaster occuring and I am in a serious need of help. My location is at following url : \n";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setTitle("Emergency");
        setContentView(R.layout.second_layout);
        currLoc=new gpsTracker(SecondActivity.this);
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, 1);
        if(currLoc.canGetLocation()) {
            lat=currLoc.getLatitude();
            lon=currLoc.getLongitude();
        }
        else
        {
            currLoc.showSettingsAlert();
        }
        msg=dum_msg.concat("http://maps.google.com/?ll=" + lat + "," + lon);
        Button button1 = (Button) findViewById(R.id.button1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent make_call = new Intent(Intent.ACTION_CALL);
                make_call.setData(Uri.parse("tel:9868891801"));
                if (ContextCompat.checkSelfPermission(SecondActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED)
                {
                    ActivityCompat.requestPermissions(SecondActivity.this, new String[]{Manifest.permission.CALL_PHONE},0);
                }
                else {
                    startActivity(make_call);
                }
            }
        });
        Button button2 = (Button) findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent make_call = new Intent(Intent.ACTION_CALL);
                make_call.setData(Uri.parse("tel:9868101885"));
                if (ContextCompat.checkSelfPermission(SecondActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED)
                {
                    ActivityCompat.requestPermissions(SecondActivity.this, new String[]{Manifest.permission.CALL_PHONE},0);
                }
                else {
                    startActivity(make_call);
                }
            }
        });
        Button button3 = (Button) findViewById(R.id.button3);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_EMAIL, "9868891801");
                intent.putExtra(Intent.EXTRA_TEXT, msg);
                startActivity(intent);
            }
        });
        Button button4 = (Button) findViewById(R.id.button4);
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_EMAIL, "9868101885");
                intent.putExtra(Intent.EXTRA_TEXT, msg);
                startActivity(intent);
            }
        });
        Button button5 = (Button) findViewById(R.id.button5);
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent make_call = new Intent(Intent.ACTION_CALL);
                make_call.setData(Uri.parse("tel:9711077372"));
                if (ContextCompat.checkSelfPermission(SecondActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED)
                {
                    ActivityCompat.requestPermissions(SecondActivity.this, new String[]{Manifest.permission.CALL_PHONE},0);
                }
                else {
                    startActivity(make_call);
                }
            }
        });
        Button button6 = (Button) findViewById(R.id.button6);
        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_EMAIL, "9711077372");
                intent.putExtra(Intent.EXTRA_TEXT, msg);
                startActivity(intent);
            }
        });
        Button button7 = (Button) findViewById(R.id.button7);
        button7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent emailIntent = new Intent(Intent.ACTION_SEND);
                emailIntent.setType("plain/text");
                emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[] {"controlroom@ndma.gov.in"});
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Regarding a natural disaster");
                emailIntent.putExtra(Intent.EXTRA_TEXT, msg);
                startActivity(emailIntent);
            }
        });
        Button button8 = (Button) findViewById(R.id.button8);
        button8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent emailIntent = new Intent(Intent.ACTION_SEND);
                emailIntent.setType("plain/text");
                emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[] {"ndmacontrolroom@gmail.com"});
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Regarding a natural disaster");
                emailIntent.putExtra(Intent.EXTRA_TEXT, msg);
                startActivity(emailIntent);
            }
        });
        Button button9 = (Button) findViewById(R.id.button9);
        button9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent emailIntent = new Intent(Intent.ACTION_SEND);
                emailIntent.setType("plain/text");
                emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[] {"hq.ndrf@nic.in"});
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Regarding a natural disaster");
                emailIntent.putExtra(Intent.EXTRA_TEXT, msg);
                startActivity(emailIntent);
            }
        });
        Button button10 = (Button) findViewById(R.id.button10);
        button10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri = Uri.parse("http://ndma.gov.in/");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });
        Button button11 = (Button) findViewById(R.id.button11);
        button11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri = Uri.parse("http://ndrf.gov.in/");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });
        Button button12 = (Button) findViewById(R.id.button12);
        button12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri = Uri.parse("http://nidm.gov.in/");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });
        ImageButton button13 = (ImageButton) findViewById(R.id.imageButton6);
        button13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Uri uri = Uri.parse("fb://facewebmodal/f?href=https://www.facebook.com/NDMA.in");
                //startActivity(new Intent(Intent.ACTION_VIEW, uri));
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("fb://page/230814463607791"));
                intent.setPackage("com.facebook.katana");
                startActivity(intent);
            }
        });
    }
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 1: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                } else {
                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
                return;
            }
            // other 'case' lines to check for other
            // permissions this app might request
        }
    }
}

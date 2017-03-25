package com.example.ani.digitalcoast;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by Ani on 05-03-2017.
 */

public class MainActivity extends AppCompatActivity {

    Double lon,lat;
    gpsTracker currLoc=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Home");
        setContentView(R.layout.activity_main);
        currLoc=new gpsTracker(MainActivity.this);
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, 1);
        if(currLoc.canGetLocation()) {
            lat=currLoc.getLatitude();
            lon=currLoc.getLongitude();
        }
        else
        {
            currLoc.showSettingsAlert();
        }
        //LocationManager lm = (LocationManager)getSystemService(MainActivity.LOCATION_SERVICE);
        /*if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 99);
            return;
        }*/
        /*Button button1 = (Button) findViewById(R.id.button1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String uri = String.format(Locale.ENGLISH, "http://maps.google.com/maps?daddr= 18.111962"+","+"83.409028");
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
                intent.setClassName("com.google.android.apps.maps", "com.google.android.maps.MapsActivity");
                startActivity(intent);
            }
        });*/
        Button button2 = (Button) findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(MainActivity.this, SecondActivity.class);
                startActivity(intent1);
            }
        });
        Button button3 = (Button) findViewById(R.id.button3);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent3 = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent3);
            }
        });
    }
    public void getPlaces(View view){
        //Toast.makeText(MainActivity.this, "I am at "+lat+" , "+lon, Toast.LENGTH_SHORT).show();
        Intent intent2=new Intent(MainActivity.this, GetPlaces.class);
        Bundle b =new Bundle();
        b.putDouble("lon",lon);
        b.putDouble("lat",lat);
        intent2.putExtras(b);
        startActivity(intent2);
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
    @Override
    protected void onDestroy() {
        super.onDestroy();
        currLoc.stopUsingGPS();
    }
}

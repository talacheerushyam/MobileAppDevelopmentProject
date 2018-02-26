package com.example.ani.digitalcoast;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.ProgressBar;

/**
 * Created by Ani on 18-03-2017.
 */

public class SplashActivity extends AppCompatActivity implements LoadingTask.LoadingTaskFinishedListener {
    Double lon,lat;
    gpsTracker currLoc=null;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Show the splash screen
        setContentView(R.layout.activity_splash);
        // Find the progress bar
        ProgressBar progressBar = (ProgressBar) findViewById(R.id.activity_splash_progress_bar);
        // Start your loading
        new LoadingTask(progressBar, this).execute("www.google.co.in"); // Pass in whatever you need a url is just an example we don't use it in this tutorial
        currLoc=new gpsTracker(SplashActivity.this);
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, 1);
        if(currLoc.canGetLocation()) {
            lat=currLoc.getLatitude();
            lon=currLoc.getLongitude();
        }
        else
        {
            currLoc.showSettingsAlert();
        }
    }

    // This is the callback for when your async task has finished
    @Override
    public void onTaskFinished() {
        completeSplash();
    }

    private void completeSplash(){
        startApp();
        finish(); // Don't forget to finish this Splash Activity so the user can't return to it!
    }

    private void startApp() {
        //Toast.makeText(SplashActivity.this, "I am at "+lat+" , "+lon, Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(SplashActivity.this, MainActivity.class);
        startActivity(intent);
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        currLoc.stopUsingGPS();
    }
}

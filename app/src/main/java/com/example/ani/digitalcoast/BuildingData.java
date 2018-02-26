package com.example.ani.digitalcoast;

import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

/**
 * Created by Ani on 05-03-2017.
 */

public class BuildingData extends AppCompatActivity {

    EditText et_name,et_addr,et_lat,et_lon,et_cap,et_dis;
    String name,addr,lat,lon,cap,dis,val;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Infrastructure Details");
        setContentView(R.layout.buildinglayout);
        Bundle bundle = getIntent().getExtras();
        val = bundle.getString("key");
        et_name = (EditText) findViewById(R.id.editText1);
        et_addr = (EditText) findViewById(R.id.editText2);
        et_lat = (EditText) findViewById(R.id.editText3);
        et_lon = (EditText) findViewById(R.id.editText4);
        et_cap = (EditText) findViewById(R.id.editText5);
        et_dis = (EditText) findViewById(R.id.editText6);
        Button button1 = (Button) findViewById(R.id.button1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        Button button2 = (Button) findViewById(R.id.button);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(BuildingData.this,ChangePassword.class);
                intent1.putExtra("key", val);
                startActivity(intent1);
            }
        });
        //builData();
    }

    private void builData() {
        // TextWatcher would let us check validation error on the fly
        et_name.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {
                validationRegistration.hasText(et_name);
            }
            public void beforeTextChanged(CharSequence s, int start, int count, int after){}
            public void onTextChanged(CharSequence s, int start, int before, int count){}
        });

        et_addr.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {
                validationRegistration.hasText(et_addr);
            }
            public void beforeTextChanged(CharSequence s, int start, int count, int after){}
            public void onTextChanged(CharSequence s, int start, int before, int count){}
        });

        et_lat.addTextChangedListener(new TextWatcher() {
            // after every change has been made to this editText, we would like to check validity
            public void afterTextChanged(Editable s) {
                validationRegistration.isLat(et_lat, true);
            }
            public void beforeTextChanged(CharSequence s, int start, int count, int after){}
            public void onTextChanged(CharSequence s, int start, int before, int count){}
        });

        et_lon.addTextChangedListener(new TextWatcher() {
            // after every change has been made to this editText, we would like to check validity
            public void afterTextChanged(Editable s) {
                validationRegistration.isLon(et_lon, true);
            }
            public void beforeTextChanged(CharSequence s, int start, int count, int after){}
            public void onTextChanged(CharSequence s, int start, int before, int count){}
        });

        et_cap.addTextChangedListener(new TextWatcher() {
            // after every change has been made to this editText, we would like to check validity
            public void afterTextChanged(Editable s) {
                validationRegistration.isCap(et_cap, true);
            }
            public void beforeTextChanged(CharSequence s, int start, int count, int after){}
            public void onTextChanged(CharSequence s, int start, int before, int count){}
        });

        et_dis.addTextChangedListener(new TextWatcher() {
            // after every change has been made to this editText, we would like to check validity
            public void afterTextChanged(Editable s) {
                validationRegistration.isDis(et_dis, true);
            }
            public void beforeTextChanged(CharSequence s, int start, int count, int after){}
            public void onTextChanged(CharSequence s, int start, int before, int count){}
        });


    }

        public void getLatit(View view) {
        Geocoder coder = new Geocoder(this, Locale.getDefault());
         List<Address> address;
            if(validationRegistration.hasText(et_addr)) {
                try {
                    address = coder.getFromLocationName(et_addr.getText().toString(), 5);
                    Address location = address.get(0);
                    location.getLatitude();
                    et_lat.setText(Double.toString(location.getLatitude()));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        public void getLongit(View view) {
            Geocoder coder = new Geocoder(this, Locale.getDefault());
            List<Address> address;
            if(validationRegistration.hasText(et_addr)) {
            try {
                address = coder.getFromLocationName(et_addr.getText().toString(), 5);
                Address location = address.get(0);
                location.getLongitude();
                et_lon.setText(Double.toString(location.getLongitude()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void dataSub(View view) {
            if(checkValidation()) {
                name = et_name.getText().toString();
                addr = et_addr.getText().toString();
                lat = et_lat.getText().toString();
                lon = et_lon.getText().toString();
                cap = et_cap.getText().toString();
                dis = et_dis.getText().toString();
                String method = "submit";
                BackgroundTask backgroundTask = new BackgroundTask(this);
                backgroundTask.execute(method, name, addr, lat, lon, cap, dis, val);
                final TextView tv1 = (TextView) findViewById(R.id.editText1);
                final TextView tv2 = (TextView) findViewById(R.id.editText2);
                final TextView tv3 = (TextView) findViewById(R.id.editText3);
                final TextView tv4 = (TextView) findViewById(R.id.editText4);
                final TextView tv5 = (TextView) findViewById(R.id.editText5);
                final TextView tv6 = (TextView) findViewById(R.id.editText6);
                tv1.setText("");
                tv2.setText("");
                tv3.setText("");
                tv4.setText("");
                tv5.setText("");
                tv6.setText("");
            }
            else {
                Toast.makeText(BuildingData.this, "Please enter valid details!!!", Toast.LENGTH_LONG).show();
            }

            //finish();
        }
    private boolean checkValidation() {
        boolean ret = true;

        if (!validationRegistration.hasText(et_name)) ret = false;
        if (!validationRegistration.hasText(et_addr)) ret = false;
        if (!validationRegistration.isLat(et_lat, true)) ret = false;
        if(!validationRegistration.isLon(et_lon,true)) ret = false;
        if(!validationRegistration.isCap(et_cap,true)) ret=false;
        if(!validationRegistration.isDis(et_dis, true)) ret = false;

        return ret;
    }

}

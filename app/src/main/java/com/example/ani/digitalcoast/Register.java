package com.example.ani.digitalcoast;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

/**
 * Created by Ani on 05-03-2017.
 */

public class Register extends AppCompatActivity {
    Button button2;
    int y_x,m_x,d_x;
    static final int V=0;
    EditText et_name,et_mailID,et_pass,et_cpass,et_aadh,et_num;
    String name,mailID,pass,aadh,num,dob;
    TextView tv_dob;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Registration Form");
        setContentView(R.layout.registerlayout);
        final Calendar cal = Calendar.getInstance();
        y_x=cal.get(Calendar.YEAR);
        m_x=cal.get(Calendar.MONTH);
        d_x=cal.get(Calendar.DAY_OF_MONTH);
        et_name = (EditText)findViewById(R.id.editText1);
        et_mailID = (EditText)findViewById(R.id.editText);
        et_pass = (EditText)findViewById(R.id.editText2);
        et_cpass = (EditText)findViewById(R.id.editText3);
        et_aadh = (EditText)findViewById(R.id.editText4);
        et_num = (EditText)findViewById(R.id.editText5);
        tv_dob = (TextView) findViewById(R.id.textView7);
        /*Button button1 = (Button) findViewById(R.id.button1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(Register.this,LoginActivity.class);
                startActivity(intent1);
            }
        });*/
        registerViews();
        showDialogOnButtonClick();
        TextView tv1 = (TextView) findViewById(R.id.textView9);
        tv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                //Intent intent2 = new Intent(Register.this, LoginActivity.class);
                //startActivity(intent2);
            }
        });

    }
    public void showDialogOnButtonClick() {
        button2 = (Button)findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog(V);

            }
        });
    }
    @Override
    protected Dialog onCreateDialog(int id){
        if(id == V)
            return new DatePickerDialog(this, dpickerListener, y_x, m_x, d_x);
        return null;
    }

    private DatePickerDialog.OnDateSetListener dpickerListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int y, int m, int d) {
            y_x=y;
            m_x=m+1;
            d_x=d;
            tv_dob.setText(new StringBuilder().append(y_x).append("-")
                    .append(m_x).append("-").append(d_x));
            /*TextView dateView = (TextView) findViewById(R.id.textView7);
            dateView.setText(new StringBuilder().append(d_x).append("/")
                    .append(m_x).append("/").append(y_x));
            Toast.makeText(Register.this, d_x+"/"+m_x+"/"+y_x,Toast.LENGTH_LONG).show();*/
        }
    };
    private void registerViews() {
        // TextWatcher would let us check validation error on the fly
        et_name.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {
                validationRegistration.hasText(et_name);
            }
            public void beforeTextChanged(CharSequence s, int start, int count, int after){}
            public void onTextChanged(CharSequence s, int start, int before, int count){}
        });

        tv_dob.addTextChangedListener(new TextWatcher() {
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            public void afterTextChanged(Editable editable) {
                validationRegistration.hasText1(tv_dob);
            }
        });

        et_mailID.addTextChangedListener(new TextWatcher() {
            // after every change has been made to this editText, we would like to check validity
            public void afterTextChanged(Editable s) {
                validationRegistration.isEmailAddress(et_mailID, true);
            }
            public void beforeTextChanged(CharSequence s, int start, int count, int after){}
            public void onTextChanged(CharSequence s, int start, int before, int count){}
        });

        et_pass.addTextChangedListener(new TextWatcher() {
            // after every change has been made to this editText, we would like to check validity
            public void afterTextChanged(Editable s) {
                validationRegistration.isPassword(et_pass, true);
            }
            public void beforeTextChanged(CharSequence s, int start, int count, int after){}
            public void onTextChanged(CharSequence s, int start, int before, int count){}
        });

        et_cpass.addTextChangedListener(new TextWatcher() {
            // after every change has been made to this editText, we would like to check validity
            public void afterTextChanged(Editable s) {
                validationRegistration.isCPassword(et_cpass, et_pass, true);
            }
            public void beforeTextChanged(CharSequence s, int start, int count, int after){}
            public void onTextChanged(CharSequence s, int start, int before, int count){}
        });

        et_aadh.addTextChangedListener(new TextWatcher() {
            // after every change has been made to this editText, we would like to check validity
            public void afterTextChanged(Editable s) {
                validationRegistration.isAadhaarNumber(et_aadh, false);
            }
            public void beforeTextChanged(CharSequence s, int start, int count, int after){}
            public void onTextChanged(CharSequence s, int start, int before, int count){}
        });

        et_num.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {
                validationRegistration.isPhoneNumber(et_num, false);
            }
            public void beforeTextChanged(CharSequence s, int start, int count, int after){}
            public void onTextChanged(CharSequence s, int start, int before, int count){}
        });

    }
    public void userReg(View view)
    {
        if ( checkValidation () ){
            name=et_name.getText().toString();
            mailID=et_mailID.getText().toString();
            pass=et_pass.getText().toString();
            aadh=et_aadh.getText().toString();
            num=et_num.getText().toString();
            dob=tv_dob.getText().toString();
            String method = "register";
            BackgroundTask backgroundTask = new BackgroundTask(this);
            backgroundTask.execute(method,name,mailID,pass,aadh,num,dob);
            finish();}
        else {
            Toast.makeText(Register.this, "Please enter valid details!!!", Toast.LENGTH_LONG).show();
        }
    }


    private boolean checkValidation() {
        boolean ret = true;

        if (!validationRegistration.hasText(et_name)) ret = false;
        if (!validationRegistration.isEmailAddress(et_mailID, true)) ret = false;
        if (!validationRegistration.isPhoneNumber(et_num, true)) ret = false;
        if(!validationRegistration.isAadhaarNumber(et_aadh,true)) ret = false;
        if(!validationRegistration.hasText1(tv_dob)) ret = false;
        if(!validationRegistration.isPassword(et_pass,true)) ret=false;
        if(!validationRegistration.isCPassword(et_cpass, et_pass, true)) ret = false;

        return ret;
    }
}

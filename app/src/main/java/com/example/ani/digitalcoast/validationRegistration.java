package com.example.ani.digitalcoast;

import android.widget.EditText;
import android.widget.TextView;

import java.util.regex.Pattern;

/**
 * Created by Ani on 18-03-2017.
 */

public class validationRegistration {
    // Regular Expression
    // you can change the expression based on your need
    private static final String EMAIL_REGEX = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    private static final String PHONE_REGEX = "\\d{10}";
    private static final String AADHAAR_REGEX="\\d{12}";
    private static final String PASSWORD_REGEX="^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$";
    private static final String DISTANCE_REGEX="[0-9]*";
    private static final String CAPACITY_REGEX="[0-9]*";
    private static final String LATITUDE_REGEX="^[-+]?([1-8]?\\d(\\.\\d+)?|90(\\.0+)?)";
    private static final String LONGITUDE_REGEX="\\s*[-+]?(180(\\.0+)?|((1[0-7]\\d)|([1-9]?\\d))(\\.\\d+)?)$";

    // Error Messages
    private static final String REQUIRED_MSG = "field required";
    private static final String EMAIL_MSG = "invalid email";
    private static final String DISTANCE_MSG = "Please enter nearest integer";
    private static final String CAPACITY_MSG = "Please enter nearest integer";
    private static final String LATITUDE_MSG = "Please enter valid latitude";
    private static final String LONGITUDE_MSG = "Please enter valid longitude";
    private static final String PHONE_MSG = "Please enter 10 digits";
    private static final String AADHAAR_MSG="Please enter 12 digits";
    private static final String PASSWORD_MSG="Password should have atleast 8 characters with 1 digit, 1 special character, 1 lower and 1 higher alphabet";

    public static boolean isCPassword(EditText editText, EditText editPass, boolean required)
    {
        String text = editText.getText().toString().trim();
        String pass = editPass.getText().toString().trim();
        editText.setError(null);
        if ( required && !hasText(editText) )
            return false;
        if(!pass.equals(text)) {
            editText.setError("Passwords doesn't match!!!");
            return false;
        }
        return  true;
    }

    // call this method when you need to check email validation
    public static boolean isEmailAddress(EditText editText, boolean required) {
        return isValid(editText, EMAIL_REGEX, EMAIL_MSG, required);
    }

    public static boolean isDis(EditText editText, boolean required) {
        return isValid(editText, DISTANCE_REGEX, DISTANCE_MSG, required);
    }

    public static boolean isCap(EditText editText, boolean required) {
        return isValid(editText, CAPACITY_REGEX, CAPACITY_MSG, required);
    }

    public static boolean isLat(EditText editText, boolean required) {
        return isValid(editText, LATITUDE_REGEX, LATITUDE_MSG, required);
    }

    public static boolean isLon(EditText editText, boolean required) {
        return isValid(editText, LONGITUDE_REGEX, LONGITUDE_MSG, required);
    }

    // call this method when you need to check phone number validation
    public static boolean isPhoneNumber(EditText editText, boolean required) {
        return isValid(editText, PHONE_REGEX, PHONE_MSG, required);
    }

    public static boolean isAadhaarNumber(EditText editText, boolean required) {
        return isValid(editText, AADHAAR_REGEX, AADHAAR_MSG, required);
    }

    public static boolean isPassword(EditText editText, boolean required) {
        return isValid(editText, PASSWORD_REGEX, PASSWORD_MSG, required);
    }


    // return true if the input field is valid, based on the parameter passed
    public static boolean isValid(EditText editText, String regex, String errMsg, boolean required) {

        String text = editText.getText().toString().trim();
        // clearing the error, if it was previously set by some other values
        editText.setError(null);

        // text required and editText is blank, so return false
        if ( required && !hasText(editText) ) return false;

        // pattern doesn't match so returning false
        if (required && !Pattern.matches(regex, text)) {
            editText.setError(errMsg);
            return false;
        };

        return true;
    }

    // check the input field has any text or not
    // return true if it contains text otherwise false
    public static boolean hasText(EditText editText) {

        String text = editText.getText().toString().trim();
        editText.setError(null);

        // length 0 means there is no text
        if (text.length() == 0) {
            editText.setError(REQUIRED_MSG);
            return false;
        }

        return true;
    }
    public static boolean hasText1(TextView textView) {

        String text = textView.getText().toString().trim();
        textView.setError(null);

        // length 0 means there is no text
        if (text.length() == 0) {
            textView.setError(REQUIRED_MSG);
            return false;
        }

        return true;
    }
}

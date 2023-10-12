package com.example.copypasteproject.utils;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import java.util.regex.Pattern;

public class AppConstant {
    public static final Pattern MOBILE_PATTERN = Pattern.compile("^(\\+91[\\-\\s]?)?[0]?(91)?[6789]\\d{9}$");
    public static final Pattern INDIANPINCODE = Pattern.compile("^[1-9][0-9]{5}$");
    public static final Pattern NAME_PATTERN = Pattern.compile("^[\\p{L} ]+$");

    public static void showtoast(String message, Context context) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT);
    }

    public static void hideKeyboard(Activity activity) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);

        View view = activity.getCurrentFocus();

        if (view == null) {
            view = new View(activity);
        }
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}

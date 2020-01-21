package com.redsponge.coolapp.projects;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.coolapp.R;
import com.redsponge.coolapp.util.Constants;

public class CookieClickerActivity extends Activity {

    private TextView tvCounter;
    private int cookieCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cookie_clicker);


        SharedPreferences sharedPref = getSharedPreferences(Constants.SharedPreferencePaths.COOKIE_CLICKER_COUNT, MODE_PRIVATE);
        cookieCount = sharedPref.getInt(Constants.SharedPreferencePaths.COOKIE_CLICKER_COUNT, 0);
        tvCounter = findViewById(R.id.tvCounter);
        cookieCount = 0;
        updateCookieDisplay();
    }


    public void addCookie(View view) {
        cookieCount++;
        updateCookieDisplay();
    }

    private void updateCookieDisplay() {
        tvCounter.setText(getString(R.string.cookie_clicker_counter_display, cookieCount));
    }

    public void saveCookies(View view) {
        SharedPreferences sharedPref = getSharedPreferences(Constants.SharedPreferencePaths.COOKIE_CLICKER_COUNT, MODE_PRIVATE);
        sharedPref.edit().putInt(Constants.SharedPreferencePaths.COOKIE_CLICKER_COUNT, cookieCount).apply();
    }
}
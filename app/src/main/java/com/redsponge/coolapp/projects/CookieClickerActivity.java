package com.redsponge.coolapp.projects;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.coolapp.R;
import com.redsponge.coolapp.util.alert.AlertUtils;

public class CookieClickerActivity extends Activity {

    private static final String TAG = "CookieClickerActivity";

    private TextView tvCounter;
    private int cookieCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cookie_clicker);

        tvCounter = findViewById(R.id.tvCounter);

        /*SharedPreferences sharedPref = getSharedPreferences(getString(R.string.shared_preferences_name), MODE_PRIVATE);
        int storedCookieCount = sharedPref.getInt(getString(R.string.shared_preferences_cookie_clicker_cookie_count), 0);
        updateCookieDisplay();
        if(storedCookieCount != 0) {
            AlertUtils.showConfirmPrompt(this, "Found Saved Cookies!",
                    "After searching throuroughly in the cabinet we found " + storedCookieCount + " cookies! Load them? (no means losing them forever!)",
                    (dialog, which) ->{
                        cookieCount = storedCookieCount;
                        updateCookieDisplay();
                    },
                    (dialog, which) -> {
                        sharedPref.edit().putInt(getString(R.string.shared_preferences_cookie_clicker_cookie_count), 0).apply();
                    });
        }*/
    }


    public void addCookie(View view) {
        cookieCount++;
        updateCookieDisplay();
    }

    private void updateCookieDisplay() {
        tvCounter.setText(getString(R.string.cookie_clicker_counter_display, cookieCount));
    }

    public void saveCookies(View view) {
        SharedPreferences sharedPref = getSharedPreferences(getString(R.string.shared_preferences_name), Context.MODE_PRIVATE);
        sharedPref.edit().putInt(getString(R.string.shared_preferences_cookie_clicker_cookie_count), cookieCount).apply();
    }
}

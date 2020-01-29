package com.redsponge.coolapp.projects;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Interpolator;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.redsponge.coolapp.R;
import com.redsponge.coolapp.util.alert.AlertUtils;
import com.redsponge.coolapp.util.toast.SingleToast;

public class CookieClickerActivity extends Activity {

    private static final String TAG = "CookieClickerActivity";

    private TextView tvCounter;
    private int cookieCount;

    private ImageView ivCookieDisplay;
    private Toast saveToast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cookie_clicker);

        tvCounter = findViewById(R.id.tvCounter);
        ivCookieDisplay = findViewById(R.id.ivCookieDisplay);

        Animation anim = AnimationUtils.loadAnimation(this, R.anim.const_rotate);
        ivCookieDisplay.startAnimation(anim);

        int storedCookieCount = getSavedCookies();

        updateCookieDisplay();
        if(storedCookieCount != 0) {
            AlertUtils.showConfirmPrompt(this, "Found Saved Cookies!",
                    "After searching throuroughly in the cabinet we found " + storedCookieCount + " cookies! Load them? (no means losing them forever!)",
                    (dialog, which) ->{
                        cookieCount = storedCookieCount;
                        updateCookieDisplay();
                    },
                    (dialog, which) -> {
                        saveCookies(0);
                    });
        }

        saveToast = Toast.makeText(this, "Saved Cookies!", Toast.LENGTH_LONG);
    }

    @Override
    public void onBackPressed() {
        Log.i(TAG, "onStop: STOPPING");
        if(cookieCount != getSavedCookies()) {
            AlertUtils.showConfirmPrompt(this, "Wait a second!", "It seems you have some unsaved cookies! would you like to save them?", (d, w) -> {
                saveCookies(cookieCount);
                super.onBackPressed();
            }, (d, w) -> {
                super.onBackPressed();
            });
        } else {
            super.onBackPressed();
        }
    }

    public void addCookie(View view) {
        final float base = 1.2f;
        final float scale = 0.2f;
        final int duration = 50;

        cookieCount++;
        view.animate().scaleX(base + scale).scaleY(base + scale).setDuration(duration).withEndAction(() -> {
            view.animate().scaleX(base).scaleY(base).setDuration(duration).start();
        }).start();
        updateCookieDisplay();
    }

    private void updateCookieDisplay() {
        tvCounter.setText(getString(R.string.cookie_clicker_counter_display, cookieCount));
    }

    public void saveCookies(int num) {
        SharedPreferences sharedPref = getSharedPreferences(getString(R.string.shared_preferences_name), Context.MODE_PRIVATE);
        sharedPref.edit().putInt(getString(R.string.shared_preferences_cookie_clicker_cookie_count), num).apply();
    }

    private int getSavedCookies() {
        SharedPreferences sharedPref = getSharedPreferences(getString(R.string.shared_preferences_name), Context.MODE_PRIVATE);
        return sharedPref.getInt(getString(R.string.shared_preferences_cookie_clicker_cookie_count), 0);
    }


    // OnClickListener
    public void saveCookies(View view) {
        saveCookies(cookieCount);
        saveToast.show();
    }

    public void loadCookies(View view) {
        cookieCount = getSavedCookies();
        updateCookieDisplay();
    }


    public void clearCookies(View view) {
        cookieCount = 0;
        updateCookieDisplay();
        saveCookies(0);
    }
}

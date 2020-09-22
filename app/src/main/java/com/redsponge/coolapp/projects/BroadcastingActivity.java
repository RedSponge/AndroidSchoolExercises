package com.redsponge.coolapp.projects;

import android.app.Activity;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.widget.TextView;

import com.redsponge.coolapp.R;

import androidx.annotation.Nullable;

public class BroadcastingActivity extends Activity {

    private TextView tvBatteryView;
    private BatteryBroadcastReciever bbr;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_broadcast);
        tvBatteryView = findViewById(R.id.tvBatteryView);
        bbr = new BatteryBroadcastReciever(tvBatteryView);
    }

    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(bbr, new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(bbr);
    }
}

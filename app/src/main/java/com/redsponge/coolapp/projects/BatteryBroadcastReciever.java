package com.redsponge.coolapp.projects;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.TextView;

import com.redsponge.coolapp.R;

import java.text.BreakIterator;

public class BatteryBroadcastReciever extends BroadcastReceiver {

    private TextView displayTextView;

    public BatteryBroadcastReciever(TextView displayTextView) {
        this.displayTextView = displayTextView;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        int battery = intent.getIntExtra("level", 0);
        displayTextView.setText(String.format(context.getString(R.string.battery_life), battery));
    }
}

package com.redsponge.coolapp;

import android.app.Activity;

public class MenuEntry {

    public final String name;
    public final String index;
    public final Class<? extends Activity> activity;

    public MenuEntry(String name, String index, Class<? extends Activity> activity) {
        this.name = name;
        this.index = index;
        this.activity = activity;
    }
}

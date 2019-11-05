package com.redsponge.coolapp.menu;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

import com.example.coolapp.R;
import com.redsponge.coolapp.projects.ImageSetterActivity;
import com.redsponge.coolapp.projects.SendSMSActivity;
import com.redsponge.coolapp.projects.CatchTheButtonActivity;
import com.redsponge.coolapp.projects.RandomColourGenerator;
import com.redsponge.coolapp.projects.RandomNumberActivity;

public class MainActivity extends Activity {

    private ListView listViewContents;
    private MainListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listViewContents = findViewById(R.id.listViewContents);
        adapter = new MainListAdapter(this);

        listViewContents.setAdapter(adapter);

        setupMainTable();
    }

    private void setupMainTable() {
        adapter.add(new MenuEntry("Random Colour Generator", "0", RandomColourGenerator.class));
        adapter.add(new MenuEntry("Random Number Generator", "1", RandomNumberActivity.class));
        adapter.add(new MenuEntry("Catch The Button", "1.5", CatchTheButtonActivity.class));
        adapter.add(new MenuEntry("Send SMS", "2", SendSMSActivity.class));
        adapter.add(new MenuEntry("Image Capturer", "2.5", ImageSetterActivity.class));
    }
}

package com.redsponge.coolapp.menu;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

import com.example.coolapp.R;

public class MainActivity extends Activity {

    @SuppressWarnings("FieldCanBeLocal")
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
        adapter.addAll(MenuEntries.ENTRIES);
    }
}

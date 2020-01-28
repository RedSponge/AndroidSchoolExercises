package com.redsponge.coolapp.menu;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import com.redsponge.coolapp.R;
import com.redsponge.coolapp.util.toast.SingleToast;

public class MainActivity extends Activity {

    @SuppressWarnings("FieldCanBeLocal")
    private ListView listViewContents;
    private MainListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i("MainActivity", "onCreate: test");
        listViewContents = findViewById(R.id.listViewContents);
        adapter = new MainListAdapter(this);

        listViewContents.setAdapter(adapter);

        setupMainTable();
        SingleToast.getInstance().addConfig(new SingleToast.ToastConfig(this, "Message A", Toast.LENGTH_LONG));
        SingleToast.getInstance().setOnToastEnd(-1, () -> {
            Log.i("meow", "onCreate: HEY WE FINISHED ONE");
//            SingleToast.getInstance().addConfig(new SingleToast.ToastConfig(this, "Message C", Toast.LENGTH_LONG, -2));
        });
        SingleToast.getInstance().addConfig(new SingleToast.ToastConfig(this, "Message B", Toast.LENGTH_LONG));

    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i("MainActivity", "onPause: Paused");
    }

    private void setupMainTable() {
        adapter.addAll(MenuEntries.ENTRIES);
    }
}

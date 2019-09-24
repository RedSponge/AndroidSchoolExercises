package com.redsponge.coolapp.menu;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.coolapp.R;

import java.util.Objects;

public class MainListAdapter extends ArrayAdapter<MenuEntry> {

    public MainListAdapter(Context context) {
        super(context, 0);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_menu_entry, parent, false);
        }

        TextView tvNumber = convertView.findViewById(R.id.tvNumber);
        TextView tvName = convertView.findViewById(R.id.tvName);
        Button buttonEnter = convertView.findViewById(R.id.buttonEnter);
        final MenuEntry entry = Objects.requireNonNull(getItem(position));

        tvNumber.setText("" + entry.index);
        tvName.setText(entry.name);
        buttonEnter.setOnClickListener((v) -> {
            getContext().startActivity(new Intent(getContext(), entry.activity));
        });

        return convertView;
    }
}

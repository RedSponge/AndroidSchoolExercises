package com.redsponge.coolapp.util.alert;

import android.content.DialogInterface;
import android.widget.Spinner;

public class OnSpinnerAcceptClickAdapter<T> implements DialogInterface.OnClickListener {

    private Spinner spinner;
    private OnChoiceAcceptListener<T> onChoiceAcceptListener;

    public OnSpinnerAcceptClickAdapter(Spinner spinner, OnChoiceAcceptListener<T> onChoiceAcceptListener) {
        this.spinner = spinner;
        this.onChoiceAcceptListener = onChoiceAcceptListener;
    }

    @Override
    @SuppressWarnings("unchecked")
    public void onClick(DialogInterface dialog, int which) {
        T choice = (T) spinner.getSelectedItem();
        if(choice != null) {
            onChoiceAcceptListener.accept(choice, spinner.getSelectedItemPosition());
        }
    }
}

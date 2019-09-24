package com.redsponge.coolapp.util.alert;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

public abstract class AlertPromptTextWatcher implements TextWatcher {

    private EditText editText;

    public AlertPromptTextWatcher() {}

    public void setEditText(EditText editText) {
        this.editText = editText;
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        onTextChanged(s, editText);
    }

    @Override
    public void afterTextChanged(Editable s) {

    }

    public abstract void onTextChanged(CharSequence s, EditText text);
}

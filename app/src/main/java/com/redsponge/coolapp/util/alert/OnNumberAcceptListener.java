package com.redsponge.coolapp.util.alert;

import android.app.AlertDialog;

@FunctionalInterface
public interface OnNumberAcceptListener {

    void onNumberEntered(int val);

}

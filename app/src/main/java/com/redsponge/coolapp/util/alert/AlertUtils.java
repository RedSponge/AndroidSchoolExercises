package com.redsponge.coolapp.util.alert;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.text.InputType;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

public class AlertUtils {


    /**
     * Shows an alert to the user with an OK button
     * @param ctx The context calling this method
     * @param title The alert's title
     * @param content The alert's contents
     * @param listener A listener for when OK has been pressed
     */
    public static void showAlert(Context ctx, String title, String content, DialogInterface.OnClickListener listener) {
        new AlertDialog.Builder(ctx)
                .setTitle(title)
                .setMessage(content)
                .setNeutralButton("OK", listener)
                .show();
    }

    /**
     * Displays a text prompt
     * @param ctx The context calling this method
     * @param title The title of the popup
     * @param onOk What happens when the text is submitted,
     * @param onTextChanged A listener to when the text changes
     * @param preEnteredText Text that is written in the input {@link EditText} when the popup is opened
     * @param isPassword Should the TextView be displayed as a password (dots)
     * @param hint Sets the hint of the edittext if not null
     *
     */
    public static void showTextPrompt(Context ctx, String title, OnTextAcceptListener onOk, AlertPromptTextWatcher onTextChanged, String preEnteredText, boolean isPassword, String hint) {
        EditText text = new EditText(ctx);

        if(preEnteredText != null) {
            text.setText(preEnteredText);
            text.setSelection(0, preEnteredText.length());
        }

        if(isPassword) {
            text.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        }
        if(hint != null) {
            text.setHint(hint);
        }
        text.requestFocus();

        if(onTextChanged != null) {
            onTextChanged.setEditText(text);
            text.addTextChangedListener(onTextChanged);
        }

        new AlertDialog.Builder(ctx)
                .setTitle(title)
                .setView(text)
                .setPositiveButton("OK", new OnTextAcceptClickAdapter(text, onOk))
                .setNegativeButton("Cancel", (dialog, which) -> dialog.cancel())
                .show();
    }

    /**
     * Shows a yes/no question
     * @param ctx The context calling this method
     * @param title The title of the popup
     * @param message The message of the popup
     * @param accept What to do when 'Yes' is pressed
     */
    public static void showConfirmPrompt(Context ctx, String title, String message, DialogInterface.OnClickListener accept, DialogInterface.OnClickListener decline) {
        new AlertDialog.Builder(ctx)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton("Yes", accept)
                .setNegativeButton("No", (dialog, which) -> {
                    if(decline != null) decline.onClick(dialog, which);
                    dialog.cancel();
                })
                .setCancelable(false)
                .show();
    }

    public static <T> void showChoicePrompt(Context ctx, String title, OnChoiceAcceptListener<T> spinnerAcceptListener, T... vals) {
        String[] displayArr = new String[vals.length];

        for (int i = 0; i < vals.length; i++) {
            displayArr[i] = vals[i].toString();
        }

        new AlertDialog.Builder(ctx)
                .setTitle(title)
                .setSingleChoiceItems(displayArr, 0, (d, i) -> {
                    spinnerAcceptListener.accept(vals[i], i);
                    d.dismiss();
                }).show();
    }

    public static <T> void showSpinnerPrompt(Context ctx, String title, ArrayAdapter<T> spinnerAdapter, OnChoiceAcceptListener<T> spinnerAcceptListener, int initialIndex) {
        Spinner spinner = new Spinner(ctx);
        spinner.setAdapter(spinnerAdapter);
        spinner.setSelection(initialIndex);

        new AlertDialog.Builder(ctx)
                .setTitle(title)
                .setView(spinner)
                .setPositiveButton("OK", new OnSpinnerAcceptClickAdapter<>(spinner, spinnerAcceptListener))
                .setNegativeButton("Cancel", (dialog, which) -> dialog.cancel())
                .show();
    }

    public static void showNumberPrompt(Context ctx, String title, OnNumberAcceptListener onNumberAcceptListener, int defaultValue, int maxLength) {
        EditText input = new EditText(ctx);
        input.setHint("" + defaultValue);
        input.setText("" + defaultValue);
        input.setInputType(InputType.TYPE_CLASS_NUMBER);
        input.setMaxLines(maxLength);
        new AlertDialog.Builder(ctx)
                .setTitle(title)
                .setView(input)
                .setPositiveButton("OK", (d, w) -> onNumberAcceptListener.onNumberEntered(Integer.parseInt(input.getText().toString())))
                .setNegativeButton("Cancel", (dialog, which) -> {
                    onNumberAcceptListener.onNumberEntered(defaultValue);
                    dialog.cancel();
                })
                .show();
    }
}

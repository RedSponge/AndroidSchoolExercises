package com.redsponge.coolapp.projects;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.coolapp.R;

public class SendSMSActivity extends Activity {

    private EditText etPhone;
    private EditText etMsg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_sms);

        etPhone = findViewById(R.id.etPhone);
        etMsg = findViewById(R.id.etMsg);
    }

    public void sendSMS(View view) {
        Intent intent = new Intent(Intent.ACTION_VIEW);

        intent.setData(Uri.parse("sms:"));
        intent.setType("vnd.android-dir/mms-sms");
        intent.putExtra("sms_body", etMsg.getText().toString());
        intent.putExtra("address", etPhone.getText().toString());

        startActivity(intent);
    }
}

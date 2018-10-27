package com.example.user.healthtech;

import android.app.Activity;
import android.os.Bundle;

/**
 * Created by User on 27.10.2018.
 */

public class Doctor_Chat extends Activity {

    private String user;
    private String patient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.doctor_chat_activity);
        user = getIntent().getExtras().getString("username");
        patient = getIntent().getExtras().getString("patient");
    }
}

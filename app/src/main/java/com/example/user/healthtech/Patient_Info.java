package com.example.user.healthtech;

import android.app.Activity;
import android.os.Bundle;

/**
 * Created by User on 27.10.2018.
 */

public class Patient_Info extends Activity {

    private String user;
    private String doctor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.patient_info_activity);
        user = getIntent().getExtras().getString("username");
        doctor = getIntent().getExtras().getString("doctor");
    }
}

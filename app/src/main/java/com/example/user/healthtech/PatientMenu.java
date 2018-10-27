package com.example.user.healthtech;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class PatientMenu extends AppCompatActivity {
    private String user_name;
    private String user_pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.patient_menu_activity);

        user_name = getIntent().getExtras().getString("username");
        user_pass = getIntent().getExtras().getString("password");
    }
}

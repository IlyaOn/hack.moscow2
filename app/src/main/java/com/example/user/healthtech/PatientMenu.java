package com.example.user.healthtech;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class PatientMenu extends AppCompatActivity {
    private String id;
    private String user_name;
    private String user_pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.patient_menu_activity);

        id = getIntent().getExtras().getString("id");
    }

    public void getDoctors(View view)
    {
        Intent intent = new Intent(PatientMenu.this, Patient_Login.class);
        intent.putExtra("id", id);
        startActivity(intent);
    }
}

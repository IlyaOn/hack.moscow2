package com.example.user.healthtech;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by User on 27.10.2018.
 */

public class Doctor_Choose_Patient extends Activity {

    private String user;
    private String patient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.doctor_choose_patiente_activity);
        user = getIntent().getExtras().getString("username");
        patient = getIntent().getExtras().getString("patient");
        Toast.makeText(getApplicationContext(), user + " " + patient,
                Toast.LENGTH_SHORT).show();
    }


    public void Course_Treatment(View view){
        // Выполняем переход на другой экран:
        Intent intent = new Intent(Doctor_Choose_Patient.this,Doctor_Course.class);
        intent.putExtra("username", user);
        intent.putExtra("patient", patient);
        startActivity(intent);
    }

    public void Chat(View view){
        // Выполняем переход на другой экран:
        Intent intent = new Intent(Doctor_Choose_Patient.this,Doctor_Chat.class);
        intent.putExtra("username", user);
        intent.putExtra("patient", patient);
        startActivity(intent);
    }
}

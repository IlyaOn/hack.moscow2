package com.example.user.healthtech;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

/**
 * Created by User on 27.10.2018.
 */

public class Patient_Choose_Doctor extends Activity {

    private String user;
    private String doctor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.patient_choose_doctor_activity);
        user = getIntent().getExtras().getString("username");
        doctor = getIntent().getExtras().getString("doctor");
        Toast.makeText(getApplicationContext(), user + " " + doctor,
                Toast.LENGTH_SHORT).show();
    }

    public void Course_Treatment(View view){
        // Выполняем переход на другой экран:
        Intent intent = new Intent(Patient_Choose_Doctor.this,Patient_Course_Treatment.class);
        intent.putExtra("username", user);
        intent.putExtra("doctor", doctor);
        startActivity(intent);
    }

    public void Chat(View view){
        // Выполняем переход на другой экран:
        Intent intent = new Intent(Patient_Choose_Doctor.this,Patient_Chat.class);
        intent.putExtra("username", user);
        intent.putExtra("doctor", doctor);
        startActivity(intent);
    }

    public void Info(View view){
        // Выполняем переход на другой экран:
        Intent intent = new Intent(Patient_Choose_Doctor.this,Patient_Info.class);
        intent.putExtra("username", user);
        intent.putExtra("doctor", doctor);
        startActivity(intent);
    }
}

package com.example.user.healthtech;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

public class MedicalCard extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.medical_card_activity);


    }

    public void SaveCard(View view){
        EditText name = (EditText)findViewById(R.id.edit_name);
        EditText surname = (EditText)findViewById(R.id.edit_surname);
        EditText lastname = (EditText)findViewById(R.id.edit_lastname);
        EditText date = (EditText)findViewById(R.id.edit_date_of_birth);
        EditText polis = (EditText)findViewById(R.id.edit_polis);


    }
}

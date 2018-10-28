package com.example.user.healthtech;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MedicalCard extends AppCompatActivity {

    private String user_name;
    private String user_pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.medical_card_activity);

        user_name = getIntent().getExtras().getString("username");
        user_pass = getIntent().getExtras().getString("password");
    }

    public void SaveCard(View view){
        String name = ((EditText)findViewById(R.id.edit_name)).getText().toString();
        String surname = ((EditText)findViewById(R.id.edit_surname)).getText().toString();
        String lastname = ((EditText)findViewById(R.id.edit_lastname)).getText().toString();
        String date = ((EditText)findViewById(R.id.edit_date_of_birth)).getText().toString();
        String polis = ((EditText)findViewById(R.id.edit_polis)).getText().toString();
        String patternNames = "^[ ]*[A-Za-z]+[ ]*$";
        String patternDate =  "^[ ]*[0-9]{2}[.][0-9]{2}[.][0-9]{4}[ ]*$";
        String patternPolis = "^[ ]*[0-9]{16}[ ]*$";
        if (CheckStr(name, patternNames) &&
                CheckStr(surname, patternNames) &&
                (CheckStr(lastname, patternNames)|| lastname.equals("")) &&
                CheckStr(date, patternDate) &&
                CheckStr(polis, patternPolis))
        {
            Toast.makeText(getApplicationContext(), "Вы успешно зарегестрированы, " + name + " " + lastname + "!",Toast.LENGTH_SHORT).show();

            String temp_to_serv = "{\"isRegister\":" + true + ",\"isPatient\":" + true + ",\"name\":\"" + name + "\",\"surname\":\"" + surname + "\",\"lastname\":\"" + lastname +
                    "\",\"login\":\"" + user_name + "\",\"password\":\"" + user_pass + "\"}";
            Json.gettingJSON = temp_to_serv;
            Json.communication_server("enter/", temp_to_serv);

            Intent intent = new Intent(MedicalCard.this, HealthMain.class);
            intent.putExtra("username", user_name);
            startActivity(intent);
        }
        else
            Toast.makeText(getApplicationContext(), "One or more fields are invalid",Toast.LENGTH_SHORT).show();


    }

    public boolean CheckStr(String str, String pattern){
        Pattern p = Pattern.compile(pattern);
        Matcher m = p.matcher(str);
        return m.matches();
    }
}

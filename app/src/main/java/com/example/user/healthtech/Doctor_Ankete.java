package com.example.user.healthtech;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Doctor_Ankete extends AppCompatActivity {
    private String user_name;
    private String user_pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.doctor_ankee_activity);

        user_name = getIntent().getExtras().getString("username");
        user_pass = getIntent().getExtras().getString("password");
    }

    public void SaveAnkete(View view){
        String name = ((EditText)findViewById(R.id.edit_name_doctor)).getText().toString();
        String surname = ((EditText)findViewById(R.id.edit_surname_doctor)).getText().toString();
        String lastname = ((EditText)findViewById(R.id.edit_lastname_doctor)).getText().toString();
        String date = ((EditText)findViewById(R.id.edit_date_of_birth_doctor)).getText().toString();
        String patternNames = "^[ ]*[A-Za-z]+[ ]*$";
        String patternDate =  "^[ ]*[0-9]{2}[.][0-9]{2}[.][0-9]{4}[ ]*$";
        if (CheckStr(name, patternNames) &&
                CheckStr(surname, patternNames) &&
                (CheckStr(lastname, patternNames)|| lastname.equals("")) &&
                CheckStr(date, patternDate))
        {
            Toast.makeText(getApplicationContext(), "Вы успешно зарегестрированы, " + name + " " + lastname + "!",Toast.LENGTH_SHORT).show();

            String temp_to_serv = "{\"isRegister\":" + true + ",\"isPatient\":" + false + ",\"name\":\"" + name + "\",\"surname\":\"" + surname + "\",\"lastname\":\"" + lastname +
                    "\",\"login\":\"" + user_name + "\",\"password\":\"" + user_pass + "\"}";
            Json.gettingJSON = temp_to_serv;
            Json.communication_server();

            Intent intent = new Intent(Doctor_Ankete.this, Doctor_Login.class);
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

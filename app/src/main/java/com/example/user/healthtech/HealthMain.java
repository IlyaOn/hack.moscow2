package com.example.user.healthtech;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;
import java.util.HashMap;
import java.util.Map;

import static java.lang.Boolean.getBoolean;

public class HealthMain extends AppCompatActivity {

    private String name;
    private String surname;
    private String lastname;
    private String host;
    private Switch choose_switch;
    private EditText username;
    private EditText password;
    //  переключение для авторизации
    private boolean patient_flag;
    private boolean doctor_flag = true;
    JSONObject json = new JSONObject();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health_main);

        // Связываемся с элементами нашего интерфейса:
        choose_switch = (Switch) findViewById(R.id.doctor_patient_switch);
        if (choose_switch != null) {
            choose_switch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    // в зависимости от значения isChecked выводим нужное сообщение
                    if (isChecked) {
                        patient_flag = true;
                        doctor_flag = false;
                    } else {
                        doctor_flag = true;
                        patient_flag = false;
                    }
                }
            });
        }
        username = (EditText) findViewById(R.id.edit_user);
        password = (EditText) findViewById(R.id.edit_password);
    }

    // Обрабатываем нажатие кнопки "Войти":
    public void Login(View view) {

        String user_str = username.getText().toString();
        String pass_str = password.getText().toString();
        String temp_to_serv;

        // показываем Toast сообщение об успешном входе:
        if (user_str.equals("")|| pass_str.equals(""))
            Toast.makeText(getApplicationContext(), "Поля не могут оставаться пустыми!",Toast.LENGTH_SHORT).show();
        else if (patient_flag) {
            temp_to_serv = "{\"isRegister\":" + false + ",\"isPatient\":" + true + ",\"name\":" + name + ",\"surname\":" + surname + ",\"lastname\":" + lastname +
                    ",\"login\":" + user_str + ",\"password\":" + pass_str + "}";
            Json.gettingJSON = temp_to_serv;
            Json.communication_server();
            JSONObject j_obj;
            boolean flag = false;
            try {
                j_obj = new JSONObject(Json.gettingJSON);
                flag =  j_obj.getBoolean("isRegister");
            } catch (JSONException e){};
            if (flag) {
                Toast.makeText(getApplicationContext(), "Вход выполнен!", Toast.LENGTH_SHORT).show();
                // Выполняем переход на другой экран:
                Intent intent = new Intent(HealthMain.this, Patient_Login.class);
                intent.putExtra("username", user_str);
                startActivity(intent);
            } else
                Toast.makeText(getApplicationContext(), "Некорректный логин/пароль!",Toast.LENGTH_SHORT).show();
        }
        else if (doctor_flag) {
            temp_to_serv = "{\"isRegister\":" + false + ",\"isPatient\":" + false + ",\"name\":" + name + ",\"surname\":" + surname + ",\"lastname\":" + lastname +
                    ",\"login\":" + user_str + ",\"password\":" + pass_str + "}";
            Json.gettingJSON = temp_to_serv;
            Json.communication_server();
            JSONObject j_obj;
            boolean flag = false;
            try {
                j_obj = new JSONObject(Json.gettingJSON);
                flag =  j_obj.getBoolean("isSuccess");
            } catch (JSONException e){};
            if (flag) {
                Toast.makeText(getApplicationContext(), "Вход выполнен!",Toast.LENGTH_SHORT).show();
                // Выполняем переход на другой экран:
                Intent intent = new Intent(HealthMain.this, Doctor_Login.class);
                intent.putExtra("username", user_str);
                startActivity(intent);
            } else
                Toast.makeText(getApplicationContext(), "Некорректный логин/пароль!",Toast.LENGTH_SHORT).show();
        }
    }

    //  Обрабатываем нажатие кнопки "Регистрация"
    public void Register(View view){
        String user_str = username.getText().toString();
        String pass_str = password.getText().toString();

        if (!user_str.equals("")){
            if (pass_str.equals(""))
                Toast.makeText(getApplicationContext(), "Для начала введите пароль!", Toast.LENGTH_SHORT).show();
            else{
                Intent intent = new Intent();
                if (patient_flag)
                    intent = new Intent(HealthMain.this, MedicalCard.class);
                else if (doctor_flag)
                    intent = new Intent(HealthMain.this, Doctor_Ankete.class);
                intent.putExtra("username", user_str);
                intent.putExtra("password", pass_str);
                startActivity(intent);
            }
        } else
            Toast.makeText(getApplicationContext(), "Сначала введите логиин!", Toast.LENGTH_SHORT).show();
    }
}
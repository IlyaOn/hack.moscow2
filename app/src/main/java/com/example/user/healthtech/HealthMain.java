package com.example.user.healthtech;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

public class HealthMain extends AppCompatActivity {

    private Switch choose_switch;
    private EditText username;
    private EditText password;
    //  переключение для авторизации
    private boolean patient_flag;
    private boolean doctor_flag = true;
    //  логины и пароли докторов и пациентов
    private Map<String, String> lp_patients = new HashMap<String, String>();
    private Map<String, String> lp_doctors = new HashMap<String, String>();

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
        lp_patients.put("p", "2");
        lp_doctors.put("d", "1");
        username = (EditText) findViewById(R.id.edit_user);
        password = (EditText) findViewById(R.id.edit_password);

    }

    // Обрабатываем нажатие кнопки "Войти":
    public void Login(View view) {

        String user_str = username.getText().toString();
        String pass_str = password.getText().toString();

        // показываем Toast сообщение об успешном входе:
        if (patient_flag && lp_patients.containsKey(user_str) && lp_patients.get(user_str).equals(pass_str)) {

            Toast.makeText(getApplicationContext(), "Вход выполнен!",Toast.LENGTH_SHORT).show();

            // Выполняем переход на другой экран:
            Intent intent = new Intent(HealthMain.this,Patient_Login.class);
            intent.putExtra("username", user_str);
            startActivity(intent);
        }
        else if (doctor_flag && lp_doctors.containsKey(user_str) && lp_doctors.get(user_str).equals(pass_str)) {

            Toast.makeText(getApplicationContext(), "Вход выполнен!",Toast.LENGTH_SHORT).show();

            // Выполняем переход на другой экран:
            Intent intent = new Intent(HealthMain.this, Doctor_Login.class);
            intent.putExtra("username", user_str);
            startActivity(intent);
        }

        // В другом случае выдаем сообщение с ошибкой:
        else
            Toast.makeText(getApplicationContext(), "Неправильные данные!",Toast.LENGTH_SHORT).show();
    }

    //  Обрабатываем нажатие кнопки "Регистрация"
    public void Register(View view){

        String user_str = username.getText().toString();
        String pass_str = password.getText().toString();
        Map<String, String> templ_map;

        if (patient_flag)
            templ_map = lp_patients;
        else
            templ_map = lp_doctors;

        if (!user_str.equals("")){
            if (templ_map.containsKey(user_str))
                Toast.makeText(getApplicationContext(), "Пользователь с данным логином уже зарегистрирован!", Toast.LENGTH_SHORT).show();
            else if (pass_str.equals(""))
                Toast.makeText(getApplicationContext(), "Для начала введите пароль!", Toast.LENGTH_SHORT).show();
            else{
                templ_map.put(user_str, pass_str);
                Toast.makeText(getApplicationContext(), "Пользователь " + user_str + " успешно зарегистрирован", Toast.LENGTH_SHORT).show();
            }
        } else
            Toast.makeText(getApplicationContext(), "Сначала введите логиин!", Toast.LENGTH_SHORT).show();
    }
}
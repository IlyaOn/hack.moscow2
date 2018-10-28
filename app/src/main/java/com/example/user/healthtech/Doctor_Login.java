package com.example.user.healthtech;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Doctor_Login extends Activity {

    private String user;
    private String patient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.doctor_login_activity);
        user = getIntent().getExtras().getString("username");
        ArrayList<String> names = new ArrayList<>();
        // находим список
        ListView list = (ListView) findViewById(R.id.list);
        // создаем адаптер
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, names);
        for (int i = 0; i < 5; i++)
            names.add("Patient " + i + " of " + user);
        adapter.notifyDataSetChanged();
        // присваиваем адаптер списку
        list.setAdapter(adapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id){
                Toast.makeText(getApplicationContext(), ((TextView)view).getText(),
                        Toast.LENGTH_SHORT).show();
                //Intent intent = new Intent(Doctor_Login.this,Doctor_Choose_Patient.class);
                //intent.putExtra("username", user);
               // intent.putExtra("patient", ((TextView)view).getText());
                //startActivity(intent);
            }
        });
    }
}

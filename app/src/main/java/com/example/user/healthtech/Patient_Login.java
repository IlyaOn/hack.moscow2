package com.example.user.healthtech;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import java.util.ArrayList;
import android.widget.ArrayAdapter;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.TextView;
import android.widget.Toast;
import android.view.View;

/**
 * Created by User on 27.10.2018.
 */

public class Patient_Login extends Activity {

    private String user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.patient_login_activity);
        user = getIntent().getExtras().getString("username");
        ArrayList<String> names = new ArrayList<>();
        // находим список
        ListView list = (ListView) findViewById(R.id.list);
        // создаем адаптер
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, names);
        for (int i = 0; i < 5; i++)
            names.add("Doctor " + i + " of " + user);
        adapter.notifyDataSetChanged();
        // присваиваем адаптер списку
        list.setAdapter(adapter);
        list.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id){
                Toast.makeText(getApplicationContext(), ((TextView)view).getText(),
                        Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Patient_Login.this,Patient_Choose_Doctor.class);
                intent.putExtra("username", user);
                intent.putExtra("doctor", ((TextView)view).getText());
                startActivity(intent);
            }
        });
    }
}

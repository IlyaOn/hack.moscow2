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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by User on 27.10.2018.
 */

public class Patient_Login extends Activity {

    private String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.patient_login_activity);
        id = getIntent().getExtras().getString("id");
        id = "0";
        ArrayList<String> names = new ArrayList<>();
        // находим список
        ListView list = (ListView) findViewById(R.id.list);
        // создаем адаптер
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, names);

        Json.communication_server("info/doctors/", "{\"patientId\":"+id+"}");

        try {
            JSONObject jsn = new JSONObject(Json.gettingJSON);
            JSONArray doctors = jsn.getJSONArray("doctors");
            for (int i = 0; i < doctors.length();  i++){
                JSONObject obj = doctors.getJSONObject(i);
                names.add(obj.getString("name")  + " " + obj.getString("surname"));
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        adapter.notifyDataSetChanged();
        // присваиваем адаптер списку
        list.setAdapter(adapter);
        list.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id){
                Toast.makeText(getApplicationContext(), ((TextView)view).getText(),
                        Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Patient_Login.this,Doctor_Course.class);
                intent.putExtra("id", id);
                intent.putExtra("doctor", ((TextView)view).getText());
                startActivity(intent);
            }
        });
    }
}

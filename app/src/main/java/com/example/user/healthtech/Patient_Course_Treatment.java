package com.example.user.healthtech;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.CheckedTextView;
import android.widget.ListView;
import java.util.ArrayList;

/**
 * Created by User on 27.10.2018.
 */

public class Patient_Course_Treatment extends Activity{

    private String user;
    private String doctor;
    String[] meds = {"Аскорбинка", "Парацетомол", "Ношпа", "Кларетин", "Спазмалгон"};
    ArrayList<String> medsss = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.patient_course_treatment_activity);
        user = getIntent().getExtras().getString("username");
        doctor = getIntent().getExtras().getString("doctor");

        // находим список
        ListView lvMain = (ListView) findViewById(R.id.list);

        // создаем адаптер
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, medsss);
        for (int i = 0; i < meds.length; i++){
            medsss.add(meds[i]);
        }
        adapter.notifyDataSetChanged();
        // присваиваем адаптер списку
        lvMain.setAdapter(adapter);
    }
}

package com.example.user.healthtech;

import android.app.Activity;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by User on 27.10.2018.
 */

public class Doctor_Course extends Activity {

    private String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.doctor_course_activity);
        id = getIntent().getExtras().getString("id");
        id = "0";

        try {
            Json.communication_server("info/health/", "{\"patientId\":"+id+"}");
            JSONObject jsn = new JSONObject(Json.gettingJSON);
            TextView diag = (TextView) findViewById(R.id.diagnosis);
            diag.setText(diag.getText() + jsn.getString("diseases") + "\n");
            diag.setText(diag.getText() + jsn.getString("short_description") + "\n");

            TextView treats = (TextView) findViewById(R.id.treatment);
            JSONArray arr = jsn.getJSONArray("tablets");
            for (int i = 0; i < arr.length(); i++)
            {
                JSONObject obj = arr.getJSONObject(i);
                treats.setText(treats.getText() + "\n" + obj.getString("pill") + " " + obj.getString("how_often") + " ");
            }

            TextView FIO = (TextView) findViewById(R.id.fio);
            Json.communication_server("info/name/", "{\"Id\":"+id+",\"isPatient\":" + false + "}");
            jsn = new JSONObject(Json.gettingJSON);
            FIO.setText(jsn.getString("surname") + " " + jsn.getString("name") + " " + jsn.getString("lastname"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
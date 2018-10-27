package com.example.user.healthtech;

import android.app.Activity;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by User on 27.10.2018.
 */

public class Doctor_Course extends Activity {

    private String user;
    private String patient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.doctor_course_activity);
        user = getIntent().getExtras().getString("username");
        patient = getIntent().getExtras().getString("patient");
        LinearLayout meds = (LinearLayout)findViewById(R.id.medicine);
        TextView med = new TextView(this);
        med.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        med.setText("123456");
        med.setTextSize(27);
        meds.addView(med);
    }
}
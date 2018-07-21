package com.example.utilisateur.qrthing.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.Toast;

import com.example.utilisateur.qrthing.R;
import com.example.utilisateur.qrthing.fragments.SelectTimeDia;

import java.util.Calendar;

public class TimeKeeperAct extends AppCompatActivity implements SelectTimeDia.SelectTimeListener {

    private NumberPicker eventSelect;
    private Button timeSelect;
    private long time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_keeper);

        eventSelect = (NumberPicker) findViewById(R.id.npSelectEvent);
        eventSelect.setMinValue(1);
        eventSelect.setMaxValue(100);

        timeSelect = (Button) findViewById(R.id.bEditTime);
        timeSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SelectTimeDia dialog = new SelectTimeDia();
                dialog.show(getSupportFragmentManager(), "timeDialog");
            }
        });
        // Set default time text
        Calendar cal = Calendar.getInstance();
        String curTime = cal.get(Calendar.HOUR_OF_DAY)+ ":" + cal.get(Calendar.MINUTE) + ":" + cal.get(Calendar.SECOND)
        timeSelect.setText(curTime);
    }

    public void goScan(View view) {
        Intent intent = new Intent(this, ScanAct.class);
        intent.putExtra("event", eventSelect.getValue());
        intent.putExtra("time", time);
        startActivity(intent);
    }

    @Override
    public void onSelectTime(int hour, int minute, int second) {
        Toast.makeText(this, "" + hour + ":" + minute + ":" + second, Toast.LENGTH_LONG).show();
        timeSelect.setText(""+hour + ":" + minute + ":" + second);
    }
}

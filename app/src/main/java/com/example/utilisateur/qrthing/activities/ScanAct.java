package com.example.utilisateur.qrthing.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.NumberPicker;

import com.example.utilisateur.qrthing.R;
import com.example.utilisateur.qrthing.fragments.SelectTimeDia;
import com.example.utilisateur.qrthing.models.Timelog;
import com.example.utilisateur.qrthing.server.TimelogMessage;

import java.util.Calendar;
import java.util.concurrent.TimeUnit;

public class ScanAct extends AppCompatActivity implements SelectTimeDia.SelectTimeListener {

    Button bTimeSelect;
    NumberPicker npRunner;
    int runner;
    int hour;
    int minute;
    int second;

    int extraEvent;
    long extraStartTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan);
        npRunner = (NumberPicker) findViewById(R.id.npSelectRunner);
        npRunner.setMinValue(1);
        npRunner.setMaxValue(100);

        bTimeSelect = (Button) findViewById(R.id.bSelectTime);
        Calendar cal = Calendar.getInstance();
        bTimeSelect.setText(cal.get(Calendar.HOUR_OF_DAY)+ ":" + cal.get(Calendar.MINUTE) + ":" + cal.get(Calendar.SECOND));

        // Retrieve extra data
        Bundle extras = getIntent().getExtras();
        int event;
        long startTime;
        if (extras == null) {
            event = -1;
            startTime = -1;
        } else {
            event = extras.getInt("event");
            startTime = extras.getInt("time");
        }
    }

    public void sendLog(View view) {
        long finishTime = TimeUnit.HOURS.toMillis(hour) + TimeUnit.MINUTES.toMillis(minute) + TimeUnit.SECONDS.toMillis(second);

        Timelog timelog = new Timelog(npRunner.getValue(), finishTime - extraStartTime);
        TimelogMessage tlMessage = new TimelogMessage(extraEvent, timelog);
        //
        // send to server: event, runner, scanAct.time - timeKeeper.time
    }

    @Override
    public void onSelectTime(int hour, int minute, int second) {
        this.hour = hour;
        this.minute = minute;
        this.second = second;
    }
}

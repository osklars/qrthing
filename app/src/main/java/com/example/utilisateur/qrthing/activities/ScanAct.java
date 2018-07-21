package com.example.utilisateur.qrthing.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.NumberPicker;

import com.example.utilisateur.qrthing.R;
import com.example.utilisateur.qrthing.fragments.SelectTimeDia;

public class ScanAct extends AppCompatActivity implements SelectTimeDia.SelectTimeListener {

    NumberPicker npRunner;
    int runner;
    int hour;
    int minute;
    int second;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan);
        npRunner = (NumberPicker) findViewById(R.id.npSelectRunner);
        npRunner.setMinValue(1);
        npRunner.setMaxValue(100);
        npRunner.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker numberPicker, int i0, int i1) {
                runner = i1;
            }
        });
    }

    public void sendLog(View view) {
        //send to server: TimelogMessage(event, runner, Scanact.time - timekeeper.time)

    }

    @Override
    public void onSelectTime(int hour, int minute, int second) {
        this.hour = hour;
        this.minute = minute;
        this.second = second;
    }
}

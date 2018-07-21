package com.example.utilisateur.qrthing.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.NumberPicker;

import com.example.utilisateur.qrthing.R;

public class LeaderboardAct extends AppCompatActivity {

    NumberPicker eventSelect;
    private int event;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leaderboard);

        eventSelect = (NumberPicker) findViewById(R.id.npSelectEvent);
        eventSelect.setMinValue(1);
        eventSelect.setMaxValue(100);
        eventSelect.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker numberPicker, int i0, int i1) {
                event = i1;
            }
        });

        //savedInstanceState.get(CurrentEvent);
    }

}

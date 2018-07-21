package com.example.utilisateur.qrthing.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.utilisateur.qrthing.R;

public class StartAct extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
    }

    public void goTimeKeeper(View view) {
        Intent intent = new Intent(this, TimeKeeperAct.class);
        startActivity(intent);
    }

    public void goLeaderboard(View view) {
        Intent intent = new Intent(this, LeaderboardAct.class);
        startActivity(intent);
    }


}

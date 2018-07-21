package com.example.utilisateur.qrthing.models;
import java.util.ArrayList;

public class Race {
	private int event;
	private int runners;

	private ArrayList<Timelog> logs;
	private Timelog[] leaderboard;

	public Race(int event, int runners) {
		this.event = event;
		this.runners = runners;
		logs = new ArrayList<>();
		leaderboard = new Timelog[runners];
	}

	public synchronized void log(Timelog log) {
	    logs.add(log);
        for(int i=0; i<runners; i++) {
            if(leaderboard[i]==null || leaderboard[i].getRunner() == log.getRunner()) {
                while(0<i && log.compareTo(leaderboard[i-1])<0) {
                    leaderboard[i] = leaderboard[i-1];
                    i--;
                }
                leaderboard[i] = log;
                return;
            }
        }
	}
	

}

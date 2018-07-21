package com.example.utilisateur.qrthing.models;
public class Timelog {
    private int runner;
    private long time;

    public int getRunner() {
        return runner;
    }

    public long getTime() {
        return time;
    }
    
    public Timelog(int runner, long time) {
        this.runner = runner;
        this.time = time;
    }

    public int compareTo(Timelog log) {
        return (int)(time - log.time);
    }
    public String toString() {
    	return "runner" + runner + " \t time: " + time;
    }
}


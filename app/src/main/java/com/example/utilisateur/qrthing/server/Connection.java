package com.example.utilisateur.qrthing.server;


import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Connection extends Thread {
    private Socket socket;
    private DataServer server;
    private ObjectOutputStream out;
    private ObjectInputStream in;

    private boolean running;
    private int event;

    public static final int LOG = 1;

    public Connection(Socket socket, DataServer server) {
        socket = socket;
        try {
            out = new ObjectOutputStream(socket.getOutputStream());
            in = new ObjectInputStream(socket.getInputStream());
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    public void start() {
        try {
            event = in.readInt();
        } catch(IOException e) {
            e.printStackTrace();
        }
        server.selectEvent(event, this);
        running = true;
    }

    @Override
    public void run() {
        start();
        while(running) {
            try{ //should receive either timelog or request for leaderboard;
                TimelogMessage msg = in.readObject();
            } catch(ClassNotFoundException e) {
                e.printStackTrace();
            } catch(IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void notifyLog() {
        try {
            out.write(LOG);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}



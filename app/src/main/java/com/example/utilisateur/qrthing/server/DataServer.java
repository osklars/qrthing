package com.example.utilisateur.qrthing.server;

import com.example.utilisateur.qrthing.models.Race;
import com.example.utilisateur.qrthing.models.Timelog;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.Semaphore;

public class DataServer {

    public static final int nEvents = 100;
    public static final int nRunners = 300;

    private Race[] races;
    private Semaphore[] raceLocks;

    private Notifier notifier;

    private ServerSocket socket;
    private ArrayList<Connection>[] connections;
    private Semaphore[] connectionsLocks;

    public static void main(String[] args) {
        if (args.length < 1) {
            System.err.println("Usage: java DataServer portnumber");
            System.exit(-1);
        }
        try {
            DataServer server = new DataServer(Integer.parseInt(args[0]));
            server.run();
        } catch (NumberFormatException e) {
            System.err.println("Error: port number must be an integer.");
            System.exit(-1);
        }

    }

    private DataServer(int port) {
        try {
            socket = new ServerSocket(port);
        } catch (IOException e) {
            e.printStackTrace();
        }
        races = new Race[nEvents];
        raceLocks = new Semaphore[nEvents];
        notifier = new Notifier(this);
        connections = new ArrayList<Connection>[nEvents];
        connectionsLocks = new Semaphore[nEvents];
        for(int i=0; i<nEvents; i++) {
            raceLocks[i] = new Semaphore(1);
        }
        for(int i=0; i<nEvents; i++) {
            races[i] = new Race(i, nRunners);
        }
        for(int i=0; i<nEvents; i++) {
            connections[i] = new ArrayList<>();
        }
        for(int i=0; i<nEvents; i++) {
            connectionsLocks[i] = new Semaphore(1);
        }
        notifier.run();
    }

    private void run() {
        System.out.println("Waiting for client messages... ");
        Socket clientSocket = null;
        while(true) {
            try { // Wait for connection
                System.out.println("Waiting for connection...");
                clientSocket = socket.accept();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Connection newCon = new Connection(clientSocket, this);
            newCon.run();
            System.out.println("Connection added");
        }
    }

    // Synchronized private functions
    public void notifyLog(int event) {
        connectionsLocks[event].acquire();
        for(Iterator<Connection> itr = connections[event].iterator(); itr.hasNext();) {
            itr.next().notifyLog();
        }
        connectionsLocks[event].release();
    }

    // Synchronized functions for connections

    public void selectEvent(int event, Connection con) {
        connectionsLocks[event].acquire();
        if(connections[event]==null) {
            connections[event] = new ArrayList<>();
        }
        connections[event].add(con);
        connectionsLocks[event].release();
    }

    public void changeEvent(int oldEvent, int newEvent, Connection con) {
        connectionsLocks[oldEvent].acquire();
        connections[oldEvent].add(con);
        connectionsLocks[oldEvent].release();
        connectionsLocks[newEvent].acquire();
        if(connections[newEvent]==null) {
            connections[newEvent] = new ArrayList<>();
        }
        connections[newEvent].add(con);
        connectionsLocks[newEvent].release();
    }

    public void log(int event, Timelog log) {
        raceLocks[event].acquire();
        races[event].log(log);
        raceLocks[event].release();
        notifyLog(event);
    }

    public synchronized void removeConnection(int event, Connection con) {
        connections[event].remove(con);
        con.interrupt();
        System.out.println("Connection "+con+" removed from event: " + event);
    }
}

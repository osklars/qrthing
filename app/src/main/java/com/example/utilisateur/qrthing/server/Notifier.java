//package com.example.utilisateur.qrthing.server;
//
//import java.util.LinkedList;
//import java.util.Queue;
//import java.util.concurrent.Semaphore;
//
//public class Notifier extends Thread {
//
//    private DataServer server;
//    private Semaphore lock;
//    private Semaphore runLock;
//    private Queue<Integer> buffer;
//
//    public Notifier(DataServer server) {
//        this.server = server;
//        buffer = new LinkedList<>();
//    }
//
//    public synchronized void notify(int event) throws InterruptedException  {
//        lock.acquire();
//        if(!buffer.contains(event)) {
//            buffer.add(event);
//            runLock.release();
//        }
//        runLock.release();
//    }
//
//    @Override
//    public void run() throws InterruptedException  {
//        while(true) {
//            runLock.acquire();
//            lock.acquire();
//            int event = buffer.poll();
//            lock.release();
//            if(event<0) {
//                break;
//            }
//            server.notifyLog(event);
//        }
//    }
//}

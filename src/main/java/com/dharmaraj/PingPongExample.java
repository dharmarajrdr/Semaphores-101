package com.dharmaraj;

import java.util.concurrent.Semaphore;

public class PingPongExample {

    public static void main(String args[]) throws InterruptedException {

        // One thread should print "Ping..." and other thread should print "Pong..."

        Semaphore pingSemaphore = new Semaphore(1);
        Semaphore pongSemaphore = new Semaphore(0);

        Thread t1 = new Thread(() -> {
            while (true) {
                try {
                    pingSemaphore.acquire();
                    System.out.println(Thread.currentThread().getName() + ": Ping...");
                    Thread.sleep(1000);
                    pongSemaphore.release();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "Thread-1");
        Thread t2 = new Thread(() -> {
            while (true) {
                try {
                    pongSemaphore.acquire();
                    System.out.println(Thread.currentThread().getName() + ": Pong...");
                    Thread.sleep(1000);
                    pingSemaphore.release();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "Thread-2");

        t1.start();
        t2.start();
    }
}

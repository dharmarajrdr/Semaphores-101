package com.dharmaraj;

import java.util.concurrent.Semaphore;

class Task implements Runnable {

    private Semaphore semaphore;

    public Task(Semaphore semaphore) {
        this.semaphore = semaphore;
    }

    public void run() {
        try {
            // Random sleep time between 1 to 5 seconds
            int sleepTime = (int) (Math.random() * 5000) + 1000;
            semaphore.acquire();
            System.out.println(Thread.currentThread().getName()
                    + " acquired the permit from shared resource and went to sleep for "
                    + sleepTime / 1000 + "sec.");
            Thread.sleep(sleepTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            System.out.println(Thread.currentThread().getName() + " released the permit.");
            semaphore.release();
        }
    }
}

public class AtomicityExample {

    public static void main(String[] args) {

        Semaphore sharedResource = new Semaphore(1);

        System.out.println(
                "Created a semaphore with 1 permit. When multiple threads try to access the shared resource, only one thread will be allowed to access it at a time.");

        Thread t1 = new Thread(new Task(sharedResource), "Thread-1");
        Thread t2 = new Thread(new Task(sharedResource), "Thread-2");
        Thread t3 = new Thread(new Task(sharedResource), "Thread-3");

        t1.start();
        t2.start();
        t3.start();
    }
}

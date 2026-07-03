package com.dharmaraj;

import java.util.concurrent.Semaphore;

public class PrintAlphabetExample {

    public static void main(String args[]) {

        // Thread 1 should print `A`
        // Thread 2 should print `B`
        // Thread 3 should print `C`
        // repeat

        Semaphore semaphoreA = new Semaphore(1);
        Semaphore semaphoreB = new Semaphore(0);
        Semaphore semaphoreC = new Semaphore(0);

        Thread thread1 = new Thread(() -> {
            while (true) {
                try {
                    semaphoreA.acquire();
                    System.out.print("A");
                    Thread.sleep(1000);
                    semaphoreB.release();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        Thread thread2 = new Thread(() -> {
            while (true) {
                try {
                    semaphoreB.acquire();
                    System.out.print("B");
                    Thread.sleep(1000);
                    semaphoreC.release();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        Thread thread3 = new Thread(() -> {
            while (true) {
                try {
                    semaphoreC.acquire();
                    System.out.print("C");
                    Thread.sleep(1000);
                    semaphoreA.release();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        thread1.start();
        thread2.start();
        thread3.start();
    }
}

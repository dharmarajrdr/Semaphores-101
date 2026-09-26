package com.dharmaraj;

import java.util.concurrent.Semaphore;

public class PrintNaturalNumbersUsingOddEvenThreads {

    public static void main(String[] args) {

        Semaphore oddSemaphore = new Semaphore(1);
        Semaphore evenSemaphore = new Semaphore(0);

        Thread oddThread = new Thread(() -> {
            for (int i = 1; i <= 10; i += 2) {
                try {
                    oddSemaphore.acquire(); // odd permits: 1 -> 0
                    System.out.println(i);
                    evenSemaphore.release(); // even permits: 0 -> 1
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread evenThread = new Thread(() -> {
            for (int i = 2; i <= 10; i += 2) {
                try {
                    evenSemaphore.acquire(); // even permits: 1 -> 0
                    System.out.println(i);
                    oddSemaphore.release(); // odd permits: 0 -> 1
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        oddThread.start();
        evenThread.start();
    }
}

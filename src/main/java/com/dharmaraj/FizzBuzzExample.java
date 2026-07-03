package com.dharmaraj;

import java.util.Scanner;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

public class FizzBuzzExample {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.print("Enter a number: ");
        int N = sc.nextInt();
        sc.close();
        AtomicInteger i = new AtomicInteger(1);

        Semaphore sharedResource = new Semaphore(1);

        // If a number is divisible by 3 and not 5, print `Fizz`
        Thread t1 = new Thread(() -> {
            while (i.get() <= N) {
                if (i.get() % 3 == 0 && i.get() % 5 != 0) {
                    try {
                        sharedResource.acquire();
                        System.out.println("[" + Thread.currentThread().getName() + "] i = " + i + " => Fizz");
                        i.incrementAndGet();
                        Thread.sleep(1000);
                        sharedResource.release();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }, "Thread 1");
        // If a number is divisible by 5 and not 3, print `Buzz`
        Thread t2 = new Thread(() -> {
            while (i.get() <= N) {
                if (i.get() % 3 != 0 && i.get() % 5 == 0) {
                    try {
                        sharedResource.acquire();
                        System.out.println("[" + Thread.currentThread().getName() + "] i = " + i + " => Buzz");
                        i.incrementAndGet();
                        Thread.sleep(1000);
                        sharedResource.release();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }, "Thread 2");
        // If a number is divisible by both 3 and 5, print `FizzBuzz`
        Thread t3 = new Thread(() -> {
            while (i.get() <= N) {
                if (i.get() % 3 == 0 && i.get() % 5 == 0) {
                    try {
                        sharedResource.acquire();
                        System.out.println("[" + Thread.currentThread().getName() + "] i = " + i + " => FizzBuzz");
                        i.incrementAndGet();
                        Thread.sleep(1000);
                        sharedResource.release();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }, "Thread 3");
        // If a number is neither divisible by both 3 and 5, print `BuzzFizz`
        Thread t4 = new Thread(() -> {
            while (i.get() <= N) {
                if (i.get() % 3 != 0 && i.get() % 5 != 0) {
                    try {
                        sharedResource.acquire();
                        System.out.println("[" + Thread.currentThread().getName() + "] i = " + i + " => BuzzFizz");
                        i.incrementAndGet();
                        Thread.sleep(1000);
                        sharedResource.release();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }, "Thread 4");

        t1.start();
        t2.start();
        t3.start();
        t4.start();
    }
}

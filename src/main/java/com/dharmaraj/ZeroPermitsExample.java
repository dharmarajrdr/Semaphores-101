package com.dharmaraj;

import java.util.concurrent.Semaphore;

public class ZeroPermitsExample {

    public static void main(String[] args) {

        // Create a semaphore with 0 permits
        Semaphore semaphore = new Semaphore(0);
        System.out.println(
                "Semaphore created with 0 permits. Someone have to release a permit before any thread can acquire it.");

        boolean acquired = semaphore.tryAcquire();
        System.out.println("Attempt to acquire permit. Was it successful? " + acquired);

        semaphore.release();
        System.out.println("A permit has been released. Now, release count is: " + semaphore.availablePermits());

        acquired = semaphore.tryAcquire();
        System.out.println("Attempt to acquire permit. Was it successful? " + acquired);

    }
}

package com.dharmaraj;

import java.util.concurrent.Semaphore;

public class SimpleSemaphoreExample {

    public static void main(String[] args) throws InterruptedException {

        Semaphore washroomPermits = new Semaphore(3); // Create a semaphore with 3 permits
        System.out.println("There are 3 stalls in the washroom. So, only 3 people can enter at a time.");

        washroomPermits.acquire();
        System.out.println("Person 1 has entered the washroom.");

        washroomPermits.acquire();
        System.out.println("Person 2 has entered the washroom.");

        washroomPermits.acquire();
        System.out.println("Person 3 has entered the washroom.");

        // Now, if a fourth person tries to enter, they will have to wait
        System.out.println(
                "Person 4 is trying to acquire a lock, but all stalls are occupied. So, they will have to wait.");
        boolean acquired = washroomPermits.tryAcquire();
        System.out.println("Person 4 attempted to acquire a permit. Was it successful? " + acquired);

    }
}
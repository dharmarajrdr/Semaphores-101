package com.dharmaraj;

import java.util.concurrent.Semaphore;

public class MultiplePermitsExample {

    public static void main(String[] args) throws InterruptedException {

        // Create a semaphore with 3 permits
        Semaphore carPooling = new Semaphore(3);
        System.out.println("There are 3 seats in the car. So, only 3 people can enter at a time.");

        if (carPooling.tryAcquire(2)) {
            System.out.println("I'm booking 2 seats in the car.");
        }

        System.out.println("Now, only " + carPooling.availablePermits() + " seats are available in the car.");

        if (carPooling.tryAcquire(3)) {
            System.out.println("Someone else is trying to book 3 seats in the car.");
        }

        System.out.println("Now, only " + carPooling.availablePermits() + " seats are available in the car.");

        carPooling.release(5);
        System.out.println("5 seats have been released. Now, " + carPooling.availablePermits()
                + " seats are available in the car.");

    }
}

package com.dharmaraj;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

class BoundedBuffer<T> {

    private final Deque<T> buffer;
    private final Semaphore empty;
    private final Semaphore full;
    private final Semaphore mutex;

    public BoundedBuffer(int capacity) {
        this.buffer = new ArrayDeque<>(capacity);
        this.empty = new Semaphore(capacity);
        this.full = new Semaphore(0);
        this.mutex = new Semaphore(1);  // lock to access the buffer
    }

    public void produce(T item) throws InterruptedException {
        empty.acquire();  // wait for an empty slot
        mutex.acquire();  // lock the buffer
        try {
            buffer.addLast(item);
            Thread.sleep(1000); // Simulate time taken to produce an item
        } finally {
            mutex.release();  // unlock the buffer
            full.release();   // signal that there's a new item
        }
    }

    public T consume() throws InterruptedException {
        full.acquire();   // wait for an item
        mutex.acquire();  // lock the buffer
        T item = null;
        if(!buffer.isEmpty()) {
            item = buffer.poll();
            Thread.sleep(1000); // Simulate time taken to consume an item
        }
        mutex.release();  // unlock the buffer
        empty.release();  // signal that there's a new empty slot
        return item;
    }
}

public class ProducerConsumerExample {
    
    public static void main(String[] args) {
        
        BoundedBuffer<Integer> buffer = new BoundedBuffer<>(5);

        ExecutorService executor = Executors.newFixedThreadPool(5);

        while (!executor.isShutdown()) {
            
            executor.submit(() -> {
                try {
                    int item = (int) (Math.random() * 100);
                    buffer.produce(item);
                    System.out.println("Produced: " + item);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            });

            executor.submit(() -> {
                try {
                    Integer item = buffer.consume();
                    System.out.println("Consumed: " + item);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            });
        }

        executor.shutdown();
    }
}

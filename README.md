## Semaphores 101

- It is a class in `java.util.concurrent` package.
- It provides two main operations: `acquire()` and `release()`.

- **Example:** Public Washroom with 3 stalls. 
    ```
           -------------------------------------
    *      |  Stall 1  |  Stall 2  |  Stall 3  |
    |      -------------------------------------

    A   B   C   D   E   F   G   H   I   
    |   |   |   |   |   |   |   |   |
    ```
    - The stalls are the resources that can be acquired and released.
    - The semaphore controls access to these stalls, allowing **a maximum of 3 people to use them at the same time**.
    - When a person wants to use a stall, they call `acquire()`. 
    - If a stall is available, they can use it. If all stalls are occupied, they must wait until one becomes available. 
    - After using the stall, they call `release()` to free it up for others.
    - This mechanism ensures that no more than 3 people can use the stalls simultaneously, preventing overcrowding and ensuring fair access.

        ```java
        Semaphore washroomPermits = new Semaphore(3); // 3 stalls available
        washroomPermits.acquire();  // Person tries to acquire a stall
        washroomPermits.release();  // Person releases the stall after use
        ```

- We can initialize the semaphore with `0` permits, which is completely valid. In this case, any thread that tries to acquire a permit will block until another thread releases a permit. [Example](src/main/java/com/dharmaraj/ZeroPermitsExample.java)
- `availablePermits()` method can be used to check how many permits are currently available.
- We can acquire and release multiple permits at once by passing an integer argument to `acquire(int permits)` and `release(int permits)`. [Example](src/main/java/com/dharmaraj/MultiplePermitsExample.java)
- When more than one thread is trying to acquire a permit, only one thread will be able to acquire/release it at a time. Meaning, internally `acquire()` and `release()` methods are Atomic and thread-safe. [Example](src/main/java/com/dharmaraj/AtomicityExample.java)

- **Problems** on passing controls to different threads:
    1. Ping-pong: [Example](src/main/java/com/dharmaraj/PingPongExample.java)
    2. Print ABC: [Example](src/main/java/com/dharmaraj/PrintAlphabetExample.java)
    3. FizzBuzz: [Example](src/main/java/com/dharmaraj/FizzBuzzExample.java)
    4. Print Natural Numbers: [Example](src/main/java/com/dharmaraj/PrintNaturalNumbersUsingOddEvenThreads.java)
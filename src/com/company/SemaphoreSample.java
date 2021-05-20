package com.company;

import java.time.LocalDateTime;
import java.util.concurrent.Semaphore;

public class SemaphoreSample {
    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(2);
        for (int i = 0; i < 5; i++) {
            int w = i;
            final int finalW = i;
            new Thread(() -> {
                System.out.println("Поток " + w + "остановился перед семафором");
                try {
                    Thread.sleep(1000);
                    semaphore.acquire(2);
                    System.out.printf("Поток " + w + "занял семафор:%s\n", LocalDateTime.now());
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    semaphore.release(2);
                    System.out.println("Поток " + w + "освободил семафор");
                }
            }).start();
        }
    }
}

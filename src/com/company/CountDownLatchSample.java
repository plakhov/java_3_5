package com.company;

import java.time.LocalDateTime;
import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class CountDownLatchSample {
    public static void main(String[] args) throws InterruptedException {
        System.out.printf("%s\n", LocalDateTime.now());
        int threadCount = 4;
        CountDownLatch countDownLatch = new CountDownLatch(threadCount);
        System.out.printf("%s\n", LocalDateTime.now());
        for (int i = 0; i < 2; i++) {
            int w = i;
            new Thread(() -> {
                try {
                    Thread.sleep(1000+new Random().nextInt(5000));
                    System.out.printf("Поток "+w+" завершил подготовку %s\n", LocalDateTime.now());
                    countDownLatch.countDown();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }
        System.out.printf("%s\n", LocalDateTime.now());
        boolean result = countDownLatch.await(7, TimeUnit.SECONDS);
        System.out.printf("%s\n", LocalDateTime.now());
        if (result) {
            System.out.println("Все потоки вызвали метод countDown");
        } else {
            System.out.println("Не все потоки вызвали метод countDown");
        }
    }
}

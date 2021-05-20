package com.company;

import java.time.LocalDateTime;
import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class CyclicBarrierSample {
    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(2);
        for (int i = 0; i < 5; i++) {
            int w = i;
            new Thread(() -> {
                try {
                    System.out.printf("Спортсмен "+w+" начинает подготовку %s\n", LocalDateTime.now());
                    Thread.sleep(1000+new Random().nextInt(2000));
                    System.out.printf("Спортсмен "+w+" закончил подготовку %s\n", LocalDateTime.now());
                    cyclicBarrier.await(2, TimeUnit.SECONDS);
                    System.out.printf("Спортсмен "+w+" дождался остальных подготовку %s\n", LocalDateTime.now());
                } catch (InterruptedException | BrokenBarrierException | TimeoutException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }
}

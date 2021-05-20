package com.company;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockSample {
    public static void main(String[] args) {
        Lock lock = new ReentrantLock();
        for (int i = 0; i < 5; i++) {
            int w = i;
            new Thread(() -> {
                System.out.println("Поток "+ w + " подошел к точке синхронизации");
                boolean resultLock = false;
                try {
                    resultLock = lock.tryLock(6, TimeUnit.SECONDS);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (resultLock) {
                    try {
                        System.out.println("Поток "+ w + " зашел в синхронизацию");
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("Поток "+ w + " вышел из синхронизации");
                    lock.unlock();
                } else {
                    System.out.println("Блокировку не получили");
                }
            }).start();
        }
    }
}

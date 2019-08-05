package com.lq.concurrent;

import org.junit.jupiter.api.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @program: wheel
 * @Date: 2019/7/23 0023 14:53
 * @Author: lin huan
 * @Description:
 */
public class VolatileTest {

    @Test
    void test1() throws InterruptedException {
        volatileExample example = new volatileExample();
        CountDownLatch latch = new CountDownLatch(1000);
        for (int i = 0;i<1000;i++){
          Thread thread = new Thread(()->{
              latch.countDown();
              try {
                  latch.await();
              } catch (InterruptedException e) {
                  e.printStackTrace();
              }
              example.add();

          });
          thread.start();
        }
        latch.await();
        System.out.println(example.getI());
    }

    @Test
    void test2() throws InterruptedException {
        volatileExample example = new volatileExample();
        CountDownLatch latch = new CountDownLatch(1000);
        ExecutorService service = Executors.newCachedThreadPool();
        for (int i = 0;i<1000;i++){
            service.execute(()->{
                example.add();
                latch.countDown();
            });
        }
        latch.await();
        service.shutdown();
        System.out.println(example.getI());
    }

}



class volatileExample{
    private volatile int i;//保证可见性 不保证原子性

    public void add() {
        i++;
    }

    public int getI() {
        return i;
    }
}
package fax.play.practice.c3;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class Foo {

   private final BlockingQueue<Integer> blockingQueue1 = new ArrayBlockingQueue<>(1);
   private final BlockingQueue<Integer> blockingQueue2 = new ArrayBlockingQueue<>(1);

   public Foo() {
   }

   public void first(Runnable printFirst) throws InterruptedException {
      // printFirst.run() outputs "first". Do not change or remove this line.
      printFirst.run();

      blockingQueue1.offer(1);
   }

   public void second(Runnable printSecond) throws InterruptedException {
      blockingQueue1.take();

      // printSecond.run() outputs "second". Do not change or remove this line.
      printSecond.run();

      blockingQueue2.offer(2);
   }

   public void third(Runnable printThird) throws InterruptedException {
      blockingQueue2.take();

      // printThird.run() outputs "third". Do not change or remove this line.
      printThird.run();
   }

}

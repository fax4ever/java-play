package fax.play.practice.c2;

import java.util.concurrent.Semaphore;

class Foo {

   private final Semaphore waitFor1 = new Semaphore(0);
   private final Semaphore waitFor2 = new Semaphore(0);

   public Foo() {
   }

   public void first(Runnable printFirst) throws InterruptedException {
      // printFirst.run() outputs "first". Do not change or remove this line.
      printFirst.run();

      waitFor1.release();
   }

   public void second(Runnable printSecond) throws InterruptedException {
      waitFor1.acquire();

      // printSecond.run() outputs "second". Do not change or remove this line.
      printSecond.run();

      waitFor2.release();
   }

   public void third(Runnable printThird) throws InterruptedException {
      waitFor2.acquire();

      // printThird.run() outputs "third". Do not change or remove this line.
      printThird.run();
   }
}

package fax.play.practice;

import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.IntConsumer;

class FizzBuzz {
   final int n;
   final AtomicInteger current = new AtomicInteger(1);

   Semaphore fCanGo = new Semaphore(0);
   Semaphore bCanGo = new Semaphore(0);
   Semaphore fbCanGo = new Semaphore(0);
   Semaphore nCanGo = new Semaphore(1);

   public FizzBuzz(int n) {
      this.n = n;
   }

   // printFizz.run() outputs "fizz".
   public void fizz(Runnable printFizz) throws InterruptedException {
      while (current.get() <= n) {
         fCanGo.acquire();
         if (current.get() <= n) {
            printFizz.run();
         }
         releaseNext();
      }
   }

   // printBuzz.run() outputs "buzz".
   public void buzz(Runnable printBuzz) throws InterruptedException {
      while (current.get() <= n) {
         bCanGo.acquire();
         if (current.get() <= n) {
            printBuzz.run();
         }
         releaseNext();
      }
   }

   // printFizzBuzz.run() outputs "fizzbuzz".
   public void fizzbuzz(Runnable printFizzBuzz) throws InterruptedException {
      while (current.get() <= n) {
         fbCanGo.acquire();
         if (current.get() <= n) {
            printFizzBuzz.run();
         }
         releaseNext();
      }
   }

   // printNumber.accept(x) outputs "x", where x is an integer.
   public void number(IntConsumer printNumber) throws InterruptedException {
      while (current.get() <= n) {
         nCanGo.acquire();
         if (current.get() <= n) {
            printNumber.accept(current.get());
         }
         releaseNext();
      }
   }

   private void releaseNext() {
      int value = current.incrementAndGet();
      if (value > n) {
         fbCanGo.release();
         fCanGo.release();
         bCanGo.release();
         nCanGo.release();
      }

      if (value % 15 == 0) {
         fbCanGo.release();
      } else if (value % 3 == 0) {
         fCanGo.release();
      } else if (value % 5 == 0) {
         bCanGo.release();
      } else {
         nCanGo.release();
      }
   }
}

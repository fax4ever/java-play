package fax.play.practice;

import java.util.concurrent.Semaphore;
import java.util.function.IntConsumer;

public class ZeroEvenOdd {

   final int n;
   final Semaphore zeroCanGo = new Semaphore(1);
   final Semaphore evenCanGo = new Semaphore(0);
   final Semaphore oddCanGo = new Semaphore(0);

   public ZeroEvenOdd(int n) {
      this.n = n;
   }

   // printNumber.accept(x) outputs "x", where x is an integer.
   public void zero(IntConsumer printNumber) throws InterruptedException {
      for (int i = 1; i <= n; i++) {
         zeroCanGo.acquire();
         printNumber.accept(0);
         if (i % 2 == 0) {
            evenCanGo.release();
         } else {
            oddCanGo.release();
         }
      }
   }

   public void even(IntConsumer printNumber) throws InterruptedException {
      for (int i = 2; i <= n; i = i+2) {
         evenCanGo.acquire();
         printNumber.accept(i);
         zeroCanGo.release();
      }
   }

   public void odd(IntConsumer printNumber) throws InterruptedException {
      for (int i = 1; i <= n; i = i+2) {
         oddCanGo.acquire();
         printNumber.accept(i);
         zeroCanGo.release();
      }
   }
}

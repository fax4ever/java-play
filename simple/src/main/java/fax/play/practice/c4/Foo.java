package fax.play.practice.c4;

public class Foo {

   boolean firstSignal = false;
   boolean secondSignal = false;
   final Object firstMonitor = new Object();
   final Object secondMonitor = new Object();

   public void firstWait() {
      synchronized (firstMonitor) {
         while (!firstSignal) {
            try {
               firstMonitor.wait();
            } catch (InterruptedException e) {
               throw new RuntimeException(e);
            }
         }
      }
   }

   public void secondWait() {
      synchronized (secondMonitor) {
         while (!secondSignal) {
            try {
               secondMonitor.wait();
            } catch (InterruptedException e) {
               throw new RuntimeException(e);
            }
         }
      }
   }

   public void firstNotify() {
      synchronized (firstMonitor) {
         firstSignal = true;
         firstMonitor.notify();
      }
   }

   public void secondNotify() {
      synchronized (secondMonitor) {
         secondSignal = true;
         secondMonitor.notify();
      }
   }

   public void first(Runnable printFirst) throws InterruptedException {
      // printFirst.run() outputs "first". Do not change or remove this line.
      printFirst.run();
      firstNotify();
   }

   public void second(Runnable printSecond) throws InterruptedException {
      firstWait();

      // printSecond.run() outputs "second". Do not change or remove this line.
      printSecond.run();

      secondNotify();
   }

   public void third(Runnable printThird) throws InterruptedException {
      secondWait();

      // printThird.run() outputs "third". Do not change or remove this line.
      printThird.run();
   }
}

package fax.play.practice.c2;

import java.util.concurrent.Semaphore;

public class FooBar {

   private final int n;
   private final Semaphore waitFor1 = new Semaphore(0);
   private final Semaphore waitFor2 = new Semaphore(1);

   public FooBar(int n) {
      this.n = n;
   }

   public void foo(Runnable printFoo) throws InterruptedException {
      for (int i = 0; i < n; i++) {
         waitFor2.acquire();
         // printFoo.run() outputs "foo". Do not change or remove this line.
         printFoo.run();
         waitFor1.release();
      }
   }

   public void bar(Runnable printBar) throws InterruptedException {
      for (int i = 0; i < n; i++) {
         waitFor1.acquire();
         // printBar.run() outputs "bar". Do not change or remove this line.
         printBar.run();
         waitFor2.release();
      }
   }
}

package fax.play.practice;

import java.util.concurrent.Semaphore;

public class H2O {

   final Semaphore hCanGo = new Semaphore(2);
   final Semaphore oCanGo = new Semaphore(0);

   public void hydrogen(Runnable releaseHydrogen) throws InterruptedException {
      hCanGo.acquire();

      // releaseHydrogen.run() outputs "H". Do not change or remove this line.
      releaseHydrogen.run();

      oCanGo.release();
   }

   public void oxygen(Runnable releaseOxygen) throws InterruptedException {
      oCanGo.acquire(2);

      // releaseOxygen.run() outputs "O". Do not change or remove this line.
      releaseOxygen.run();

      hCanGo.release(2);
   }
}

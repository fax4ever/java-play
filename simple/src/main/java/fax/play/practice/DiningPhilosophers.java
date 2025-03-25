package fax.play.practice;

import java.util.concurrent.Semaphore;

public class DiningPhilosophers {

   private final Semaphore[] semaphores = new Semaphore[5];

   public DiningPhilosophers() {
      semaphores[0] = new Semaphore(0);
      semaphores[1] = new Semaphore(0);
      semaphores[2] = new Semaphore(1);
      semaphores[3] = new Semaphore(0);
      semaphores[4] = new Semaphore(1);
   }

   // call the run() method of any runnable to execute its code
   public void wantsToEat(int philosopher, Runnable pickLeftFork, Runnable pickRightFork, Runnable eat,
                          Runnable putLeftFork, Runnable putRightFork) throws InterruptedException {
      semaphores[philosopher].acquire();
      pickRightFork.run();
      pickLeftFork.run();
      eat.run();
      putLeftFork.run();
      putRightFork.run();
      semaphores[(philosopher + 1) % 5].release();
   }
}

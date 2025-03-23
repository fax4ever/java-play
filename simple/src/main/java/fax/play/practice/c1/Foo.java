package fax.play.practice.c1;

class Foo {

   volatile boolean waitFor1 = true;
   volatile boolean waitFor2 = true;

   public Foo() {

   }

   public void first(Runnable printFirst) throws InterruptedException {
      // printFirst.run() outputs "first". Do not change or remove this line.
      printFirst.run();

      waitFor1 = false;
   }

   public void second(Runnable printSecond) throws InterruptedException {
      while (waitFor1) {
         Thread.sleep(10);
      }

      // printSecond.run() outputs "second". Do not change or remove this line.
      printSecond.run();

      waitFor2 = false;
   }

   public void third(Runnable printThird) throws InterruptedException {
      while (waitFor2) {
         Thread.sleep(10);
      }

      // printThird.run() outputs "third". Do not change or remove this line.
      printThird.run();
   }
}

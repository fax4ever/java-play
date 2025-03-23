package fax.play.practice.c2;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.assertj.core.api.Assertions;
import org.junit.Test;

public class FooBarTest {

   @Test
   public void test() throws Exception {
      ExecutorService executorService = Executors.newFixedThreadPool(2);

      FooBar fooBar = new FooBar(3);
      executorService.submit(() -> {
         try {
            Thread.sleep((long)(Math.random() * 1000));
            fooBar.foo(() -> System.out.print("foo"));
         } catch (InterruptedException e) {
            throw new RuntimeException(e);
         }
      });
      executorService.submit(() -> {
         try {
            Thread.sleep((long)(Math.random() * 1000));
            fooBar.bar(() -> System.out.print("bar"));
         } catch (InterruptedException e) {
            throw new RuntimeException(e);
         }
      });

      executorService.shutdown();
      Assertions.assertThat(executorService.awaitTermination(10000, TimeUnit.MILLISECONDS)).isTrue();
   }
}

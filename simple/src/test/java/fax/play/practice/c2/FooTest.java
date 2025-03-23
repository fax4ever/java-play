package fax.play.practice.c2;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.assertj.core.api.Assertions;
import org.junit.Test;

public class FooTest {

   @Test
   public void test() throws Exception {
      ExecutorService executorService = Executors.newFixedThreadPool(3);

      Foo foo = new Foo();
      executorService.submit(() -> {
         try {
            Thread.sleep((long)(Math.random() * 1000));
            foo.first(() -> System.out.print("first"));
         } catch (InterruptedException e) {
            throw new RuntimeException(e);
         }
      });
      executorService.submit(() -> {
         try {
            Thread.sleep((long)(Math.random() * 1000));
            foo.second(() -> System.out.print("second"));
         } catch (InterruptedException e) {
            throw new RuntimeException(e);
         }
      });
      executorService.submit(() -> {
         try {
            Thread.sleep((long)(Math.random() * 1000));
            foo.third(() -> System.out.print("third"));
         } catch (InterruptedException e) {
            throw new RuntimeException(e);
         }
      });

      executorService.shutdown();
      Assertions.assertThat(executorService.awaitTermination(10000, TimeUnit.MILLISECONDS)).isTrue();
   }
}

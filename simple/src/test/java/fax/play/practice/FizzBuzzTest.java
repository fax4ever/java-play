package fax.play.practice;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.assertj.core.api.Assertions;
import org.junit.Test;


public class FizzBuzzTest {

   @Test
   public void test() throws Exception {
      ExecutorService executorService = Executors.newFixedThreadPool(4);

      FizzBuzz foo = new FizzBuzz(15);
      executorService.submit(() -> {
         try {
            Thread.sleep((long)(Math.random() * 1000));
            foo.buzz(() -> System.out.print("buzz "));
         } catch (InterruptedException e) {
            throw new RuntimeException(e);
         }
      });
      executorService.submit(() -> {
         try {
            Thread.sleep((long)(Math.random() * 1000));
            foo.fizz(() -> System.out.print("fizz "));
         } catch (InterruptedException e) {
            throw new RuntimeException(e);
         }
      });
      executorService.submit(() -> {
         try {
            Thread.sleep((long)(Math.random() * 1000));
            foo.fizzbuzz(() -> System.out.print("fizzbuzz "));
         } catch (InterruptedException e) {
            throw new RuntimeException(e);
         }
      });
      executorService.submit(() -> {
         try {
            Thread.sleep((long)(Math.random() * 1000));
            foo.number((n) -> System.out.print(n + " "));
         } catch (InterruptedException e) {
            throw new RuntimeException(e);
         }
      });

      executorService.shutdown();
      Assertions.assertThat(executorService.awaitTermination(10000, TimeUnit.MILLISECONDS)).isTrue();
   }

}

package fax.play.practice;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

public class MinSubArraySumTest {

   MinSubArraySum target = new MinSubArraySum();

   @Test
   public void test_1() {
      int[] ints = {2, 3, 1, 2, 4, 3};
      int i = target.minSubArrayLen(7, ints);
      assertThat(i).isEqualTo(2);
   }

   @Test
   public void test_2() {
      int[] ints = {1,2,3,4,5};
      int i = target.minSubArrayLen(15, ints);
      assertThat(i).isEqualTo(5);
   }

   @Test
   public void test_3() {
      int[] ints = {12,28,83,4,25,26,25,2,25,25,25,12};
      int i = target.minSubArrayLen(213, ints);
      assertThat(i).isEqualTo(8);
   }
}

package fax.play.practice;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class ImprovedSmallestSumTest {

   private ImprovedSmallestSum target = new ImprovedSmallestSum();

   @Test
   public void test() {
      int[] num1 = {1,7,11};
      int[] num2 = {2,4,6};
      List<List<Integer>> lists = target.kSmallestPairs(num1, num2, 3);
      assertThat(lists).containsExactlyInAnyOrder(c(1,2),c(1,4),c(1,6));
   }

   @Test
   public void test_2() {
      int[] num1 = {-10,-4,0,0,6};
      int[] num2 = {3,5,6,7,8,100};
      List<List<Integer>> lists = target.kSmallestPairs(num1, num2, 10);
      assertThat(lists).containsExactlyInAnyOrder(c(-10,3),c(-10,5),c(-10,6),c(-10,7),c(-10,8),c(-4,3),c(-4,5),c(-4,6),c(-4,7),c(0,3));
   }

   static List<Integer> c(int a, int b) {
      return Arrays.asList(a,b);
   }
}

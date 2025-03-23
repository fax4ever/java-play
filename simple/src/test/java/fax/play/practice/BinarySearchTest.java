package fax.play.practice;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

public class BinarySearchTest {

   BinarySearch target = new BinarySearch();

   @Test
   public void test() {
      int[] nums = {-33, -32, -30, -20, -19, -7, -6, 0, 1, 23, 34, 44, 50};
      int result = target.searchInsert(nums, 49);
      assertThat(result).isEqualTo(12);
   }

}

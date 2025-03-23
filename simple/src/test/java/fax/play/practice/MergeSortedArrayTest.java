package fax.play.practice;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

public class MergeSortedArrayTest {

   private final MergeSortedArray testSubject = new MergeSortedArray();

   @Test
   public void merge() {
      int[] num1 = {1,2,3,0,0,0};
      int[] num2 = {2,5,6};
      int[] expected = {1,2,2,3,5,6};

      testSubject.merge(num1, 3, num2, 3);
      assertThat(num1).isEqualTo(expected);
   }

}

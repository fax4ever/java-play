package fax.play.practice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.Test;

public class BinaryTreeRightSideViewTest {

   BinaryTreeRightSideView target = new BinaryTreeRightSideView();

   @Test
   public void test() {
      Integer[] a = {2, 4, 5};
      Integer[] b = {3};
      List<Integer> merge = target.merge(new ArrayList<>(Arrays.asList(a)), new ArrayList<>(Arrays.asList(b)));
      Assertions.assertThat(merge).containsExactly(3, 4, 5);
   }

}

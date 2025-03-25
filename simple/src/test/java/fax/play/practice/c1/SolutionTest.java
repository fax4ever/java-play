package fax.play.practice.c1;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

public class SolutionTest {

   static final int[][] BROAD = {
         {-1, -1, -1, -1, -1, -1},
         {-1, -1, -1, -1, -1, -1},
         {-1, -1, -1, -1, -1, -1},
         {-1, 35, -1, -1, 13, -1},
         {-1, -1, -1, -1, -1, -1},
         {-1, 15, -1, -1, -1, -1}};

   @Test
   public void test() {
      HashMap<Integer, Solution.Cell> expected = new HashMap<>();
      for (int i=1; i<=36; i++) {
         Solution.Cell cell;
         if (i == 2) {
            cell = new Solution.Cell(i, 15);
         } else if (i == 14) {
            cell = new Solution.Cell(i, 35);
         } else if (i == 17) {
            cell = new Solution.Cell(i, 13);
         } else {
            cell = new Solution.Cell(i, -1);
         }

         expected.put(i, cell);
      }

      Solution solution = new Solution();
      Map<Integer, Solution.Cell> cells = solution.cells(BROAD);
      assertThat(cells).isEqualTo(expected);
   }
}

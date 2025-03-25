package fax.play.practice.c1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution {

   public int snakesAndLadders(int[][] board) {
      Map<Integer, Cell> cells = cells(board);


      return 0;
   }

   public static class Node {
      List<Node> nexts = new ArrayList<>(6);

   }

   public Map<Integer, Cell> cells(int[][] broad) {
      int maxI = broad.length;
      int maxJ = 0;
      if (maxI > 0) {
         maxJ = broad[0].length;
      }

      return cells(broad, maxI, maxJ);
   }

   public Map<Integer, Cell> cells(int[][] broad, int maxI, int maxJ) {
      Map<Integer, Cell> cells = new HashMap<>(maxI * maxJ);
      int id = 0;
      int row = 0;

      for (int i = maxI-1; i >= 0; i--) {
         if (row++ % 2 == 0) {
            // left to right
            for (int j = 0; j < maxJ; j++) {
               Cell node = new Cell(++id, broad[i][j]);
               cells.put(node.id, node);
            }
         } else {
            // right to left
            for (int j = maxJ - 1; j >= 0; j--) {
               Cell node = new Cell(++id, broad[i][j]);
               cells.put(node.id, node);
            }
         }
      }
      return cells;
   }

   public record Cell(int id, int num, List<Cell> next) {

      public Cell(int id, int num) {
         this(id, num, new ArrayList<>(6));
      }
   }

}

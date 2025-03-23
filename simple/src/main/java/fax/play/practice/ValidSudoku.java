package fax.play.practice;

import java.util.HashSet;
import java.util.Set;

public class ValidSudoku {

   public boolean isValidSudoku(char[][] board) {
      if (!validRows(board)) {
         return false;
      }
      if (!validColumns(board)) {
         return false;
      }
      return validBoxes(board);
   }

   private boolean validBoxes(char[][] board) {
      for (int i = 0; i < 3; i++) {
         for (int j = 0; j < 3; j++) {
            if (!validateBox(board, i * 3, j * 3)) {
               return false;
            }
         }
      }
      return true;
   }

   private boolean validateBox(char[][] board, int a, int b) {
      ValueCollector valueCollector = new ValueCollector();
      for (int i = a; i < a + 3; i++) {
         for (int j = b; j < b + 3; j++) {
            if (valueCollector.addAndValidate(board[i][j])) {
               return false;
            }
         }
      }
      return true;
   }

   private boolean validColumns(char[][] board) {
      for (int i=0; i<9; i++) {
         ValueCollector valueCollector = new ValueCollector();
         for (int j=0; j<9; j++) {
            if (valueCollector.addAndValidate(board[i][j])) {
               return false;
            }
         }
      }
      return true;
   }

   private boolean validRows(char[][] board) {
      for (int i=0; i<9; i++) {
         ValueCollector valueCollector = new ValueCollector();
         for (int j=0; j<9; j++) {
            if (valueCollector.addAndValidate(board[j][i])) {
               return false;
            }
         }
      }
      return true;
   }

   static class ValueCollector {
      Set<Character> state = new HashSet<>();

      public boolean addAndValidate(char item) {
         if (item == '.') {
            return false;
         }
         if (state.contains(item)) {
            return true;
         }
         state.add(item);
         return false;
      }
   }
}

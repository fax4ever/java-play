package fax.play.practice;

public class NumberOfIsland {

   static final char ONE = '1';
   static final char ZERO = '0';

   public int numIslands(char[][] grid) {
      int islands = 0;

      while (true) {
         Coordinates nextOne = findAOne(grid);
         if (nextOne == null) {
            return islands;
         }

         islands++;
         removeIslandOf(nextOne, grid);
      }
   }

   private Coordinates findAOne(char[][] grid) {
      for (int i = 0; i < grid.length; i++) {
         for (int j = 0; j < grid[i].length; j++) {
            if (grid[i][j] == ONE) {
               return new Coordinates(i, j, grid.length, grid[i].length);
            }
         }
      }
      return null;
   }

   private void removeIslandOf(Coordinates nextOne, char[][] grid) {
      nextOne.zero(grid);

      Coordinates left = nextOne.left();
      if (left != null && left.value(grid) == ONE) {
         removeIslandOf(left, grid);
      }

      Coordinates right = nextOne.right();
      if (right != null && right.value(grid) == ONE) {
         removeIslandOf(right, grid);
      }

      Coordinates top = nextOne.top();
      if (top != null && top.value(grid) == ONE) {
         removeIslandOf(top, grid);
      }

      Coordinates bottom = nextOne.bottom();
      if (bottom != null && bottom.value(grid) == ONE) {
         removeIslandOf(bottom, grid);
      }
   }

   record Coordinates(int x, int y, int length, int height) {
      Coordinates left() {
         if (x == 0) {
            return null;
         }
         return new Coordinates(x-1, y, length, height);
      }

      Coordinates top() {
         if (y == 0) {
            return null;
         }
         return new Coordinates(x, y-1, length, height);
      }

      Coordinates right() {
         if (x == length - 1) {
            return null;
         }
         return new Coordinates(x+1, y, length, height);
      }

      Coordinates bottom() {
         if (y == height - 1) {
            return null;
         }
         return new Coordinates(x, y+1, length, height);
      }

      char value(char[][] grid) {
         return grid[x][y];
      }

      void zero(char[][] grid) {
         grid[x][y] = ZERO;
      }
   }
}

package fax.play.practice;

public class MinSubArraySum {

   public int minSubArrayLen(int target, int[] nums) {
      if (nums.length == 0) {
         return 0;
      }

      int solution = Integer.MAX_VALUE;
      Window window = new Window(target, nums);
      if (window.solves()) {
         solution = Math.min(solution, window.size());
      }

      for (int i = 0; i < nums.length - 1; i++) {
         window.move();
         if (window.solves()) {
            solution = Math.min(solution, window.size());
         }
      }

      return (solution == Integer.MAX_VALUE) ? 0 : solution;
   }

   public static class Window {
      final int target;
      final int[] nums;
      int first = 0;
      int last = 0;
      int total;

      public Window(int target, int[] nums) {
         this.target = target;
         this.nums = nums;
         total = nums[0];
      }

      public int totalWithoutFirst() {
         return total - nums[first];
      }

      public boolean solves() {
         return total >= target;
      }

      public int size() {
         return last - first + 1;
      }

      public void move() {
         if (last == nums.length - 1) {
            throw new RuntimeException("Window cannot move further");
         }
         last++;
         total += nums[last];
         if (solves()) {
            tryToImprove();
         }
      }

      private void tryToImprove() {
         if (totalWithoutFirst() >= target) {
            total = totalWithoutFirst();
            first++;
            tryToImprove();
         }
      }

      @Override
      public String toString() {
         StringBuilder builder = new StringBuilder("[ ");
         for (int i = first; i <= last; i++) {
            builder.append(nums[i]);
            builder.append(" ");
         }
         builder.append("]");
         return builder.toString();
      }
   }
}

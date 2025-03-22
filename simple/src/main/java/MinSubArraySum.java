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

      public Window(int target, int[] nums) {
         this.target = target;
         this.nums = nums;
      }

      public int valueFrom(int from) {
         int tot = 0;
         for (int i = from; i <= last; i++) {
            tot += nums[i];
         }
         return tot;
      }

      public boolean solves() {
         return valueFrom(first) >= target;
      }

      public int size() {
         return last - first + 1;
      }

      public void move() {
         if (last == nums.length - 1) {
            throw new RuntimeException("Window cannot move further");
         }
         last++;
         if (solves()) {
            tryToImprove();
         }
      }

      private void tryToImprove() {
         if (valueFrom(first + 1) >= target) {
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

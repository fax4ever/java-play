package fax.play.practice;

public class BinarySearch {

   public static final int CONSTANT_TIME = 10;

   public int searchInsert(int[] nums, int target) {
      if (nums.length == 0) {
         return 0;
      }

      int a = 0; // first element in the interval
      int b = nums.length - 1; // last element in the interval
      return searchInsert(nums, target, a, b);
   }

   public int searchInsert(int[] nums, int target, int a, int b) {
      if (b - a <= CONSTANT_TIME) {
         return simpleSearch(nums, target, a, b);
      }
   }

   private int simpleSearch(int[] nums, int target, int a, int b) {
      for (int i = a; i <= b; i++) {
         if (target <= nums[i]) {
            return i;
         }
      }
      return b;
   }
}

package fax.play.practice;

public class RemoveElement {

   int i = 0;
   int j = 0;

   public int removeElement(int[] nums, int val) {
      i = 0;
      j = nums.length - 1;

      while (i <= j) {
         if (nums[i] == val) {
            tryToSwap(nums, val);
            if (nums[i] == val) {
               return i;
            }
         }
         i++;
      }
      return i;
   }

   public void tryToSwap(int[] nums, int val) {
      while (i < j) {
         if (nums[j] != val) {
            nums[i] = nums[j];
            nums[j] = val;
            return;
         }
         j--;
      }
   }
}

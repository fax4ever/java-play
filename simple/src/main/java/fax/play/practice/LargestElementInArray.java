package fax.play.practice;

import java.util.Collections;
import java.util.PriorityQueue;

public class LargestElementInArray {

   public int findKthLargest(int[] nums, int k) {
      PriorityQueue<Integer> heap = new PriorityQueue<>(Collections.reverseOrder());
      for (int i = 0; i < nums.length; i++) {
         heap.add(nums[i]);
      }
      for (int i = 0; i < k - 1; i++) {
         heap.poll();
      }
      return heap.peek();
   }

}

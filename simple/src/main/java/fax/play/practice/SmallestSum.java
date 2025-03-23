package fax.play.practice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.PriorityQueue;

public class SmallestSum {

   public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
      // even if the problem requires a minHeap, we use a maxHeap to bound the memory footprint
      PriorityQueue<Pair> maxHeap = new PriorityQueue<>(k, Collections.reverseOrder());

      int num1Iteration = Math.min(nums1.length, k);
      int num2Iteration = Math.min(nums2.length, k);
      for (int i = 0; i < num1Iteration; i++) {
         for (int j = 0; j < num2Iteration; j++) {
            maxHeap.add(new Pair(nums1[i], nums2[j]));
            if (maxHeap.size() > k) {
               maxHeap.poll(); // remove the greatest element
               break;
            }
         }
      }

      PriorityQueue<Pair> minHeap = new PriorityQueue<>(maxHeap);
      ArrayList<List<Integer>> result = new ArrayList<>(k);
      for (int i = 0; i < k; i++) {
         Pair poll = minHeap.poll();
         result.add(Objects.requireNonNull(poll).list());
      }
      return result;
   }

   record Pair(int x, int y) implements Comparable<Pair> {
      int value() {
         return x + y;
      }

      List<Integer> list() {
         return Arrays.asList(x, y);
      }

      @Override
      public int compareTo(Pair o) {
         return this.value() - o.value();
      }
   }
}

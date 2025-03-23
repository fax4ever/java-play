package fax.play.practice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.PriorityQueue;

public class ImprovedSmallestSum {

   public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
      int i = 0;
      int j = 0;

      // even if the problem requires a minHeap, we use a maxHeap to bound the memory footprint
      PriorityQueue<Pair> maxHeap = new PriorityQueue<>(k, Collections.reverseOrder());
      maxHeap.offer(new Pair(nums1[i], nums2[j]));

      while (maxHeap.size() < k || !((i + 1 == nums1.length) && (j + 1 == nums2.length))) {
         int iNext = (i + 1 == nums1.length) ? Integer.MAX_VALUE : nums1[i + 1];
         int jNext = (j + 1 == nums2.length) ? Integer.MAX_VALUE : nums2[j + 1];

         if (iNext <= jNext) {
            // a step more on i is more (or equal) promising...
            moveOnI(nums1, nums2, k, j, maxHeap, i);
            i++;
         } else {
            // a step more on j is more (or equal) promising...
            moveOnJ(nums1, nums2, k, i, maxHeap, j);
            j++;
         }
      }

      PriorityQueue<Pair> minHeap = new PriorityQueue<>(maxHeap);
      ArrayList<List<Integer>> result = new ArrayList<>(k);
      for (int ii = 0; ii < k; ii++) {
         Pair poll = minHeap.poll();
         result.add(Objects.requireNonNull(poll).list());
      }
      return result;
   }

   private static void moveOnJ(int[] nums1, int[] nums2, int k, int i, PriorityQueue<Pair> maxHeap, int j) {
      for (int ii = 0; ii <= i; ii++) {
         Pair newPair = new Pair(nums1[ii], nums2[j + 1]);
         maxHeap.offer(newPair);
         if (maxHeap.size() > k) {
            Pair removed = maxHeap.poll();// remove the greatest element
            if (removed.equals(newPair)) {
               break;
            }
         }
      }
   }

   private static void moveOnI(int[] nums1, int[] nums2, int k, int j, PriorityQueue<Pair> maxHeap, int i) {
      for (int jj = 0; jj <= j; jj++) {
         Pair newPair = new Pair(nums1[i + 1], nums2[jj]);
         maxHeap.offer(newPair);
         if (maxHeap.size() > k) {
            Pair removed = maxHeap.poll();// remove the greatest element
            if (removed.equals(newPair)) {
               break;
            }
         }
      }
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

package fax.play.practice;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class MinAbsDiffBSD {

   public int getMinimumDifference(TreeNode root) {
      Set<Integer> values = new HashSet<>();

      values.add(root.val);
      if (root.left != null) {
         collect(root.left, values);
      }
      if (root.right != null) {
         collect(root.right, values);
      }

      return getMinimumDifference(values);
   }

   private int getMinimumDifference(Set<Integer> values) {
      ArrayList<Integer> list = new ArrayList<>(values);
      Collections.sort(list);

      Integer lastElem = null;
      int result = Integer.MAX_VALUE;

      for (Integer val : list) {
         if (lastElem == null) {
            lastElem = val;
            continue;
         }

         int diff = val - lastElem;
         if (diff == 1) {
            return 1; // optimization
         }
         result = Math.min(result, diff);
         lastElem = val;
      }

      return result;
   }

   private void collect(TreeNode node, Set<Integer> values) {
      values.add(node.val);
      if (node.left != null) {
         collect(node.left, values);
      }
      if (node.right != null) {
         collect(node.right, values);
      }
   }
}

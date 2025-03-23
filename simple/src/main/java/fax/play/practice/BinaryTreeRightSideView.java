package fax.play.practice;

import java.util.ArrayList;
import java.util.List;

public class BinaryTreeRightSideView {

   public List<Integer> rightSideView(TreeNode root) {
      ArrayList<Integer> result = new ArrayList<>();
      if (root == null) {
         return result;
      }

      result.add(root.val);
      result.addAll(merge(rightSideView(root.left), rightSideView(root.right)));
      return result;
   }

   public List<Integer> merge(List<Integer> left, List<Integer> right) {
      if (right.size() >= left.size()) {
         return right;
      }
      for (int i = 0; i < left.size(); i++) {
         if (i >= right.size()) {
            right.add(left.get(i));
         }
      }
      return right;
   }
}

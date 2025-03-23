package fax.play.practice;

public class MaxDepthBinTree {

   public int maxDepth(TreeNode node) {
      if (node == null) {
         return 0;
      }

      int left = (node.left == null) ? 0 : maxDepth(node.left) + 1;
      int right = (node.right == null) ? 0 : maxDepth(node.right) + 1;
      return Math.max(Math.max(left, right), 1);
   }
}
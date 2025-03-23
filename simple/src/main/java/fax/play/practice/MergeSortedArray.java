package fax.play.practice;

public class MergeSortedArray {

   public void merge(int[] nums1, int m, int[] nums2, int n) {
      int[] merge = new int[n+m];
      int i = 0;
      int j = 0;

      for (int k=0; k<m+n; k++) {
         if (i == m) {
            // getting from num2
            merge[k] = nums2[j++];
         } else if (j == n) {
            // getting from num1
            merge[k] = nums1[i++];
         } else if (nums1[i] < nums2[j]) {
            // getting from num1
            merge[k] = nums1[i++];
         } else {
            // getting from num2
            merge[k] = nums2[j++];
         }
      }

      // now merge contains our result

      for (int k=0; k<m+n; k++) {
         nums1[k] = merge[k];
      }
   }
}

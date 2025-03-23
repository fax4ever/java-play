package fax.play.practice;

import java.util.ArrayList;
import java.util.List;

public class SummaryRanges {

   public List<String> summaryRanges(int[] nums) {
      ArrayList<String> result = new ArrayList<>();
      if (nums.length == 0) {
         return result;
      }

      Range range = new Range(nums[0]);
      for (int i=1; i<nums.length; i++) {
         boolean canBeAdded = range.addressNum(nums[i]);
         if (!canBeAdded) {
            result.add(range.toString());
            range = new Range(nums[i]);
         }
      }
      result.add(range.toString());

      return result;
   }

   static class Range {
      final int start;
      int end;

      Range(int start) {
         this.start = start;
         this.end = start;
      }

      boolean addressNum(int num) {
         if (num == end + 1) {
            end = num;
            return true;
         }
         return false;
      }

      @Override
      public String toString() {
         if (end == start) {
            return start + "";
         }
         return start + "->" + end;
      }
   }
}

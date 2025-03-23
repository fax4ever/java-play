package fax.play.practice;

import java.util.HashSet;
import java.util.Set;

public class LinkedListCycle {

   public class Solution {
      public boolean hasCycle(ListNode head) {
         if (head == null) {
            return false;
         }

         Set<ListNode> visited = new HashSet<>();
         visited.add(head);
         return hasCycle(head.next, visited);
      }

      public boolean hasCycle(ListNode head, Set<ListNode> visited) {
         if (head == null) {
            return false;
         }
         if (visited.contains(head)) {
            return true;
         }

         visited.add(head);
         return hasCycle(head.next, visited);
      }
   }

   static class ListNode {
      int val;
      ListNode next;
      ListNode(int x) {
         val = x;
         next = null;
      }
   }
}

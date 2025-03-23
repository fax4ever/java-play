package fax.play.practice;

import java.util.HashMap;
import java.util.Map;

public class RansomNote {

   public boolean canConstruct(String ransomNote, String magazine) {
      Map<Character, Integer> rCount = count(ransomNote);
      Map<Character, Integer> mCount = count(magazine);

      for (Map.Entry<Character, Integer> rLetter : rCount.entrySet()) {
         Integer availability = mCount.get(rLetter.getKey());
         if (availability == null) {
            return false;
         }
         if (availability < rLetter.getValue()) {
            return false;
         }
      }
      return true;
   }

   Map<Character, Integer> count(String text) {
      HashMap<Character, Integer> result = new HashMap<>();
      for (char c : text.toCharArray()) {
         result.putIfAbsent(c, 0);
         result.put(c, result.get(c) + 1);
      }
      return result;
   }
}

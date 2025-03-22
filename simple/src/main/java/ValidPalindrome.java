public class ValidPalindrome {

   public boolean isPalindrome(String s) {
      char[] target = removeNonAlphaChars(s).toCharArray();
      int i = 0;
      int j = target.length - 1;

      while (i < j) {
         if (target[i] != target[j]) {
            return false;
         }
         i++;
         j--;
      }
      return true;
   }

   public String removeNonAlphaChars(String origin) {
      StringBuilder builder = new StringBuilder();
      for (char c : origin.toUpperCase().toCharArray()) {
         if (c >= 'A' && c <= 'Z') {
            builder.append(c);
         } else if (c >= '0' && c <= '9') {
            builder.append(c);
         }
      }
      return builder.toString();
   }
}

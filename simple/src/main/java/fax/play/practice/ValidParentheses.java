package fax.play.practice;

import java.util.Stack;

public class ValidParentheses {

   public boolean isValid(String s) {
      Stack<Character> validationState = new Stack<>();
      for (char c : s.toCharArray()) {
         if ('(' == c || '[' == c || '{' == c) {
            validationState.push(c);
            continue;
         }

         if (validationState.isEmpty()) {
            return false;
         }

         Character pop = validationState.pop();
         if (c == ')') {
            if (pop != '(') {
               return false;
            }
         } else if (c == ']') {
            if (pop != '[') {
               return false;
            }
         } else if (c == '}') {
            if (pop != '{') {
               return false;
            }
         } else {
            return false;
         }
      }

      return validationState.isEmpty();
   }
}

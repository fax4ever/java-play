import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

public class ValidPalindromeTest {

   ValidPalindrome target = new ValidPalindrome();

   @Test
   public void test_removeNonAlphaChars() {
      String s = target.removeNonAlphaChars("A man, a plan, a canal: Panama");
      assertThat(s).isEqualTo("AMANAPLANACANALPANAMA");
   }
}

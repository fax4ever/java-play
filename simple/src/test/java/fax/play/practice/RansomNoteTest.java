package fax.play.practice;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

public class RansomNoteTest {

   RansomNote target = new RansomNote();

   @Test
   public void test() {
      boolean b = target.canConstruct("fihjjjjei", "hjibagacbhadfaefdjaeaebgi");
      assertThat(b).isFalse();
   }
}

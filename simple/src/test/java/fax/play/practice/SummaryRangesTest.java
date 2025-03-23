package fax.play.practice;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Test;

public class SummaryRangesTest {

   SummaryRanges target = new SummaryRanges();

   @Test
   public void test() {
      int[] value = {0,1,2,4,5,7};
      List<String> strings = target.summaryRanges(value);
      assertThat(strings).isNotNull();
   }

}

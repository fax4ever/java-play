package it.redhat.demo.three;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class ThreeLevelWrapperTest {

	private ThreeLevelWrapper testSubject;

	@Test
	public void test() {
		String myText = "ciao come va?";
		testSubject = new ThreeLevelWrapper( myText );
		assertEquals( myText, testSubject.getName() );
	}
}

package it.redhat.demo.two;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class TwoLevelWrapperTest {

	private TwoLevelWrapper testSubject;

	@Test
	public void test() {
		String myText = "ciao come va?";
		testSubject = new TwoLevelWrapper( myText );
		assertEquals( "[(" + myText + ")]", testSubject.getName() );
	}
}

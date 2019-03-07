package it.redhat.demo.one;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class OneLevelWrapperTest {

	private OneLevelWrapper testSubject;

	@Test
	public void test() {
		String myText = "ciao come va?";
		testSubject = new OneLevelWrapper( myText );
		assertEquals( myText, testSubject.getName() );
	}
}

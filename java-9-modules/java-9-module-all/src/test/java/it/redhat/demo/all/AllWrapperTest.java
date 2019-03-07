package it.redhat.demo.all;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class AllWrapperTest {

	private AllWrapper testSubject;

	@Test
	public void test() {
		String myText = "ciao come va?";
		testSubject = new AllWrapper( myText );
		assertEquals( myText, testSubject.getNameOne() );
		assertEquals( myText, testSubject.getNameTwo() );
		assertEquals( myText, testSubject.getNameThree() );
	}
}

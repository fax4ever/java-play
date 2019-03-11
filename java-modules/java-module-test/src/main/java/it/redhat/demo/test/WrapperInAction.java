package it.redhat.demo.test;

import it.redhat.demo.all.AllWrapper;

public class WrapperInAction {

	public static void main(final String[] args) {
		String myText = "Inside text";
		AllWrapper wrapper = new AllWrapper( myText );

		System.out.println( wrapper.getNameOne() );
		System.out.println( wrapper.getNameTwo() );
		System.out.println( wrapper.getNameThree() );
	}
}

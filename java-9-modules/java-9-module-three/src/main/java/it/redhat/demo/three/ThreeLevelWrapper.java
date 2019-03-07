package it.redhat.demo.three;

import it.redhat.demo.two.TwoLevelWrapper;

public class ThreeLevelWrapper {

	private final TwoLevelWrapper two;

	public ThreeLevelWrapper(String name) {
		this.two = new TwoLevelWrapper( name );
	}

	public String getName() {
		return two.getName();
	}
}

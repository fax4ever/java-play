package it.redhat.demo.all;

import it.redhat.demo.one.OneLevelWrapper;
import it.redhat.demo.three.ThreeLevelWrapper;
import it.redhat.demo.two.TwoLevelWrapper;

public class AllWrapper {

	private final OneLevelWrapper one;
	private final TwoLevelWrapper two;
	private final ThreeLevelWrapper three;

	public AllWrapper(String name) {
		one = new OneLevelWrapper( name );
		two = new TwoLevelWrapper( name );
		three = new ThreeLevelWrapper( name );
	}

	public String getNameOne() {
		return one.getName();
	}

	public String getNameTwo() {
		return two.getName();
	}

	public String getNameThree() {
		return three.getName();
	}
}

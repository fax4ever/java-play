package it.redhat.demo.two;

import it.redhat.demo.one.OneLevelWrapper;

public class TwoLevelWrapper {

	private final OneLevelWrapper one;

	public TwoLevelWrapper(String name) {
		this.one = new OneLevelWrapper( name );
	}

	public String getName() {
		return "[" + one.getName() + "]";
	}
}

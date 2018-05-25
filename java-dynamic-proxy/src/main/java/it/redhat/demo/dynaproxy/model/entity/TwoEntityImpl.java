package it.redhat.demo.dynaproxy.model.entity;

import it.redhat.demo.dynaproxy.model.driver.Driver;

public abstract class TwoEntityImpl extends DriverHolderEntity implements TwoEntity {

	public TwoEntityImpl(Driver driver) {
		super( driver );
	}

	@Override
	public String two() {
		return "two";
	}
}

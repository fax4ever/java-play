package it.redhat.demo.dynaproxy.model.entity;

import it.redhat.demo.dynaproxy.model.driver.Driver;

public abstract class OneEntityImpl extends DriverHolderEntity implements OneEntity {

	public OneEntityImpl(Driver driver) {
		super( driver );
	}

	@Override
	public String one() {
		return "one";
	}

}

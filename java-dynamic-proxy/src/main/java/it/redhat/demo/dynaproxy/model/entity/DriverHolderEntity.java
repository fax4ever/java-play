package it.redhat.demo.dynaproxy.model.entity;

import it.redhat.demo.dynaproxy.model.driver.Driver;

public class DriverHolderEntity {

	private final Driver driver;

	public DriverHolderEntity(Driver driver) {
		this.driver = driver;
	}

	public String sayHello() {
		return driver.sayHello();
	}

}

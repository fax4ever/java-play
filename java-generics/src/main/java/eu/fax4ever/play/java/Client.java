package eu.fax4ever.play.java;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Client {

	public void useGenerics() {
		List<Employee> employees = new ArrayList<>();
		List<? extends Person> people = new ArrayList<>();

		areEqual( employees, people );
	}

	public void useGenericsClasses() {
		List<Class<Employee>> employees = new ArrayList<>();
		List<Class<? extends Person>> people = new ArrayList<>();

		areEqual( employees, people );
	}

	public void useGenericInterface() {
		Map<String, Class<? extends Animal>> alfa = new HashMap<>();
		Map<String, Class<Person>> beta = new HashMap<>();

		areEqual( alfa, beta );
	}

	public void useGenericInterfaceWrapper() {
		Map<String, Class<? extends Animal>> alfa = new HashMap<>();
		Map<String, Class<Person>> beta = new HashMap<>();

		areEqual( alfa, new Wrapper<>( beta ) );
	}

	private <T> boolean areEqual(T alfa, T beta) {
		return alfa.equals( beta );
	}

	private <T> boolean areEqual(T alfa, Wrapper<T> beta) {
		return alfa.equals( beta.field );
	}
}

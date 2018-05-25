package it.redhat.demo.dynaproxy.javassist;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import it.redhat.demo.dynaproxy.model.driver.Driver;
import it.redhat.demo.dynaproxy.model.driver.DriverImpl;
import it.redhat.demo.dynaproxy.model.entity.DriverHolderEntity;
import it.redhat.demo.dynaproxy.model.entity.DynaEntity;
import javassist.util.proxy.MethodFilter;
import javassist.util.proxy.MethodHandler;
import javassist.util.proxy.ProxyFactory;

public class JavassistProxyFactory {

	public <G extends DynaEntity> G giveMe(Class<? extends G> kindOf, DriverImpl driver) {
		ProxyFactory proxyFactory = new ProxyFactory();
		proxyFactory.setSuperclass( kindOf );
		proxyFactory.setFilter( new EntityMethodFilter() );

		try {
			return (G) proxyFactory.create(
					new Class<?>[] { Driver.class },
					new Object[] { driver },
					new EntityMethodHandler()
			);
		}
		catch (NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
			throw new RuntimeException( e );
		}
	}

	private final class EntityMethodFilter implements MethodFilter {

		@Override
		public boolean isHandled(Method m) {
			return m.getName().equals( "translate" ) && m.getParameterTypes().length == 1 && m.getParameterTypes()[0] == String.class;
		}
	}

	private final class EntityMethodHandler implements MethodHandler {

		@Override
		public Object invoke(Object self, Method thisMethod, Method proceed, Object[] args) throws Throwable {
			String hello = ( (DriverHolderEntity) self ).sayHello();
			String body = (String) args[0];

			return hello + " " + body;
		}
	}

}

package it.redhat.demo.dynaproxy.javassist;

import static net.bytebuddy.implementation.MethodDelegation.to;
import static net.bytebuddy.matcher.ElementMatchers.named;
import static net.bytebuddy.matcher.ElementMatchers.takesArguments;

import java.lang.reflect.InvocationTargetException;

import it.redhat.demo.dynaproxy.model.driver.Driver;
import it.redhat.demo.dynaproxy.model.driver.DriverImpl;
import it.redhat.demo.dynaproxy.model.entity.DriverHolderEntity;
import it.redhat.demo.dynaproxy.model.entity.DynaEntity;
import net.bytebuddy.ByteBuddy;
import net.bytebuddy.implementation.bind.annotation.Argument;
import net.bytebuddy.implementation.bind.annotation.This;

public class ByteBuddyProxyFactory {

	public <G extends DynaEntity> G giveMe(Class<? extends G> kindOf, DriverImpl driver) {
		Class<? extends G> dynamicType = new ByteBuddy()
				.subclass( kindOf )
				.method( named( "translate" ).and( takesArguments( 1 ) ).and( takesArguments( String.class ) ) )
				.intercept( to( new EntityInterceptor() ) )
				.make().load( getClass().getClassLoader() ).getLoaded();

		try {
			return dynamicType.getConstructor( Driver.class ).newInstance( driver );
		}
		catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
			throw new RuntimeException( e );
		}
	}

	public static class EntityInterceptor {
		public String translate(@This DriverHolderEntity proxy, @Argument(0) String original) {
			return proxy.sayHello() + " " + original;
		}
	}
}

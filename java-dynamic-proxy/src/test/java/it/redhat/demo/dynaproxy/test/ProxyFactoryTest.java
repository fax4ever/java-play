package it.redhat.demo.dynaproxy.test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import it.redhat.demo.dynaproxy.javassist.ByteBuddyProxyFactory;
import it.redhat.demo.dynaproxy.javassist.JavassistProxyFactory;
import it.redhat.demo.dynaproxy.model.driver.DriverImpl;
import it.redhat.demo.dynaproxy.model.entity.OneEntity;
import it.redhat.demo.dynaproxy.model.entity.OneEntityImpl;
import it.redhat.demo.dynaproxy.model.entity.TwoEntity;
import it.redhat.demo.dynaproxy.model.entity.TwoEntityImpl;

public class ProxyFactoryTest {

	@Test
	public void testJavaAssistProxies_dyna() {
		JavassistProxyFactory testSubject = new JavassistProxyFactory();
		DriverImpl driver = new DriverImpl();

		OneEntity oneEntity = testSubject.giveMe( OneEntityImpl.class, driver );
		TwoEntity twoEntity = testSubject.giveMe( TwoEntityImpl.class, driver );

		assertEquals( "ciao Fabio", oneEntity.translate( "Fabio" ) );
		assertEquals( "ciao Fabio", twoEntity.translate( "Fabio" ) );

		assertEquals( "one", oneEntity.one() );
		assertEquals( "two", twoEntity.two() );
	}

	@Test
	public void testByteBuddyProxies_dyna() {
		ByteBuddyProxyFactory testSubject = new ByteBuddyProxyFactory();
		DriverImpl driver = new DriverImpl();

		OneEntity oneEntity = testSubject.giveMe( OneEntityImpl.class, driver );
		TwoEntity twoEntity = testSubject.giveMe( TwoEntityImpl.class, driver );

		assertEquals( "ciao Fabio", oneEntity.translate( "Fabio" ) );
		assertEquals( "ciao Fabio", twoEntity.translate( "Fabio" ) );

		assertEquals( "one", oneEntity.one() );
		assertEquals( "two", twoEntity.two() );
	}
}

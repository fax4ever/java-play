package it.redhat.demo;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * @author Fabio Massimo Ercoli (C) 2017 Red Hat Inc.
 */
@RunWith(JUnit4.class)
public class RegExTest {

	private final static String TARGET_STRING_1 = "Error during command org.jbpm.process.core.async.AsyncSignalEventCommand error message [it.redhat.demo.exception-after-safe-point:49 - Rest:2] -- org.jbpm.workflow.instance.WorkflowRuntimeException: [it.redhat.demo.exception-after-safe-point:49 - RuntimeException:3] -- null";
	private final static String TARGET_STRING_2 = "Unexpected error during processing [it.redhat.demo.exception-after-start:50 - RuntimeException:2] -- null";

	@Test
	public void test_string1() {

		Pattern pattern = Pattern.compile( RegexDef.REG_EX );
		Matcher matcher = pattern.matcher( TARGET_STRING_1 );

		assertTrue(matcher.find());
		assertEquals( "it.redhat.demo.exception-after-safe-point:49 - Rest:2", matcher.group( 1 ));


	}

	@Test
	public void test_string2() {

		Pattern pattern = Pattern.compile( RegexDef.REG_EX );
		Matcher matcher = pattern.matcher( TARGET_STRING_2 );

		assertTrue(matcher.find());
		assertEquals( "it.redhat.demo.exception-after-start:50 - RuntimeException:2", matcher.group( 1 ));

	}

}

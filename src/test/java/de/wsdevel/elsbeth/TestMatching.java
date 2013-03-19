package de.wsdevel.elsbeth;

import java.io.File;

import junit.framework.TestCase;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

/**
 * Created on 15.08.2008.
 * 
 * for project: Elsbeth
 * 
 * @author <a href="mailto:sweiss@weissundschmidt.de">Sebastian A. Weiss - Weiss
 *         und Schmidt, Mediale Systeme GbR</a>
 * @version $Author: $ -- $Revision: $ -- $Date: $
 * 
 * <br>
 *          (c) 2007, Weiss und Schmidt, Mediale Systeme GbR - All rights
 *          reserved.
 * 
 */
public class TestMatching extends TestCase {

    /**
     * {@link DialogAgent} COMMENT.
     */
    private final DialogAgent da;

    /**
     * COMMENT.
     * 
     * @param name
     */
    public TestMatching(final String name) {
	super(name);
	BasicConfigurator.configure();
	Logger.getRootLogger().setLevel(Level.DEBUG);
	this.da = new DialogAgent(null);
	this.da.load(new File("src/test/aiml/test_matching.aiml.xml"), false);
    }

    public final void testMultipleWildcards() {
	assertEquals(
		"wrong response",
		"H @@ LKJASD ASLKFDS @@ J @@ LKDF IS ASD @@ K @@ HSADKAS @@ L @@ KJDSAH DA @@",
		this.da.getEvaluationResultForRequest(
			"H lkjasd aslkfds J lkdf is asd K hsadkas L kjdsah da")
			.getText());
    }

    /**
     * COMMENT.
     */
    public final void testStarWildcard() {
	assertTrue("wrong response", !"A @@B@@ RESULT".equals(this.da
		.getEvaluationResultForRequest("A B").getText()));
	// assertEquals("wrong response", "A @@ B C D @@ RESULT", this.da
	// .getResponseForRequest("A B C D"));
	assertEquals(
		"wrong response",
		"* RESULT",
		this.da.getEvaluationResultForRequest(
			"kjhsda asdljkjasd asdlkjasd sadlkas dslkjdas")
			.getText());
	assertEquals(
		"wrong response",
		"* X Y Z RESULT",
		this.da.getEvaluationResultForRequest(
			"lksda asposd sakwqn doidqw jd opsda X Y Z").getText());
	assertEquals("wrong response", "* X @@ AA BB CC @@ Z RESULT", this.da
		.getEvaluationResultForRequest("jsadsdaj salkd kasd X aa bb cc Z")
		.getText());
	assertTrue(
		"should have no response!",
		!"* X * Z RESULT".equals(this.da.getEvaluationResultForRequest(
			"jsadsdaj salkd kasd X Z").getText()));
    }

    /**
     * COMMENT.
     */
    public final void testThat() {
	// trigger first response
	assertEquals("wrong response", "THAT FIRST INPUT THAT", this.da
		.getEvaluationResultForRequest("THAT FIRST INPUT").getText());
	// trigger second response depending on that
	assertEquals("wrong response", "I KNOW WHAT THAT MEANS", this.da
		.getEvaluationResultForRequest("THAT SECOND INPUT").getText());
    }

    /**
     * COMMENT.
     */
    public final void testUnderscoreWildcard() {
	assertEquals("wrong response", "A _ RESULT", this.da
		.getEvaluationResultForRequest("A X").getText());
	assertEquals("wrong response", "A _ RESULT", this.da
		.getEvaluationResultForRequest("A B").getText());
	assertEquals("wrong response", "A _ RESULT", this.da
		.getEvaluationResultForRequest("A kjdajsf lksdkas").getText());
	assertEquals("wrong response", "A _ Z RESULT", this.da
		.getEvaluationResultForRequest("A Y Z").getText());
	assertEquals("wrong response", "A _ Z RESULT", this.da
		.getEvaluationResultForRequest("A Y lkjle Z").getText());
    }

    /**
     * COMMENT.
     */
    public final void testWithoutWildcards() {
	assertEquals("wrong response", "A RESULT", this.da
		.getEvaluationResultForRequest("A").getText());
	// next pattern may not match due to underscore wildcard in aiml.
	assertTrue("wrong response", !"A B RESULT".equals(this.da
		.getEvaluationResultForRequest("A B").getText()));
	assertEquals("wrong response", "D E F RESULT", this.da
		.getEvaluationResultForRequest("D E F").getText());
    }

    // SEBASTIAN test topics!!

}
//
// $Log: $
//

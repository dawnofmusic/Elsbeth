package de.wsdevel.elsbeth.evaluators;

import java.io.File;
import java.util.LinkedList;

import junit.framework.TestCase;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import de.wsdevel.elsbeth.DialogAgent;

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
public class TestEvaluators extends TestCase {

    private static final String BOT_PROPERTY_1_NAME = "BOT_PROPERTY_1_NAME";
    private static final String BOT_PROPERTY_1_VALUE = "BOT_PROPERTY_1_VALUE";
    private static final String FALSE = "FALSE";
    /**
     * {@link Log} the logger.
     */
    private static final Log LOG = LogFactory.getLog(TestEvaluators.class);
    private static final String PREDICATE_1_NAME = "PREDICATE_1_NAME";
    private static final String PREDICATE_1_VALUE = "PREDICATE_1_VALUE";
    private static final String PREDICATE_DUMMY_VALUE = "DUMMY_VALUE";
    private static final String PREDICATE_FALSE = "PREDICATE_FALSE";
    @SuppressWarnings("unused")
    private static final String PREDICATE_SET_NAME = "PREDICATE_SET_NAME";
    private static final String PREDICATE_TRUE = "PREDICATE_TRUE";
    private static final String TRUE = "TRUE";
    private final DialogAgent da;

    /**
     * COMMENT.
     * 
     * @param name
     */
    public TestEvaluators(final String name) {
	super(name);
	BasicConfigurator.configure();
	Logger.getRootLogger().setLevel(Level.ERROR);
	Logger.getLogger(TestEvaluators.class).setLevel(Level.DEBUG);
	this.da = new DialogAgent(null);
	this.da.load(new File("src/test/aiml/test_aiml_tags.aiml.xml"), false);

	this.da.setPredicateValue(TestEvaluators.PREDICATE_1_NAME,
		TestEvaluators.PREDICATE_1_VALUE);
	this.da.setPredicateValue(TestEvaluators.PREDICATE_TRUE,
		TestEvaluators.TRUE);
	this.da.setPredicateValue(TestEvaluators.PREDICATE_FALSE,
		TestEvaluators.FALSE);
	this.da.getBotProperties().put(TestEvaluators.BOT_PROPERTY_1_NAME,
		TestEvaluators.BOT_PROPERTY_1_VALUE);

    }

    public final void testBlockCondition() {
	final String response = this.da
		.getResponseForRequest("BLOCK CONDITION").getText();
	assertEquals("invalid response!", "TRUE", response);
    }

    public final void testBot() {
	final String response = this.da.getResponseForRequest("BOT").getText();
	assertEquals("invalid value!", TestEvaluators.BOT_PROPERTY_1_VALUE,
		response);
    }

    public final void testDate() {
	final String response = this.da.getResponseForRequest("DATE").getText();
	if (TestEvaluators.LOG.isDebugEnabled()) {
	    TestEvaluators.LOG.debug("date response: " + response);
	}
	assertTrue("date should not be empty!", !response.equals(""));
    }

    public final void testFormal() {
	final String response = this.da.getResponseForRequest("FORMAL")
		.getText();
	assertEquals("all words should be capatalized!", "This Is A House",
		response);
    }

    // SEBASTIAN implement
    // public final void testGender() {
    // final String response = this.da.getResponseForRequest("GENDER")
    // .getText();
    // assertEquals("invalid response!",
    // "He was listening. She was listening.", response);
    // }

    // SEBASTIAN implement
    // public final void testGenderSC() {
    // final String response = this.da.getResponseForRequest("GENDER SHE")
    // .getText();
    // assertEquals("invalid response!", "He", response);
    // }

    public final void testGet() {
	final String response = this.da.getResponseForRequest("GET").getText();
	assertEquals("invalid value!", TestEvaluators.PREDICATE_1_VALUE,
		response);
    }

    public final void testId() {
	final String response = this.da.getResponseForRequest("ID").getText();
	if (TestEvaluators.LOG.isDebugEnabled()) {
	    TestEvaluators.LOG.debug("id response: " + response);
	}
	assertTrue("id should not be empty!", !response.equals(""));
    }

    public final void testJavascript() {
	final String response = this.da.getResponseForRequest("JAVASCRIPT")
		.getText();
	// return result script!
	assertEquals("invalid response!", "", response);
    }

    public final void testLearn() {
	String response = this.da.getResponseForRequest("LEARN").getText();
	assertEquals("reponse should be empty!", null, response);
	response = this.da.getResponseForRequest("HAVE YOU LEARNED ANYTHING")
		.getText();
	assertEquals("invalid response!", "Yes, I did! @@@IDENTIFIER@@@",
		response);
    }

    public final void testLowercase() {
	final String response = this.da.getResponseForRequest("LOWERCASE")
		.getText();
	assertEquals("all characters should be lowercase!",
		"should be lowercase", response);
    }

    // SEBASTIAN implement
    // public final void testPerson() {
    // final String response = this.da.getResponseForRequest("PERSON")
    // .getText();
    // assertEquals("invalid response!",
    // "You were listening. I was listening.", response);
    // }

    // SEBASTIAN implement
    // public final void testPerson2() {
    // final String response = this.da.getResponseForRequest("PERSON2")
    // .getText();
    // assertEquals("invalid response!", "He was listening. I was listening.",
    // response);
    // }

    // SEBASTIAN implement
    // public final void testPerson2SC() {
    // final String response = this.da.getResponseForRequest("PERSON2 I")
    // .getText();
    // assertEquals("invalid response!", "He", response);
    // }

    // SEBASTIAN implement
    // public final void testPersonSC() {
    // final String response = this.da.getResponseForRequest("PERSON YOU")
    // .getText();
    // assertEquals("invalid response!", "I", response);
    // }

    public final void testRandom() {
	final LinkedList<String> validResponse = new LinkedList<String>();
	validResponse.add("ITEM 1");
	validResponse.add("ITEM 2");
	validResponse.add("ITEM 3");

	String response = this.da.getResponseForRequest("RANDOM").getText();
	assertTrue("invalid response!", validResponse.contains(response));
	validResponse.remove(response);
	LOG.debug("valid responses " + validResponse);

	response = this.da.getResponseForRequest("RANDOM").getText();
	assertTrue("invalid response!", validResponse.contains(response));
	validResponse.remove(response);
	LOG.debug("valid responses " + validResponse);

	response = this.da.getResponseForRequest("RANDOM").getText();
	assertTrue("invalid response!", validResponse.contains(response));
	validResponse.remove(response);
	LOG.debug("valid responses " + validResponse);

	validResponse.add("ITEM 1");
	validResponse.add("ITEM 2");
	validResponse.add("ITEM 3");
	LOG.debug("valid responses " + validResponse);

	response = this.da.getResponseForRequest("RANDOM").getText();
	assertTrue("invalid response!", validResponse.contains(response));
	validResponse.remove(response);
	LOG.debug("valid responses " + validResponse);
    }

    public final void testSentence() {
	final String response = this.da.getResponseForRequest("SENTENCE")
		.getText();
	assertEquals("first word should be capatalized!",
		"This is an apple. This is a banana.", response);
    }

    public final void testSet() {
	EvaluationResult response = this.da.getResponseForRequest("SET 1");
	assertEquals("response should be 1!", "1", response.getText());

	// SEBASTIAN implement setting of real predicate values
	// SCENEJO
	// assertEquals("should contain one operation", 1, response
	// .getPredicateOperations().size());
	// PredicateOperation op = response.getPredicateOperations().get(0);
	// assertEquals(
	// "set should be 1!",
	// "1",
	// Operator.getOperatorForString(op.getOperator()).doIt(
	// this.da.getPredicateValue(op.getPredicateName()),
	// op.getValue()));

	response = this.da.getResponseForRequest("SET 2");
	assertEquals("response should be 2!", "2", response.getText());

	// SCENEJO
	// assertEquals("should contain one operation", 1, response
	// .getPredicateOperations().size());
	// op = response.getPredicateOperations().get(0);
	// assertEquals(
	// "set should be 2!",
	// "2",
	// Operator.getOperatorForString(op.getOperator()).doIt(
	// this.da.getPredicateValue(op.getPredicateName()),
	// op.getValue()));

	response = this.da.getResponseForRequest("SET 1");
	assertEquals("response should be 1!", "1", response.getText());

	// SCENEJO
	// assertEquals("should contain one operation", 1, response
	// .getPredicateOperations().size());
	// op = response.getPredicateOperations().get(0);
	// assertEquals(
	// "set should be 1!",
	// "1",
	// Operator.getOperatorForString(op.getOperator()).doIt(
	// this.da.getPredicateValue(op.getPredicateName()),
	// op.getValue()));
    }

    public final void testSize() {
	final String response = this.da.getResponseForRequest("SIZE").getText();
	if (TestEvaluators.LOG.isDebugEnabled()) {
	    TestEvaluators.LOG.debug("size response: " + response);
	}
	assertTrue("size should be greater than zero!",
		Integer.parseInt(response) > 0);
    }

    public final void testSR() {
	final String response = this.da.getResponseForRequest("SR REQUEST")
		.getText();
	assertEquals("invalid response!", "RESPONSE", response);
    }

    public final void testSRAI() {
	final String response = this.da.getResponseForRequest("SRAI").getText();
	assertEquals("invalid response!", "SRAI RESULT", response);
    }

    public final void testStar() {
	final String response = this.da.getResponseForRequest(
		"ZYXXYZ STAR XYZZYX").getText();
	assertEquals("invalid response!", "XYZZYX ZYXXYZ", response);
    }

    public final void testSystem() {
	final String response = this.da.getResponseForRequest("SYSTEM")
		.getText();
	// return result of system call!
	assertEquals("invalid response!", "", response);
    }

    public final void testThink() {
	final EvaluationResult response = this.da
		.getResponseForRequest("THINK");
	assertEquals("invalid response!", null, response.getText());

	// SCENEJO
	// assertEquals("should contain one operation", 1, response
	// .getPredicateOperations().size());
	// final PredicateOperation op =
	// response.getPredicateOperations().get(0);
	// assertEquals(
	// "invalid predicate value!",
	// TestEvaluators.PREDICATE_DUMMY_VALUE,
	// Operator.getOperatorForString(op.getOperator()).doIt(
	// this.da.getPredicateValue(op.getPredicateName()),
	// op.getValue()));
    }

    public final void testUppercase() {
	final String response = this.da.getResponseForRequest("UPPERCASE")
		.getText();
	assertEquals("all characters should be uppercase!",
		"SHOULD BE UPPERCASE", response);
    }

    public final void testVersion() {
	final String response = this.da.getResponseForRequest("VERSION")
		.getText();
	assertEquals("bad version!", DialogAgent.VERSION, response);
    }

}
//
// $Log: $
//

package de.wsdevel.elsbeth.evaluators;

import junit.framework.TestCase;

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
public class TestStringHelper extends TestCase {

    /**
     * {@link String} COMMENT.
     */
    private static final String STRING_WITH_TAB_LINE_BREAK_AND_CARRIAGE_RETURN = "a\nb    \t \rc";

    /**
     * COMMENT.
     * 
     * @param name
     */
    public TestStringHelper(final String name) {
	super(name);
    }

    /**
     * COMMENT.
     */
    public final void testRemoveUnecessaryWhitespaceFromString() {
	assertEquals(
		"string is not empty but should be!",
		"a b c",
		StringHelper
			.removeUnecessaryWhitespaceFromString(TestStringHelper.STRING_WITH_TAB_LINE_BREAK_AND_CARRIAGE_RETURN));
    }
}
//
// $Log: $
//

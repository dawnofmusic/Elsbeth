package de.wsdevel.elsbeth.inputnormalization.patternfitting;

/**
 * InvalidCharacters created on 02.05.2009. for project: Elsbeth
 * 
 * @author <a href="mailto:sweiss@scenejo.org">Sebastian A. Weiss -
 *         scenejo.org</a>
 * @version $Author: $ -- $Revision: $ -- $Date: $
 * 
 * <br>
 *          (c) 2008, scenejo.org - All rights reserved. Scenejo - An
 *          Interactive Storytelling Framework
 */
public class InvalidCharacters {

    /**
     * {@link String} COMMENT.
     */
    @SuppressWarnings("unused")
    private static final String EMPTY_STRING = "";

    // /**
    // * {@link String[]} COMMENT.
    // *
    // * SEBASTIAN '*' is a valid wildcard!
    // */
    // private static String[] invalidCharactersToBeRemoved = new String[] {
    // "'",
    // "^", "$", "%", "&", "=", "`", "\\", "~", "\"", "#" };

    /**
     * {@link String[]} COMMENT.
     * 
     * SEBASTIAN '_' is a valid wildcard!
     */
    private static String[] invalidCharactersToBeReplacedBySpace = new String[] {
	    ".", ",", ";", ":", "-", "!", "/", "(", ")", "?", "{", "[", "]",
	    "}", "+", "<", ">", "|", "'", "^", "$", "%", "&", "=", "`", "\\",
	    "~", "\"", "#" };

    /**
     * {@link String} COMMENT.
     */
    private static final String SPACE = " ";

    /**
     * @param value
     *            {@link String}
     * @return {@link String}
     */
    public static String clear(final String value) {
	String returnVal = value.trim();
	// for (final String tbr :
	// InvalidCharacters.invalidCharactersToBeRemoved) {
	// returnVal = returnVal.replace(tbr, InvalidCharacters.EMPTY_STRING);
	// }
	for (final String tbr : InvalidCharacters.invalidCharactersToBeReplacedBySpace) {
	    returnVal = returnVal.replace(tbr, InvalidCharacters.SPACE);
	}
	return returnVal;
    }

}
//
// $Log: $
//

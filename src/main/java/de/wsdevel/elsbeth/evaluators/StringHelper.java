package de.wsdevel.elsbeth.evaluators;

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
public final class StringHelper {

    /**
     * COMMENT.
     * 
     * @param result
     *            {@link String}
     * @return {@link String}
     */
    public static String appendSpaceIfNecessary(final String result) {
	if (result == null) {
	    return "";
	}
	if ((result.length() > 0) && !result.endsWith(" ")) {
	    return result + " ";
	}
	return result;
    }

    /**
     * COMMENT.
     * 
     * @param value
     *            {@link String} to be cleaned up
     * @return {@link String}
     */
    public static String removeUnecessaryWhitespaceFromString(final String value) {
	final String step1 = value.replaceAll("\\n", " ").replaceAll("\\r", "")
		.replaceAll("\\t", "");

	String step2 = "";
	final String[] split = step1.split("\\s");
	int succeededParts = 0;
	for (final String element : split) {
	    final String trim = element.trim();
	    if (!trim.equals("")) {
		if (succeededParts > 0) {
		    step2 += " ";
		}
		step2 += trim;
		succeededParts++;
	    }
	}
	return step2;
    }

    /**
     * Hidden constructor.
     */
    private StringHelper() {
    }
}
//
// $Log: $
//

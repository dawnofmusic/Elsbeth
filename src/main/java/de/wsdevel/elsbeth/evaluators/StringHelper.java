package de.wsdevel.elsbeth.evaluators;

/**
 * Created on 15.08.2008 for project: Elsbeth
 * 
 * (c) 2007, Sebastian A. Weiss - All rights reserved.
 * 
 * @author <a href="mailto:post@sebastian-weiss.de">Sebastian A. Weiss</a>
 * @version $Author: $ -- $Revision: $ -- $Date: $
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

	final StringBuffer step2 = new StringBuffer();
	final String[] split = step1.split("\\s");
	int succeededParts = 0;
	for (final String element : split) {
	    final String trim = element.trim();
	    if (!trim.equals("")) {
		if (succeededParts > 0) {
		    step2.append(" ");
		}
		step2.append(trim);
		succeededParts++;
	    }
	}
	return step2.toString();
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

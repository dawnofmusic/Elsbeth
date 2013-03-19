package de.wsdevel.elsbeth.inputnormalization.substitution;

import java.util.ArrayList;
import java.util.Collection;

import de.wsdevel.elsbeth.inputnormalization.InputNormalizer;

/**
 * UK_US_WebAddressSubstituionNormalizer created on 22.10.2011 in project
 * Elsbeth.
 * 
 * (c) 2004-2011 scenejo.org - All rights reserved. Scenejo - An Interactive
 * Storytelling Framework
 * 
 * @author <a href="mailto:sebastian@scenejo.org">Sebastian A. Weiss,
 *         scenejo.org</a>
 * @version $Author: Sebastian A. Weiß $ -- $Revision: $ -- $Date: $
 * 
 */
public class UK_US_WebAddressSubstitutionNormalizer implements InputNormalizer {

    /**
     * ${InputNormalizer} INSTANCE. The instance to be used.
     */
    public static final InputNormalizer INSTANCE = new UK_US_WebAddressSubstitutionNormalizer();

    /**
     * ${String} HTTP_PREFIX
     */
    private static final String HTTP_PREFIX = "HTTP://";

    /**
     * @param input
     *            {@link String}[]
     * @return {@link String}[]
     * @see de.wsdevel.elsbeth.inputnormalization.InputNormalizer#normalizeInput(java.lang.String[])
     */
    public static Collection<String> normalizeInput(
	    final Collection<String> input, final String wordForDot,
	    final String wordForMinus, final String wordForPort) {
	final ArrayList<String> output = new ArrayList<String>();
	outer: for (String part : input) {
	    if (part.toUpperCase().startsWith(
		    UK_US_WebAddressSubstitutionNormalizer.HTTP_PREFIX)) {
		output.add("HTTP");
		part = part
			.substring(UK_US_WebAddressSubstitutionNormalizer.HTTP_PREFIX
				.length());
		final String[] split = part.split("\\.");
		for (int i = 0; i < split.length; i++) {
		    final String e = split[i];
		    if (e.contains(":")) {
			final String[] split2 = e.split("\\:");
			output.add(split2[0]);
			output.add(wordForPort);
			output.add(split2[1]);
		    } else {
			if (e.contains("-")) {
			    final String[] split2 = e.split("\\-");
			    for (int j = 0; j < split2.length; j++) {
				output.add(split2[j]);
				if (j < split2.length - 1) {
				    output.add(wordForMinus);
				}
			    }
			} else {
			    output.add(e);
			}
			if (i < split.length - 1) {
			    output.add(wordForDot);
			}
		    }
		}
		continue outer;
	    }
	    output.add(part);
	}
	return output;
    }

    /**
     * @param input
     *            {@link String}[]
     * @return {@link String}[]
     * @see de.wsdevel.elsbeth.inputnormalization.InputNormalizer#normalizeInput(java.lang.String[])
     */
    @Override
    public Collection<String> normalizeInput(final Collection<String> input) {
	return normalizeInput(input, "DOT", "MINUS", "PORT");
    }

}

package de.wsdevel.elsbeth.inputnormalization.substitution;

import java.util.ArrayList;
import java.util.Collection;

/**
 * SubstitutionNormalizer created on 22.10.2011 in project Elsbeth.
 * 
 * (c) 2004-2011 scenejo.org - All rights reserved. Scenejo - An Interactive
 * Storytelling Framework
 * 
 * @author <a href="mailto:sebastian@scenejo.org">Sebastian A. Weiss,
 *         scenejo.org</a>
 * @version $Author: Sebastian A. Weiß $ -- $Revision: $ -- $Date: $
 * 
 */
public class SubstitutionNormalizerHelper {

    /**
     * @see de.wsdevel.elsbeth.inputnormalization.InputNormalizer#normalizeInput(java.lang.String)
     */
    public static Collection<String> normalizeInput(
	    final Substitution[] substitutions, final Collection<String> input) {
	final ArrayList<String> output = new ArrayList<String>();
	outer: for (final String part : input) {
	    for (final Substitution subs : substitutions) {
		if (part.toUpperCase().equals(subs.getPattern().toUpperCase())) {
		    for (final String sub : subs.getSubstitution()) {
			output.add(sub);
		    }
		    continue outer;
		}
	    }
	    output.add(part);
	}
	return output;
    }

}

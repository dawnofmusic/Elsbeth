package de.wsdevel.elsbeth.inputnormalization.patternfitting;

import java.util.Collection;
import java.util.LinkedList;

import de.wsdevel.elsbeth.inputnormalization.InputNormalizer;

/**
 * PatterFittingInputNormalizer created on 22.10.2011 in project Elsbeth.
 * 
 * (c) 2004-2011 scenejo.org - All rights reserved. Scenejo - An Interactive
 * Storytelling Framework
 * 
 * @author <a href="mailto:sebastian@scenejo.org">Sebastian A. Weiss,
 *         scenejo.org</a>
 * @version $Author: Sebastian A. Weiß $ -- $Revision: $ -- $Date: $
 * 
 */
public class PatterFittingInputNormalizer implements InputNormalizer {

    /**
     * ${InputNormalizer} INSTANCE. The instance to be used.
     */
    public final static InputNormalizer INSTANCE = new PatterFittingInputNormalizer();

    /**
     * @param input
     *            {@link Collection}< {@link String}>
     * @return {@link Collection}< {@link String}>
     * @see de.wsdevel.elsbeth.inputnormalization.InputNormalizer#normalizeInput(java.util.Collection)
     */
    public Collection<String> normalizeInput(final Collection<String> input) {
	final LinkedList<String> result = new LinkedList<String>();
	for (final String element : input) {
	    final String trim = InvalidCharacters.clear(element.trim()).trim();
	    if (!trim.equals("")) {
		result.add(trim.toUpperCase().intern());
	    }
	}
	return result;
    }

}

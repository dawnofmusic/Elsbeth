/**
 * 
 */
package de.wsdevel.elsbeth.inputnormalization;

import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Locale;

import de.wsdevel.elsbeth.Brain;
import de.wsdevel.elsbeth.inputnormalization.patternfitting.PatterFittingInputNormalizer;
import de.wsdevel.elsbeth.inputnormalization.sentencesplitting.SentenceSplittingInputNormalizer;
import de.wsdevel.elsbeth.inputnormalization.substitution.SubstitutionNormalizers;

/**
 * InputNormalization created on 22.10.2011 in project Elsbeth.
 * 
 * (c) 2004-2011 scenejo.org - All rights reserved. Scenejo - An Interactive
 * Storytelling Framework
 * 
 * @author <a href="mailto:sebastian@scenejo.org">Sebastian A. Weiss,
 *         scenejo.org</a>
 * @version $Author: Sebastian A. Weiß $ -- $Revision: $ -- $Date: $
 * 
 */
public final class InputNormalizationService {

    /**
     * ${InputNormalizer} chain
     */
    private final InputNormalizer chain;

    /**
     * Hidden constructor.
     */
    public InputNormalizationService(final Locale locale) {
	this.chain = new ChainedInputNormalizer(new ChainedInputNormalizer(
		SubstitutionNormalizers.createChain(locale),
		SentenceSplittingInputNormalizer.INSTANCE),
		PatterFittingInputNormalizer.INSTANCE);
    }

    /**
     * COMMENT.
     * 
     * @param pattern
     *            {@link String}
     * @return {@link Collection}< {@link String}>
     */
    public Collection<String> getWordsForPattern(final String pattern) {
	if ((pattern == null) || pattern.trim().equals("")) {
	    final LinkedList<String> result = new LinkedList<String>();
	    result.add(Brain.STAR);
	    return result;
	}
	return this.chain.normalizeInput(Arrays.asList(pattern.split("\\s")));
    }

}

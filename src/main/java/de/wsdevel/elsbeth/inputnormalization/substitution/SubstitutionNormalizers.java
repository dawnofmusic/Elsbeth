package de.wsdevel.elsbeth.inputnormalization.substitution;

import java.util.HashMap;
import java.util.Locale;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import de.wsdevel.elsbeth.inputnormalization.ChainedInputNormalizer;
import de.wsdevel.elsbeth.inputnormalization.DoNothingInputNormalizer;
import de.wsdevel.elsbeth.inputnormalization.InputNormalizer;

/**
 * SubstitutionNormalizers created on 22.10.2011 in project Elsbeth.
 * 
 * (c) 2004-2011 scenejo.org - All rights reserved. Scenejo - An Interactive
 * Storytelling Framework
 * 
 * @author <a href="mailto:sebastian@scenejo.org">Sebastian A. Weiss,
 *         scenejo.org</a>
 * @version $Author: Sebastian A. Weiß $ -- $Revision: $ -- $Date: $
 * 
 */
public final class SubstitutionNormalizers {

    /**
     * ${Log} LOG. The logger.
     */
    private static final Log LOG = LogFactory
	    .getLog(SubstitutionNormalizers.class);

    /**
     * ${HashMap<Locale,InputNormalizer>} substitutionNormalizers
     */
    private static final HashMap<Locale, SubstitutionNormalizerSet> substitutionNormalizers = new HashMap<Locale, SubstitutionNormalizerSet>();

    static {
	// SEBASTIAN create file extension normalizer!
	SubstitutionNormalizers.substitutionNormalizers.put(Locale.GERMANY,
		new SubstitutionNormalizerSet(
			new DE_AbbreviationSubstitutionNormalizer(),
			DE_WebAddressSubstitutionNormalizer.INSTANCE,
			DoNothingInputNormalizer.INSTANCE));
	SubstitutionNormalizers.substitutionNormalizers.put(Locale.UK,
		new SubstitutionNormalizerSet(
			new UK_AbbreviationSubstitutionNormalizer(),
			UK_US_WebAddressSubstitutionNormalizer.INSTANCE,
			DoNothingInputNormalizer.INSTANCE));
	SubstitutionNormalizers.substitutionNormalizers.put(Locale.US,
		new SubstitutionNormalizerSet(
			new US_AbbreviationSubstitutionNormalizer(),
			UK_US_WebAddressSubstitutionNormalizer.INSTANCE,
			DoNothingInputNormalizer.INSTANCE));
    }

    /**
     * @param locale
     *            {@link Locale}
     * @return {@link InputNormalizer}
     */
    public static InputNormalizer createChain(final Locale locale) {
	if (locale != null) {
	    final SubstitutionNormalizerSet substitutionNormalizerSet = SubstitutionNormalizers.substitutionNormalizers
		    .get(locale);
	    if (substitutionNormalizerSet != null) {
		return new ChainedInputNormalizer(new ChainedInputNormalizer(
			substitutionNormalizerSet.getAbbreviationNormalizer(),
			substitutionNormalizerSet.getWebAddressNormalizer()),
			substitutionNormalizerSet
				.getFilenamExtensionNormalizer());
	    }
	    if (SubstitutionNormalizers.LOG.isWarnEnabled()) {
		SubstitutionNormalizers.LOG
			.warn("Could not find substitution set for locale ["
				+ locale + "]");
	    }
	}
	return DoNothingInputNormalizer.INSTANCE;
    }

}

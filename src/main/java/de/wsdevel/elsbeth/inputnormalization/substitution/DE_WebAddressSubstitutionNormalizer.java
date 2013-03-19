package de.wsdevel.elsbeth.inputnormalization.substitution;

import java.util.Collection;

import de.wsdevel.elsbeth.inputnormalization.InputNormalizer;

/**
 * DE_WebAddressSubstitutionNormalizer created on 22.10.2011 in project Elsbeth.
 * 
 * (c) 2004-2011 scenejo.org - All rights reserved. Scenejo - An Interactive
 * Storytelling Framework
 * 
 * @author <a href="mailto:sebastian@scenejo.org">Sebastian A. Weiss,
 *         scenejo.org</a>
 * @version $Author: Sebastian A. Weiß $ -- $Revision: $ -- $Date: $
 * 
 */
public class DE_WebAddressSubstitutionNormalizer implements InputNormalizer {

    /**
     * ${InputNormalizer} INSTANCE. The instance to be used.
     */
    public static final InputNormalizer INSTANCE = new DE_WebAddressSubstitutionNormalizer();

    /**
     * @param input
     *            {@link String}[]
     * @return {@link String}[]
     * @see de.wsdevel.elsbeth.inputnormalization.InputNormalizer#normalizeInput(java.lang.String[])
     */
    @Override
    public Collection<String> normalizeInput(final Collection<String> input) {
	return UK_US_WebAddressSubstitutionNormalizer.normalizeInput(input,
		"PUNKT", "MINUS", "PORT");
    }

}

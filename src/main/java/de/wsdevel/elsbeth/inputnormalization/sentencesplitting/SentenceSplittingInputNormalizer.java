package de.wsdevel.elsbeth.inputnormalization.sentencesplitting;

import java.util.Collection;


import de.wsdevel.elsbeth.inputnormalization.DoNothingInputNormalizer;
import de.wsdevel.elsbeth.inputnormalization.InputNormalizer;

/**
 * SentenceSplittingInputNormalizer created on 22.10.2011 in project Elsbeth.
 * 
 * (c) 2004-2011 scenejo.org - All rights reserved. Scenejo - An Interactive
 * Storytelling Framework
 * 
 * @author <a href="mailto:sebastian@scenejo.org">Sebastian A. Weiss,
 *         scenejo.org</a>
 * @version $Author: Sebastian A. Weiß $ -- $Revision: $ -- $Date: $
 * 
 */
public class SentenceSplittingInputNormalizer implements InputNormalizer {

    /**
     * ${InputNormalizer} INSTANCE. The instance to be used.
     */
    public static final InputNormalizer INSTANCE = new SentenceSplittingInputNormalizer();

    /**
     * @param input
     * @return
     * @see de.wsdevel.elsbeth.inputnormalization.InputNormalizer#normalizeInput(java.util.Collection)
     */
    public Collection<String> normalizeInput(final Collection<String> input) {
	// SEBASTIAN implement
	return DoNothingInputNormalizer.INSTANCE.normalizeInput(input);
    }

}

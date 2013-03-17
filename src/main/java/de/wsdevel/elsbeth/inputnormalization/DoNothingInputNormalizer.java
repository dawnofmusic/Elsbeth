package de.wsdevel.elsbeth.inputnormalization;

import java.util.ArrayList;
import java.util.Collection;


/**
 * DoNothingInputNormalizer created on 22.10.2011 in project Elsbeth.
 * 
 * (c) 2004-2011 scenejo.org - All rights reserved. Scenejo - An Interactive
 * Storytelling Framework
 * 
 * @author <a href="mailto:sebastian@scenejo.org">Sebastian A. Weiss,
 *         scenejo.org</a>
 * @version $Author: Sebastian A. Weiß $ -- $Revision: $ -- $Date: $
 * 
 */
public class DoNothingInputNormalizer implements InputNormalizer {

    /**
     * ${InputNormalizer} INSTANCE. The default instance to be used.
     */
    public static final InputNormalizer INSTANCE = new DoNothingInputNormalizer();

    /**
     * @param input
     *            {@link String}
     * @return {@link String}
     * @see de.wsdevel.elsbeth.inputnormalization.InputNormalizer#normalizeInput(java.lang.String[])
     */
    public Collection<String> normalizeInput(Collection<String> input) {
	return new ArrayList<String>(input);
    }

}

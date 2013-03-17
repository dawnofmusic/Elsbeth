/**
 * 
 */
package de.wsdevel.elsbeth.inputnormalization;

import java.util.Collection;

/**
 * InputNormalizer created on 22.10.2011 in project Elsbeth.
 * 
 * (c) 2004-2011 scenejo.org - All rights reserved. Scenejo - An Interactive
 * Storytelling Framework
 * 
 * @author <a href="mailto:sebastian@scenejo.org">Sebastian A. Weiss,
 *         scenejo.org</a>
 * @version $Author: Sebastian A. Weiß $ -- $Revision: $ -- $Date: $
 * 
 */
public interface InputNormalizer {

    /**
     * @param input
     *            {@link String}
     * @return {@link String}
     */
    Collection<String> normalizeInput(Collection<String> input);

}

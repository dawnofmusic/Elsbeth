package de.wsdevel.elsbeth.inputnormalization.substitution;

import de.wsdevel.elsbeth.inputnormalization.InputNormalizer;

/**
 * SubstitutionNormalizerSet created on 22.10.2011 in project Elsbeth.
 * 
 * (c) 2004-2011 scenejo.org - All rights reserved. Scenejo - An Interactive
 * Storytelling Framework
 * 
 * @author <a href="mailto:sebastian@scenejo.org">Sebastian A. Weiss,
 *         scenejo.org</a>
 * @version $Author: Sebastian A. Weiß $ -- $Revision: $ -- $Date: $
 * 
 */
public class SubstitutionNormalizerSet {

    /**
     * ${InputNormalizer} abbreviationNormalizer
     */
    private InputNormalizer abbreviationNormalizer;

    /**
     * ${InputNormalizer} filenamExtensionNormalizer
     */
    private InputNormalizer filenamExtensionNormalizer;

    /**
     * ${InputNormalizer} webAddressNormalizer
     */
    private InputNormalizer webAddressNormalizer;

    /**
     * Default constructor.
     */
    public SubstitutionNormalizerSet() {
    }

    /**
     * @param abbreviationNormalizerRef
     *            {@link InputNormalizer}
     * @param webAddressNormalizerRef
     *            {@link InputNormalizer}
     * @param filenamExtensionNormalizerRef
     *            {@link InputNormalizer}
     */
    public SubstitutionNormalizerSet(
	    final InputNormalizer abbreviationNormalizerRef,
	    final InputNormalizer webAddressNormalizerRef,
	    final InputNormalizer filenamExtensionNormalizerRef) {
	setAbbreviationNormalizer(abbreviationNormalizerRef);
	setWebAddressNormalizer(webAddressNormalizerRef);
	setFilenamExtensionNormalizer(filenamExtensionNormalizerRef);
    }

    /**
     * @return the abbreviationNormalizer
     */
    public InputNormalizer getAbbreviationNormalizer() {
	return this.abbreviationNormalizer;
    }

    /**
     * @return the filenamExtensionNormalizer
     */
    public InputNormalizer getFilenamExtensionNormalizer() {
	return this.filenamExtensionNormalizer;
    }

    /**
     * @return the webAddressNormalizer
     */
    public InputNormalizer getWebAddressNormalizer() {
	return this.webAddressNormalizer;
    }

    /**
     * @param abbreviationNormalizerRef
     *            the abbreviationNormalizer to set
     */
    public void setAbbreviationNormalizer(
	    final InputNormalizer abbreviationNormalizerRef) {
	this.abbreviationNormalizer = abbreviationNormalizerRef;
    }

    /**
     * @param filenamExtensionNormalizerRef
     *            the filenamExtensionNormalizer to set
     */
    public void setFilenamExtensionNormalizer(
	    final InputNormalizer filenamExtensionNormalizerRef) {
	this.filenamExtensionNormalizer = filenamExtensionNormalizerRef;
    }

    /**
     * @param webAddressNormalizerRef
     *            the webAddressNormalizer to set
     */
    public void setWebAddressNormalizer(
	    final InputNormalizer webAddressNormalizerRef) {
	this.webAddressNormalizer = webAddressNormalizerRef;
    }
}

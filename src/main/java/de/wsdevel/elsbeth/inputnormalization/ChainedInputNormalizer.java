package de.wsdevel.elsbeth.inputnormalization;

import java.util.Collection;


/**
 * ChainedInputNormalizer created on 22.10.2011 in project Elsbeth.
 * 
 * (c) 2004-2011 scenejo.org - All rights reserved. Scenejo - An Interactive
 * Storytelling Framework
 * 
 * @author <a href="mailto:sebastian@scenejo.org">Sebastian A. Weiss,
 *         scenejo.org</a>
 * @version $Author: Sebastian A. Weiß $ -- $Revision: $ -- $Date: $
 * 
 */
public class ChainedInputNormalizer implements InputNormalizer {

    /**
     * ${InputNormalizer} first
     */
    private InputNormalizer first;

    /**
     * ${InputNormalizer} second
     */
    private InputNormalizer second;

    /**
     * Default constructor.
     */
    public ChainedInputNormalizer() {
    }

    /**
     * @param firstRef
     *            {@link InputNormalizer}
     * @param secondRef
     *            {@link InputNormalizer}
     */
    public ChainedInputNormalizer(final InputNormalizer firstRef,
	    final InputNormalizer secondRef) {
	setFirst(firstRef);
	setSecond(secondRef);
    }

    /**
     * @return the first
     */
    public InputNormalizer getFirst() {
	return this.first;
    }

    /**
     * @return the second
     */
    public InputNormalizer getSecond() {
	return this.second;
    }

    /**
     * @param input
     *            {@link Collection}< {@link String}>
     * @return {@link Collection}< {@link String}>
     * @see de.wsdevel.elsbeth.inputnormalization.InputNormalizer#normalizeInput(java.util.Collection)
     */
    public Collection<String> normalizeInput(final Collection<String> input) {
	if (this.second != null) {
	    if (this.first != null) {
		return this.second.normalizeInput(this.first
			.normalizeInput(input));
	    }
	    return this.second.normalizeInput(input);
	}
	if (this.first != null) {
	    return this.first.normalizeInput(input);
	}
	return input;
    }

    /**
     * @param firstRef
     *            the first to set
     */
    public void setFirst(final InputNormalizer firstRef) {
	this.first = firstRef;
    }

    /**
     * @param secondRef
     *            the second to set
     */
    public void setSecond(final InputNormalizer secondRef) {
	this.second = secondRef;
    }

}

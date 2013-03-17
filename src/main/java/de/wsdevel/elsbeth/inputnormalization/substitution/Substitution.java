package de.wsdevel.elsbeth.inputnormalization.substitution;

/**
 * Substitution created on 22.10.2011 in project Elsbeth.
 * 
 * (c) 2004-2011 scenejo.org - All rights reserved. Scenejo - An Interactive
 * Storytelling Framework
 * 
 * @author <a href="mailto:sebastian@scenejo.org">Sebastian A. Weiss,
 *         scenejo.org</a>
 * @version $Author: Sebastian A. Weiß $ -- $Revision: $ -- $Date: $
 * 
 */
public class Substitution {

    /**
     * ${String} pattern
     */
    private String pattern;

    /**
     * ${String[]} substitution
     */
    private String[] substitution;

    /**
     * @param patternVal
     * @param substitutionVal
     */
    public Substitution(final String patternVal, final String[] substitutionVal) {
	this.pattern = patternVal;
	this.substitution = substitutionVal;
    }

    /**
     * @return the pattern
     */
    public String getPattern() {
	return this.pattern;
    }

    /**
     * @return the substitution
     */
    public String[] getSubstitution() {
	return this.substitution;
    }

    /**
     * @param patternVal
     *            the pattern to set
     */
    public void setPattern(final String patternVal) {
	this.pattern = patternVal;
    }

    /**
     * @param substitutionRef
     *            the substitution to set
     */
    public void setSubstitution(final String[] substitutionRef) {
	this.substitution = substitutionRef;
    }
}
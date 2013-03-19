package de.wsdevel.elsbeth.inputnormalization.substitution;

import java.util.Collection;

import de.wsdevel.elsbeth.inputnormalization.InputNormalizer;

/**
 * UKSubstitutionNormalizer created on 22.10.2011 in project Elsbeth.
 * 
 * (c) 2004-2011 scenejo.org - All rights reserved. Scenejo - An Interactive
 * Storytelling Framework
 * 
 * @author <a href="mailto:sebastian@scenejo.org">Sebastian A. Weiss,
 *         scenejo.org</a>
 * @version $Author: Sebastian A. Weiß $ -- $Revision: $ -- $Date: $
 * 
 */
public class UK_AbbreviationSubstitutionNormalizer implements InputNormalizer {

    /**
     * ${Substitution[]} substitutions
     */
    private static final Substitution[] substitutions = new Substitution[] {
	    // BE
	    new Substitution("I'M", new String[] { "I", "AM" }),
	    new Substitution("YOU'RE", new String[] { "YOU", "ARE" }),
	    new Substitution("HE'S", new String[] { "HE", "IS" }),
	    new Substitution("SHE'S", new String[] { "SHE", "IS" }),
	    new Substitution("IT'S", new String[] { "IT", "IS" }),
	    // SEBASTIAN take care !!!
	    // new Substitution("HE'S", new String[] { "HE", "HAS" }),
	    // new Substitution("SHE'S", new String[] { "SHE", "HAS" }),
	    // new Substitution("IT'S", new String[] { "IT", "HAS" }),
	    new Substitution("WE'RE", new String[] { "WE", "ARE" }),
	    new Substitution("THEY'RE", new String[] { "THEY", "ARE" }),
	    new Substitution("ISN'T", new String[] { "IS", "NOT" }),
	    new Substitution("WASN'T", new String[] { "WAS", "NOT" }),
	    new Substitution("WEREN'T", new String[] { "WERE", "NOT" }),
	    // DO
	    new Substitution("DON'T", new String[] { "DO", "NOT" }),
	    new Substitution("DOESN'T", new String[] { "DOES", "NOT" }),
	    new Substitution("DIDN'T", new String[] { "DID", "NOT" }),
	    // HAVE
	    new Substitution("I'VE", new String[] { "I", "HAVE" }),
	    new Substitution("YOU'VE", new String[] { "YOU", "HAVE" }),
	    new Substitution("WE'VE", new String[] { "WE", "HAVE" }),
	    new Substitution("THEY'VE", new String[] { "THEY", "HAVE" }),
	    new Substitution("HAVEN'T", new String[] { "HAVE", "NOT" }),
	    new Substitution("HASN'T", new String[] { "HAS", "NOT" }),
	    new Substitution("HADN'T", new String[] { "HAD", "NOT" }),
	    // CAN
	    new Substitution("CAN'T", new String[] { "CANNOT" }),
	    new Substitution("COULDN'T", new String[] { "COULD", "NOT" }),
	    // OTHER MODAL
	    new Substitution("I'D", new String[] { "I", "WOULD" }),
	    new Substitution("YOU'D", new String[] { "YOU", "WOULD" }),
	    new Substitution("HE'D", new String[] { "HE", "WOULD" }),
	    new Substitution("SHE'D", new String[] { "SHE", "WOULD" }),
	    new Substitution("IT'D", new String[] { "IT", "WOULD" }),
	    new Substitution("WE'D", new String[] { "WE", "WOULD" }),
	    new Substitution("THEY'D", new String[] { "THEY", "WOULD" }),
	    // SEBASTIAN take care
	    // new Substitution("I'D", new String[] { "I","HAD" }),
	    // new Substitution("YOU'D", new String[] { "YOU", "HAD" }),
	    // new Substitution("HE'D", new String[] { "HE", "HAD" }),
	    // new Substitution("SHE'D", new String[] { "SHE", "HAD" }),
	    // new Substitution("IT'D", new String[] { "IT", "HAD" }),
	    // new Substitution("WE'D", new String[] { "WE", "HAD" }),
	    // new Substitution("THEY'D", new String[] { "THEY", "HAD" }),

	    new Substitution("WON'T", new String[] { "WILL", "NOT" }),
	    new Substitution("WOULDN'T", new String[] { "WOULD", "NOT" }),
	    new Substitution("OUGHTN'T", new String[] { "OUGHT", "NOT" }),
	    new Substitution("SHAN'T", new String[] { "SHALL", "NOT" }),
	    new Substitution("SHOULDN'T", new String[] { "SHOULD", "NOT" }),
	    new Substitution("MIGHTN'T", new String[] { "MIGHT", "NOT" }),
	    new Substitution("NEEDN'T", new String[] { "NEED", "NOT" }),
	    new Substitution("MUSTN'T", new String[] { "MUST", "NOT" }),
	    // TITLES
	    new Substitution("MR", new String[] { "MISTER" }),
	    new Substitution("MRS", new String[] { "MISSES" }),
	    new Substitution("MS", new String[] { "MISS" }),
	    new Substitution("DR", new String[] { "DOCTOR" }),
	    new Substitution("PROF", new String[] { "PROFESSOR" }) };

    /**
     * @param input
     *            {@link String}
     * @return {@link String}[]
     * @see de.wsdevel.elsbeth.inputnormalization.InputNormalizer#normalizeInput(java.lang.String[])
     */
    public Collection<String> normalizeInput(final Collection<String> input) {
	return SubstitutionNormalizerHelper.normalizeInput(
		UK_AbbreviationSubstitutionNormalizer.substitutions, input);
    }

}

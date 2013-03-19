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
 *          SEBASTIAN reuse uk substitutions
 * 
 */
public class DE_AbbreviationSubstitutionNormalizer implements InputNormalizer {

    /**
     * ${Substitution[]} substitutions
     */
    private static final Substitution[] substitutions = new Substitution[] {
	    // VERSCHMELZUNGEN
	    new Substitution("ANS", new String[] { "AN", "DAS" }),
	    new Substitution("AUFS", new String[] { "AUF", "DAS" }),
	    new Substitution("DURCHS", new String[] { "DURCH", "DAS" }),
	    new Substitution("FÜRS", new String[] { "FÜR", "DAS" }),
	    new Substitution("HINTERS", new String[] { "HINTER", "DAS" }),
	    new Substitution("INS", new String[] { "IN", "DAS" }),
	    new Substitution("ÜBERS", new String[] { "ÜBER", "DAS" }),
	    new Substitution("UNTERS", new String[] { "UNTER", "DAS" }),
	    new Substitution("VORS", new String[] { "VOR", "DAS" }),
	    new Substitution("AM", new String[] { "AN", "DEM" }),
	    new Substitution("BEIM", new String[] { "BEI", "DEM" }),
	    new Substitution("HINTERM", new String[] { "HINTER", "DEM" }),
	    new Substitution("ÜBERM", new String[] { "ÜBER", "DEM" }),
	    new Substitution("UNTERM", new String[] { "UNTER", "DEM" }),
	    new Substitution("VORM", new String[] { "VOR", "DEM" }),
	    new Substitution("HINTERN", new String[] { "HINTER", "DEN" }),
	    new Substitution("ÜBERN", new String[] { "ÜBER", "DEN" }),
	    new Substitution("UNTERN", new String[] { "UNTER", "DEN" }),
	    new Substitution("VORN", new String[] { "VOR", "DEN" }),
	    new Substitution("ZUR", new String[] { "ZU", "DER" }),
	    // TITLES
	    new Substitution("HR.", new String[] { "HERR" }),
	    new Substitution("FR.", new String[] { "FRAU" }),
	    new Substitution("DR.", new String[] { "DOKTOR" }),
	    new Substitution("PROF.", new String[] { "PROFESSOR" }) };

    /**
     * @param input
     *            {@link String}
     * @return {@link String}[]
     * @see de.wsdevel.elsbeth.inputnormalization.InputNormalizer#normalizeInput(java.lang.String[])
     */
    @Override
    public Collection<String> normalizeInput(final Collection<String> input) {
	return SubstitutionNormalizerHelper.normalizeInput(
		DE_AbbreviationSubstitutionNormalizer.substitutions, input);
    }

}

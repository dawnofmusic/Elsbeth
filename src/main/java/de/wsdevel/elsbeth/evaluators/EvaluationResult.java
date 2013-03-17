package de.wsdevel.elsbeth.evaluators;

import de.wsdevel.elsbeth.Match;

/**
 * Created on 24.09.2008.
 * 
 * for project: Elsbeth
 * 
 * @author <a href="mailto:sweiss@weissundschmidt.de">Sebastian A. Weiss - Weiss
 *         und Schmidt, Mediale Systeme GbR</a>
 * @version $Author: $ -- $Revision: $ -- $Date: $
 * 
 * <br>
 *          (c) 2007, Weiss und Schmidt, Mediale Systeme GbR - All rights
 *          reserved.
 * 
 */
public interface EvaluationResult {

    /**
     * @return {@link Match} the {@link Match} this {@link EvaluationResult} is
     *         derives from.
     */
    Match getOriginMatch();

    /**
     * @param textVal
     *            {@link String}
     */
    void setText(String textVal);

    /**
     * @return {@link String} the text.
     */
    String getText();

}
//
// $Log: $
//

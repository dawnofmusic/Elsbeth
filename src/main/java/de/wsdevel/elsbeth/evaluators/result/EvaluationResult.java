package de.wsdevel.elsbeth.evaluators.result;

import java.util.List;

import de.wsdevel.elsbeth.Match;

/**
 * Created on 24.09.2008 for project: Elsbeth
 * 
 * (c) 2008, Sebastian A. Weiss - All rights reserved.
 * 
 * @author <a href="mailto:post@sebastian-weiss.de">Sebastian A. Weiss</a>
 * @version $Author: $ -- $Revision: $ -- $Date: $
 */
public interface EvaluationResult {

    /**
     * @return {@link Match} the {@link Match} this {@link EvaluationResult} is
     *         derives from.
     */
    Match getOriginMatch();

    /**
     * @return {@link List}< {@link PredicateOperation}>
     */
    List<? extends PredicateOperation> getPredicateOperations();

    /**
     * @return {@link String} the text.
     */
    String getText();

    /**
     * @param textVal
     *            {@link String}
     */
    void setText(String textVal);

}
//
// $Log: $
//

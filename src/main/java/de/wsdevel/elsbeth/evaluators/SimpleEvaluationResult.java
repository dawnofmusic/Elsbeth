package de.wsdevel.elsbeth.evaluators;

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
public class SimpleEvaluationResult extends EvaluationResultImpl implements
	EvaluationResult {

    /**
     * COMMENT.
     * 
     * @param textVal
     *            {@link String}
     */
    public SimpleEvaluationResult(final String textVal) {
	super();
	setText(textVal);
    }

}
//
// $Log: $
//

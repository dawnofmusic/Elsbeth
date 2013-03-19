package de.wsdevel.elsbeth.evaluators.result;

/**
 * Created on 24.09.2008 for project: Elsbeth
 * 
 * (c) 2008, Sebastian A. Weiss - All rights reserved.
 * 
 * @author <a href="mailto:post@sebastian-weiss.de">Sebastian A. Weiss</a>
 * @version $Author: $ -- $Revision: $ -- $Date: $
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

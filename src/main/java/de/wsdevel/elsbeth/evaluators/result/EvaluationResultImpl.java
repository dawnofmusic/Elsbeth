package de.wsdevel.elsbeth.evaluators.result;

import java.util.LinkedList;
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
public class EvaluationResultImpl implements EvaluationResult {

    /**
     * Integrates an old evaluation result in a new one.
     * 
     * @param base
     *            {@link EvaluationResult}
     * @return {@link EvaluationResult}
     */
    public static final EvaluationResultImpl copy(final EvaluationResult base) {
	final EvaluationResultImpl evaluationResultImpl = new EvaluationResultImpl();
	evaluationResultImpl.setText(base.getText() != null ? base.getText()
		.trim() : null);
	// SCENEJO
	// evaluationResultImpl.setChangeScene(base.getChangeScene());
	evaluationResultImpl.getPredicateOperations().addAll(
		base.getPredicateOperations());
	return evaluationResultImpl;
    }

    /**
     * {@link List}< {@link PredicateOperation}>
     */
    private List<PredicateOperation> predicateOperations = new LinkedList<PredicateOperation>();

    /**
     * {@link String} COMMENT.
     */
    private String text;

    /**
     * {@link Match} COMMENT.
     */
    private Match originMatch;

    /**
     * @return {@link Match} the originMatch.
     * @see de.wsdevel.elsbeth.evaluators.result.EvaluationResult#getOriginMatch()
     */
    @Override
    public final Match getOriginMatch() {
	return this.originMatch;
    }

    /**
     * @return the predicateOperations
     */
    @Override
    public List<PredicateOperation> getPredicateOperations() {
	return this.predicateOperations;
    }

    /**
     * @return {@link String} the text.
     */
    @Override
    public String getText() {
	return this.text;
    }

    /**
     * COMMENT.
     * 
     * @param toBeIntegrated
     *            {@link EvaluationResult}
     */
    public final void integrate(final EvaluationResult toBeIntegrated) {
	integrateText(toBeIntegrated.getText());
	// SCENEJO
	// setChangeScene(toBeIntegrated.getChangeScene());
	getPredicateOperations()
		.addAll(toBeIntegrated.getPredicateOperations());
    }

    /**
     * COMMENT.
     * 
     * @param textVal
     *            {@link String}
     */
    public final void integrateText(final String textVal) {
	if ((textVal != null) && !textVal.trim().equals("")) {
	    setText((getText() != null ? getText().trim() : "") + " " + textVal);
	}
    }

    /**
     * @param originMatchRef
     *            {@link Match} the originMatch to set.
     */
    public final void setOriginMatch(final Match originMatchRef) {
	this.originMatch = originMatchRef;
    }

    /**
     * @param predicateOperations
     *            the predicateOperations to set
     */
    public void setPredicateOperations(
	    final List<PredicateOperation> predicateOperations) {
	this.predicateOperations = predicateOperations;
    }

    /**
     * @param text
     *            {@link String} the text to set.
     */
    @Override
    public void setText(final String text) {
	this.text = text;
    }

    /**
     * {@inheritDoc}
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public final String toString() {
	return getClass().getSimpleName()
		+ " [text: '"
		+ getText()
		+ "', operations count: "
		+ (getPredicateOperations() != null ? getPredicateOperations()
			.size() : "")
		// + ", changeScene: "
		// + (getChangeScene() != null ? getChangeScene().getSceneName()
		// : "")
		+ "]";
    }

}
//
// $Log: $
//

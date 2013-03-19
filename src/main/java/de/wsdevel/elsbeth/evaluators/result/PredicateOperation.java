package de.wsdevel.elsbeth.evaluators.result;

import de.wsdevel.elsbeth.DialogAgent;

/**
 * Created on 19.03.2013 for project: Elsbeth
 * 
 * (c) 2013, Sebastian A. Weiss - All rights reserved.
 * 
 * @author <a href="mailto:post@sebastian-weiss.de">Sebastian A. Weiss</a>
 * @version $Author: $ -- $Revision: $ -- $Date: $
 */
public class PredicateOperation {

    /**
     * {@link String} the name of the predicate.
     */
    private String predicateName;

    /**
     * {@link String} the value.
     */
    private String value;

    /**
     * {@link Operator}
     */
    private Operator operator;

    /**
     * Default constructor.
     */
    public PredicateOperation() {
    }

    /**
     * @param predicateNameVal
     *            {@link String}
     * @param operatorRef
     *            {@link Operator}
     * @param valueVal
     *            {@link String}
     */
    public PredicateOperation(final String predicateNameVal,
	    final Operator operatorRef, final String valueVal) {
	setPredicateName(predicateNameVal);
	setValue(valueVal);
	setOperator(operatorRef);
    }

    /**
     * @param da
     *            {@link DialogAgent}
     */
    public void execute(final DialogAgent da) {
	switch (this.operator) {
	case SET:
	    da.setPredicateValue(getPredicateName(), getValue());
	    break;
	default:
	    break;
	}
    }

    /**
     * @return the operator
     */
    public Operator getOperator() {
	return this.operator;
    }

    /**
     * @return the predicateName
     */
    public String getPredicateName() {
	return this.predicateName;
    }

    /**
     * @return the value
     */
    public String getValue() {
	return this.value;
    }

    /**
     * @param operator
     *            the operator to set
     */
    public void setOperator(final Operator operator) {
	this.operator = operator;
    }

    /**
     * @param predicateName
     *            the predicateName to set
     */
    public void setPredicateName(final String predicateName) {
	this.predicateName = predicateName;
    }

    /**
     * @param value
     *            the value to set
     */
    public void setValue(final String value) {
	this.value = value;
    }

}

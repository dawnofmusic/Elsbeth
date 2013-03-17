package de.wsdevel.elsbeth.evaluators.impl;

import org.apache.xmlbeans.XmlObject;

import de.wsdevel.elsbeth.DialogAgent;
import de.wsdevel.elsbeth.evaluators.Evaluator;

/**
 * Created on 21.11.2010 for project: Elsbeth. (c) 2010, Sebastian A. Weiss -
 * All rights reserved.
 * 
 * @author <a href="mailto:sebastian@scenejo.org">Sebastian A. Weiss -
 *         scenejo.org</a>
 */
public abstract class AbstractEvaluator<T extends XmlObject> implements
	Evaluator<T> {

    /**
     * {@link DialogAgent} COMMENT.
     */
    private DialogAgent agent;

    /**
     * Default constructor.
     */
    public AbstractEvaluator() {
	this(null);
    }

    /**
     * @param agentRef
     *            {@link DialogAgent}
     * @see AbstractEvaluator#setAgent(DialogAgent)
     */
    public AbstractEvaluator(final DialogAgent agentRef) {
	setAgent(agentRef);
    }

    /**
     * @return {@link DialogAgent} the agent.
     */
    public final DialogAgent getAgent() {
	return this.agent;
    }

    /**
     * @param agentRef
     *            {@link DialogAgent} the agent to set.
     */
    public final void setAgent(final DialogAgent agentRef) {
	this.agent = agentRef;
    }

}
//
// $Log: $
//

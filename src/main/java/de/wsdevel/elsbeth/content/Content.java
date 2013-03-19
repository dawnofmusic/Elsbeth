package de.wsdevel.elsbeth.content;

import java.util.LinkedList;

import org.aitools.xaiml.MixedPatternExpression;
import org.apache.lucene.document.Document;

import de.wsdevel.elsbeth.Match;
import de.wsdevel.elsbeth.evaluators.EvaluationException;
import de.wsdevel.elsbeth.evaluators.Evaluators;
import de.wsdevel.elsbeth.evaluators.result.EvaluationResult;

/**
 * Created on 01.11.2010 for project: Elsbeth. (c) 2010, Sebastian A. Weiss -
 * All rights reserved.
 * 
 * @author <a href="mailto:sebastian@scenejo.org">Sebastian A. Weiss -
 *         scenejo.org</a>
 */
public interface Content {

    /**
     * @param match
     *            {@link Match}
     * @return {@link EvaluationResult}
     */
    EvaluationResult evaluate(Evaluators evaluators, Match match)
	    throws EvaluationException;

    /**
     * @return {@link Source} the source this content is coming from.
     */
    Source getSource();

    /**
     * @return {@link String}
     */
    String getStringRepresentation();

    /**
     * @return {@link LinkedList<MixedPatternExpression>} the thats.
     */
    LinkedList<MixedPatternExpression> getThats();

    /**
     * @return {@link LinkedList<String>} the topics.
     */
    LinkedList<String> getTopics();

    /**
     * @param doc
     *            {@link Document}
     */
    void storeInLuceneDocument(Document doc);
}
//
// $Log: $
//

package de.wsdevel.elsbeth.evaluators;

import org.apache.xmlbeans.XmlObject;

import de.wsdevel.elsbeth.Match;

/**
 * Created on 15.08.2008.
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
public interface Evaluator<T extends XmlObject> {

    /**
     * COMMENT.
     * 
     * @param xml
     *            some class of {@link XmlObject} to be evaluated.
     * @param tagName
     *            {@link String} name of tag, maybe needed for evaluation. MayBe
     *            <code>null</code> or empty!
     * @param match
     *            {@link Match} containing the template and the mathcinbg path
     * @return {@link String} evaluated value.
     * @throws EvaluationException
     */
    EvaluationResult evaluate(T xml, String tagName, Match match)
	    throws EvaluationException;

}
//
// $Log: $
//

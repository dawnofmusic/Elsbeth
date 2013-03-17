package de.wsdevel.elsbeth.content;

import org.aitools.xaiml.MixedTemplateContentContainer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;

import de.wsdevel.elsbeth.Brain;
import de.wsdevel.elsbeth.Match;
import de.wsdevel.elsbeth.aiml.AimlTags;
import de.wsdevel.elsbeth.evaluators.EvaluationException;
import de.wsdevel.elsbeth.evaluators.EvaluationResult;
import de.wsdevel.elsbeth.evaluators.Evaluators;

/**
 * Created on 01.11.2010 for project: Elsbeth. (c) 2010, Sebastian A. Weiss -
 * All rights reserved.
 * 
 * @author <a href="mailto:sebastian@scenejo.org">Sebastian A. Weiss -
 *         scenejo.org</a>
 */
public class TemplateContent extends AbstractContent implements Content {

    /**
     * {@link MixedTemplateContentContainer} COMMENT.
     */
    private MixedTemplateContentContainer template = null;

    /**
     * @param templateRef
     *            {@link MixedTemplateContentContainer}
     */
    public TemplateContent(final Source sourceVal,
	    final MixedTemplateContentContainer templateRef) {
	super(sourceVal);
	setTemplate(templateRef);
    }

    /**
     * @param evaluators
     *            {@link Evaluators}
     * @param match
     *            {@link Match}
     * @return {@link EvaluationResult}
     * @throws EvaluationException
     * @see de.wsdevel.elsbeth.content.Content#evaluate(org.scenejo.elsbeth.evaluators.Evaluators,
     *      org.scenejo.elsbeth.Match)
     */
    public final EvaluationResult evaluate(final Evaluators evaluators,
	    final Match match) throws EvaluationException {
	return evaluators.evaluate(getTemplate(), AimlTags.TEMPLATE, match);
    }

    /**
     * @return {@link String}
     * @see de.wsdevel.elsbeth.content.Content#getStringRepresentation()
     */
    public final String getStringRepresentation() {
	return getTemplate() != null ? getTemplate().xmlText() : "";
    }

    /**
     * @return {@link MixedTemplateContentContainer} the template.
     */
    public final MixedTemplateContentContainer getTemplate() {
	return this.template;
    }

    /**
     * @param templateRef
     *            {@link MixedTemplateContentContainer} the template to set.
     */
    public final void setTemplate(
	    final MixedTemplateContentContainer templateRef) {
	this.template = templateRef;
    }

    /**
     * @param doc
     *            {@link Document}
     * @see de.wsdevel.elsbeth.content.Content#storeInLuceneDocument(org.apache.lucene.document.Document)
     */
    public final void storeInLuceneDocument(final Document doc) {
	doc.add(new Field(Brain.LUCENE_FIELD_NAME_CONTENT_TEMPLATE,
		getStringRepresentation(), Field.Store.YES,
		Field.Index.ANALYZED));
    }

}
//
// $Log: $
//

package de.wsdevel.elsbeth;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.SortedSet;

import javax.swing.ListModel;

import org.aitools.xaiml.AimlDocument.Aiml;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import de.wsdevel.elsbeth.aiml.AimlTags;
import de.wsdevel.elsbeth.aiml.Predicate;
import de.wsdevel.elsbeth.evaluators.EvaluationException;
import de.wsdevel.elsbeth.evaluators.EvaluationResult;
import de.wsdevel.elsbeth.evaluators.EvaluationResultImpl;
import de.wsdevel.elsbeth.evaluators.Evaluators;
import de.wsdevel.tools.awt.model.SetWithListModel;
import de.wsdevel.tools.awt.model.SetWithListModelImpl;

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
public class DialogAgent implements PredicateHolder {

    /**
     * {@link Log} the logger.
     */
    private static final Log LOG = LogFactory.getLog(DialogAgent.class);

    /**
     * <code>int</code> the maximal number of random values used for fuzzy
     * expressions.
     */
    private static final int MAX_NUMBER_RANDOM_VALUES = 100;

    /**
     * {@link String} COMMENT.
     */
    private static final String PREDICATE_NAME_RANDOM = "RANDOM";

    /**
     * {@link String} COMMENT.
     */
    private static final String PREDICATE_NAME_RANDOM_PER_PLAY = "RANDOM_PER_PLAY";

    /**
     * {@link String} COMMENT.
     */
    private static final String PREDICATE_NAME_RANDOM_PER_TURN = "RANDOM_PER_TURN";

    /**
     * {@link String} COMMENT. SEBASTIAN write version support
     */
    public static final String VERSION = "Elsbeth v0.3.0";

    /**
     * {@link HashMap<String,String>} COMMENT.
     */
    private HashMap<String, String> botProperties = new HashMap<String, String>();

    /**
     * {@link Brain} COMMENT.
     */
    private Brain brain = null;

    /**
     * {@link SetWithListModel}< {@link Predicate}> COMMENT.
     */
    private final SetWithListModel<Predicate> currentPredicates = new SetWithListModelImpl<Predicate>();

    /**
     * {@link Evaluators} COMMENT.
     */
    private Evaluators evaluators = null;

    /**
     * ${Locale} locale
     */
    private final Locale locale;

    /**
     * {@link HashMap<String,String>} COMMENT.
     */
    private final HashMap<String, String> predicates = new HashMap<String, String>();

    /**
     * {@link PredicateWatcherSupport} COMMENT.
     */
    private final PredicateWatcherSupport pws = new PredicateWatcherSupport();

    /**
     * {@link Random} randomGenerator used for creating random numbers for fuzzy
     * conditions.
     */
    private final Random randomGenerator;

    /**
     * @param localeRef
     *            {@link Locale} determining the language settings for this
     *            agent. This parameter might be <code>null</code>, but in that
     *            case proper handling of language specifics cannot be
     *            guaranteed
     */
    public DialogAgent(final Locale localeRef) {
	this.locale = localeRef;
	this.randomGenerator = new Random(System.currentTimeMillis());
	setPredicateValue(DialogAgent.PREDICATE_NAME_RANDOM_PER_TURN,
		Integer.toString(this.randomGenerator
			.nextInt(DialogAgent.MAX_NUMBER_RANDOM_VALUES)));
	setPredicateValue(DialogAgent.PREDICATE_NAME_RANDOM_PER_PLAY,
		Integer.toString(this.randomGenerator
			.nextInt(DialogAgent.MAX_NUMBER_RANDOM_VALUES)));

	this.evaluators = new Evaluators(this);
	setBrain(new Brain(this.evaluators, this.locale));
    }

    /**
     * @param watcher
     *            {@link PredicateWatcher}
     * @see org.scenejo.elsbeth.PredicateWatcherSupport#addWatcher(org.scenejo.elsbeth.PredicateWatcher)
     */
    public final void addWatcher(final PredicateWatcher watcher) {
	this.pws.addWatcher(watcher);
    }

    /**
     * COMMENT.
     */
    public void clear() {
	clearBrain();
	clearPredicates();
    }

    /**
     * COMMENT.
     */
    public void clearBrain() {
	synchronized (this.brain) {
	    this.brain = null;
	    this.brain = new Brain(this.evaluators, this.locale);
	}
    }

    /**
     * COMMENT.
     */
    public void clearPredicates() {
	setPredicateValue(DialogAgent.PREDICATE_NAME_RANDOM_PER_PLAY,
		Integer.toString(this.randomGenerator
			.nextInt(DialogAgent.MAX_NUMBER_RANDOM_VALUES)));
	this.predicates.clear();
	this.currentPredicates.clear();
    }

    /**
     * COMMENT.
     * 
     * @param match
     *            {@link Match}
     * @return {@link EvaluationResult}
     */
    private final EvaluationResult evaluate(final Match match) {
	if ((match != null) && (match.getContent() != null)) {
	    try {
		final EvaluationResult evaluate = this.evaluators.evaluate(
			match.getContent(), match);
		if (evaluate == null) {
		    return Evaluators.EMPTY_EVALUATION_RESULT;
		}
		final EvaluationResultImpl impl = new EvaluationResultImpl();
		impl.setText(evaluate.getText() != null ? evaluate.getText()
			.trim() : null);
		// SCENEJO
		// impl.setChangeScene(evaluate.getChangeScene());
		// impl.getPredicateOperations().addAll(
		// evaluate.getPredicateOperations());
		impl.setOriginMatch(match);
		if (DialogAgent.LOG.isDebugEnabled()) {
		    DialogAgent.LOG.debug("response: " + impl);
		}
		return impl;
	    } catch (final EvaluationException e) {
		DialogAgent.LOG.error(e.getLocalizedMessage(), e);
	    }
	}
	return Evaluators.EMPTY_EVALUATION_RESULT;
    }

    /**
     * @return {@link HashMap<String,String>} the botProperties.
     */
    public final HashMap<String, String> getBotProperties() {
	return this.botProperties;
    }

    /**
     * @return {@link Brain} the brain.
     */
    public final Brain getBrain() {
	return this.brain;
    }

    /**
     * @return {@link String} the current topic.
     */
    public final String getCurrentThat() {
	String string = getPredicateValue(AimlTags.THAT);
	if (string == null) {
	    string = "";
	}
	return string;
    }

    /**
     * @return {@link String} the current topic.
     */
    public final String getCurrentTopic() {
	String string = getPredicateValue(AimlTags.TOPIC);
	if (string == null) {
	    string = "";
	}
	return string;
    }

    /**
     * @return {@link ListModel}
     */
    public final ListModel getListModelOverCurrentPredicates() {
	return this.currentPredicates.getListModel();
    }

    /**
     * @param request
     *            {@link String}
     * @return {@link SortedSet}< {@link Match}> matches sorted by relevance.
     */
    private final List<Match> getMatchesForRequest(final String request) {
	return this.brain.match(request.trim(), getCurrentThat(),
		getCurrentTopic());
    }

    /**
     * COMMENT.
     * 
     * @param name
     *            {@link String}
     */
    public final String getPredicateValue(final String name) {
	if (name.equals(DialogAgent.PREDICATE_NAME_RANDOM)) {
	    setPredicateValue(DialogAgent.PREDICATE_NAME_RANDOM,
		    Integer.toString(this.randomGenerator
			    .nextInt(DialogAgent.MAX_NUMBER_RANDOM_VALUES)));
	}
	return this.predicates.get(name);
    }

    /**
     * COMMENT.
     * 
     * @param request
     *            {@link String}
     * @return {@link String}
     */
    public final EvaluationResult getResponseForRequest(final String request) {
	setPredicateValue(DialogAgent.PREDICATE_NAME_RANDOM_PER_TURN,
		Integer.toString(this.randomGenerator
			.nextInt(DialogAgent.MAX_NUMBER_RANDOM_VALUES)));

	final List<Match> matchesForRequest = getMatchesForRequest(request);
	if (DialogAgent.LOG.isDebugEnabled()) {
	    DialogAgent.LOG.debug("### MATCHES");

	}
	for (final Match match : matchesForRequest) {
	    if (DialogAgent.LOG.isDebugEnabled()) {
		DialogAgent.LOG.debug(match);
	    }
	}
	// SEBASTIAN here we have to decide which one we want to use!
	if (matchesForRequest.size() > 0) {
	    final EvaluationResult evaluate = evaluate(matchesForRequest.get(0));
	    setCurrentThat(evaluate.getText() != null ? evaluate.getText() : "");
	    return evaluate;
	}
	setCurrentThat("");
	return Evaluators.EMPTY_EVALUATION_RESULT;
    }

    /**
     * @see org.scenejo.elsbeth.expressions.PredicateHolder#getValueForPredicateName(java.lang.String)
     */
    public final String getValueForPredicateName(final String predicateName) {
	return getPredicateValue(predicateName);
    }

    /**
     * @param aiml
     *            {@link Aiml}
     * @see org.scenejo.elsbeth.Brain#load(org.aitools.xaiml.AimlDocument.Aiml)
     */
    public final void load(final Aiml aiml) {
	this.brain.load(aiml);
    }

    /**
     * @param doc
     *            {@link File}
     * @see org.scenejo.elsbeth.Brain#load(java.io.File)
     */
    public final boolean load(final File doc, final boolean overwrite) {
	return this.brain.load(doc, overwrite);
    }

    // SCENEJO
    // /**
    // * @param scenejoAIML
    // * {@link ScenejoAIML}
    // * @see
    // org.scenejo.elsbeth.Brain#load(org.scenejo.scenejoAIML.v11.ScenejoAIML)
    // */
    // public void load(final ScenejoAIML scenejoAIML) {
    // this.brain.load(scenejoAIML);
    // }

    /**
     * @param watcher
     *            {@link PredicateWatcher}
     * @see org.scenejo.elsbeth.PredicateWatcherSupport#removeWatcher(org.scenejo.elsbeth.PredicateWatcher)
     */
    public final void removeWatcher(final PredicateWatcher watcher) {
	this.pws.removeWatcher(watcher);
    }

    /**
     * @param botPropertiesRef
     *            {@link HashMap<String,String>} the botProperties to set.
     */
    public final void setBotProperties(
	    final HashMap<String, String> botPropertiesRef) {
	this.botProperties = botPropertiesRef;
    }

    /**
     * @param brainRef
     *            {@link Brain} the brain to set.
     */
    public final void setBrain(final Brain brainRef) {
	this.brain = brainRef;
    }

    /**
     * @return {@link String} the current topic.
     */
    public final void setCurrentThat(final String that) {
	if (that == null) {
	    throw new NullPointerException("that may not be null!");
	}
	setPredicateValue(AimlTags.THAT, that);
    }

    /**
     * COMMENT.
     * 
     * @param name
     *            {@link String}
     * @param value
     *            {@link String}
     */
    public final void setPredicateValue(final String name, final String value) {
	this.predicates.put(name, value);
	final Predicate simplePredicate = new Predicate(name, value, "");
	if (!this.currentPredicates.add(simplePredicate)) {
	    this.currentPredicates.remove(simplePredicate);
	    this.currentPredicates.add(simplePredicate);
	}
	this.pws.fireWatchPredicate(simplePredicate);
    }

}
//
// $Log: $
//

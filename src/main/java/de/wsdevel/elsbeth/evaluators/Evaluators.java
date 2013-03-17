package de.wsdevel.elsbeth.evaluators;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Random;
import java.util.Vector;

import org.aitools.xaiml.MixedTemplateContentContainer;
import org.aitools.xaiml.impl.BotPredicateImpl;
import org.aitools.xaiml.impl.IndexedElementImpl;
import org.aitools.xaiml.impl.MixedTemplateContentContainerImpl;
import org.aitools.xaiml.impl.MixedTemplateContentContainerImpl.ConditionImpl;
import org.aitools.xaiml.impl.MixedTemplateContentContainerImpl.GetImpl;
import org.aitools.xaiml.impl.MixedTemplateContentContainerImpl.LearnImpl;
import org.aitools.xaiml.impl.MixedTemplateContentContainerImpl.RandomImpl;
import org.aitools.xaiml.impl.MixedTemplateContentContainerImpl.SetImpl;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.xmlbeans.XmlCursor;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.values.XmlAnyTypeImpl;

import de.wsdevel.elsbeth.DialogAgent;
import de.wsdevel.elsbeth.Match;
import de.wsdevel.elsbeth.aiml.AimlTags;
import de.wsdevel.elsbeth.content.Content;

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
public class Evaluators implements Evaluator<XmlObject> {

    /**
     * {@link String} COMMENT.
     */
    private static final String EMPTY_ELEMENT_XML_TEXT = "<xml-fragment xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"/>";

    /**
     * {@link SimpleEvaluationResult} COMMENT.
     */
    public static final SimpleEvaluationResult EMPTY_EVALUATION_RESULT = new SimpleEvaluationResult(
	    "");

    /**
     * {@link Log} the logger.
     */
    private static final Log LOG = LogFactory.getLog(Evaluators.class);

    /**
     * COMMENT.
     * 
     * @param xml
     *            {@link ConditionImpl}
     * @param agent
     *            {@link DialogAgent}
     * @return <code>boolean</code>
     */
    private static boolean aimlConditionIsFullfilled(
	    final MixedTemplateContentContainer.Condition xml,
	    final DialogAgent agent) {
	if (xml.isSetName() && xml.isSetValue()) {
	    final String predicateValue = agent.getValueForPredicateName(xml
		    .getName());
	    if ((predicateValue == null) || predicateValue.trim().equals("")) {
		return false;
	    }
	    return predicateValue.trim().equals(xml.getValue().trim());
	}
	return false;
    }

    // SCENEJO
    // /**
    // * COMMENT.
    // *
    // * @param xml
    // * {@link ConditionImpl}
    // * @param agent
    // * {@link DialogAgent}
    // * @return <code>boolean</code>
    // */
    // private static boolean conditionIsFullfilled(final Condition xml,
    // final DialogAgent agent) {
    // if (xml.isSetPredicateName() && xml.isSetExpression()) {
    // return PredicateConditionExpression.evaluate(agent,
    // xml.getPredicateName(), xml.getExpression());
    // }
    // return false;
    // }

    /**
     * COMMENT.
     * 
     * @param content
     *            {@link String}
     * @return {@link String}
     */
    private static String doGender(final String content) {
	// SEBASTIAN implement
	return content;
    }

    /**
     * COMMENT.
     * 
     * @param content
     *            {@link String}
     * @return {@link String}
     */
    private static String doPerson(final String content) {
	// SEBASTIAN implement
	return content;
    }

    /**
     * COMMENT.
     * 
     * @param content
     *            {@link String}
     * @return {@link String}
     */
    private static String doPerson2(final String content) {
	// SEBASTIAN implement
	return content;
    }

    /**
     * {@link HashMap<Class<? extends XmlObject>,Evaluator>} COMMENT.
     */
    @SuppressWarnings("rawtypes")
    private final HashMap<Class<? extends XmlObject>, Evaluator> evaluators = new HashMap<Class<? extends XmlObject>, Evaluator>();

    /**
     * COMMENT.
     * 
     * @param agent
     *            {@link DialogAgent}
     */
    public Evaluators(final DialogAgent agent) {

	this.evaluators.put(IndexedElementImpl.class,
		createIndexedElementEvaluator());
	// SCENEJO
	// this.evaluators.put(ActionImpl.class, createActionEvaluator(agent));
	this.evaluators.put(BotPredicateImpl.class,
		createBotPredicateEvaluator(agent));
	this.evaluators.put(GetImpl.class, createGetEvaluator(agent));
	this.evaluators.put(SetImpl.class, createSetEvaluator());

	// SCENEJO
	// this.evaluators.put(NextSREConnectorImpl.class,
	// new Evaluator<NextSREConnectorImpl>() {
	// public EvaluationResult evaluate(NextSREConnectorImpl xml,
	// String tagName, Match match)
	// throws EvaluationException {
	// agent.getBrain().setNextPreferredSREId(
	// xml.getNextSREId());
	// return EMPTY_EVALUATION_RESULT;
	// }
	// });

	final Evaluator<MixedTemplateContentContainerImpl> mtcce = createMixedTemplateContentContainerEvaluator(agent);
	this.evaluators.put(MixedTemplateContentContainerImpl.class, mtcce);
	// SCENEJO
	// this.evaluators.put(AbstractOutputImpl.class, mtcce);

	this.evaluators.put(LearnImpl.class, createLearnEvaluator(agent));
	this.evaluators.put(
		MixedTemplateContentContainerImpl.ConditionImpl.class,
		createAIMLConditionEvaluator(agent));
	// SCENEJO
	// this.evaluators.put(ConditionImpl.class,
	// createConditionEvaluator(agent));
	// this.evaluators.put(BlockConditionImpl.class,
	// createBlockConditionEvaluator(agent));
	this.evaluators.put(RandomImpl.class, createRandomEvaluator());
	this.evaluators.put(XmlAnyTypeImpl.class,
		new Evaluator<XmlAnyTypeImpl>() {
		    public EvaluationResult evaluate(final XmlAnyTypeImpl xml,
			    final String tagName, final Match match)
			    throws EvaluationException {
			if (tagName != null) {
			    if (tagName.equals(AimlTags.DATE)) {
				return new SimpleEvaluationResult(
					new SimpleDateFormat()
						.format(new Date()));
			    } else if (tagName.equals(AimlTags.ID)) {
				// SEBASTIAN implement returning meaningful
				// client id.
				return new SimpleEvaluationResult("localhost");
			    } else if (tagName.equals(AimlTags.SIZE)) {
				return new SimpleEvaluationResult(Integer
					.toString(agent.getBrain()
						.getCategoryCount()));
			    } else if (tagName.equals(AimlTags.VERSION)) {
				return new SimpleEvaluationResult(
					DialogAgent.VERSION);
			    } else if (tagName.equals(AimlTags.SR)) {
				if (match.getCapturedWords().size() < 1) {
				    throw new EvaluationException(
					    "No words captured due to wildcards!");
				}
				return agent.getResponseForRequest(match
					.getCapturedWords().get(0));
			    }
			}
			return Evaluators.EMPTY_EVALUATION_RESULT;
		    }
		});
	// SCENEJO
	// this.evaluators.put(PredicateOperationImpl.class,
	// new PredicateOperationEvaluator());
	// this.evaluators.put(ChangeSceneImpl.class, new
	// ChangeSceneEvaluator());
	// this.evaluators.put(EmotionImpl.class, new Evaluator<EmotionImpl>() {
	// public EvaluationResult evaluate(final EmotionImpl xml,
	// final String tagName, final Match match) {
	// // SEBASTIAN implement!!
	// return EMPTY_EVALUATION_RESULT;
	// }
	// });
	// this.evaluators.put(GestureMNLGImpl.class,
	// new Evaluator<GestureMNLGImpl>() {
	// public EvaluationResult evaluate(final GestureMNLGImpl xml,
	// final String tagName, final Match match) {
	// // SEBASTIAN implement!!
	// return EMPTY_EVALUATION_RESULT;
	// }
	// });
	// this.evaluators.put(SentenceImpl.class, new Evaluator<SentenceImpl>()
	// {
	// public EvaluationResult evaluate(final SentenceImpl xml,
	// final String tagName, final Match match) {
	// // SEBASTIAN implement!!
	// return EMPTY_EVALUATION_RESULT;
	// }
	// });

    }

    // SCENEJO
    // private Evaluator<ActionImpl> createActionEvaluator(final DialogAgent
    // agent) {
    // return new Evaluator<ActionImpl>() {
    // public EvaluationResult evaluate(final ActionImpl xml,
    // final String tagName, final Match match)
    // throws EvaluationException {
    // // 1st check whether simple template is set
    // final MixedTemplateContentContainer template = xml
    // .getTemplate();
    // if (template != null) {
    // return Evaluators.this.evaluate(template,
    // AimlTags.TEMPLATE, match);
    // }
    // // 2nd check whether there is a reference to an abstract output
    // else if (xml.getAbstractOutput() != null) {
    // final AbstractOutput abstractOutputRef = xml
    // .getAbstractOutput();
    // final String id2 = abstractOutputRef.getId2();
    // if (id2 != null && !id2.trim().equals("")) {
    // final AbstractOutput abstractOutputForId = agent
    // .getBrain().getAbstractOutputForId(id2);
    // if (abstractOutputForId != null) {
    // return Evaluators.this.evaluate(
    // abstractOutputForId, AimlTags.TEMPLATE,
    // match);
    // }
    // }
    // if (Evaluators.LOG.isDebugEnabled()) {
    // Evaluators.LOG
    // .debug("no abstract output found for id ["
    // + id2
    // + "], handle as locally defined abstract output.");
    // }
    // // throw new EvaluationException(
    // // arg0);
    // return Evaluators.this.evaluate(abstractOutputRef,
    // AimlTags.TEMPLATE, match);
    // }
    // // 3rd check whether we'll find a condition
    // else if (xml.getIf() != null) {
    // final NewConditionBlock[] elseifArray = xml
    // .getElseifArray();
    // if (evaluate(xml.getIf().getCondition())) {
    // return Evaluators.this.evaluate(
    // xml.getIf().getAction(), "action", match);
    // } else if (elseifArray != null && elseifArray.length > 0) {
    // for (final NewConditionBlock ncb : elseifArray) {
    // if (evaluate(ncb.getCondition())) {
    // return Evaluators.this.evaluate(
    // ncb.getAction(), "action", match);
    // }
    // }
    // if (xml.getElse() != null) {
    // return Evaluators.this.evaluate(xml.getElse(),
    // "action", match);
    // }
    // } else if (xml.getElse() != null) {
    // return Evaluators.this.evaluate(xml.getElse(),
    // "action", match);
    // }
    // return null;
    // }
    // // something went wrong
    // else {
    // throw new EvaluationException("action with bad content! ["
    // + xml.xmlText() + "]");
    // }
    // }
    //
    // /**
    // * @param condition
    // * {@link Expression}
    // * @return <code>boolean</code>
    // * @throws EvaluationException
    // */
    // private final boolean evaluate(final Expression condition)
    // throws EvaluationException {
    // if (condition.isSetAnd()) {
    // final ExpressionTuple and = condition.getAnd();
    // return evaluate(and.getFirst())
    // && evaluate(and.getSecond());
    // } else if (condition.isSetOr()) {
    // final ExpressionTuple or = condition.getOr();
    // return evaluate(or.getFirst()) || evaluate(or.getSecond());
    // } else if (condition.isSetPredicateCondition()) {
    // final Condition predicateCondition = condition
    // .getPredicateCondition();
    // if (predicateCondition.isSetPredicateName()
    // && predicateCondition.isSetExpression()) {
    // final String value = agent
    // .getPredicateValue(predicateCondition
    // .getPredicateName());
    // if (value == null) {
    // throw new EvaluationException(
    // "no predicate for name ["
    // + predicateCondition
    // .getPredicateName()
    // + "] found!");
    // }
    // final String expression = predicateCondition
    // .getExpression().trim();
    // final float parseFloat = parse(value);
    // if (expression.startsWith("==")) {
    // return parseFloat == parse(expression.substring(2)
    // .trim());
    // } else if (expression.startsWith("!=")) {
    // return parseFloat != parse(expression.substring(2)
    // .trim());
    // } else if (expression.startsWith(">=")) {
    // return parseFloat >= parse(expression.substring(2)
    // .trim());
    // } else if (expression.startsWith(">")) {
    // return parseFloat > parse(expression.substring(1)
    // .trim());
    // } else if (expression.startsWith("<=")) {
    // return parseFloat <= parse(expression.substring(2)
    // .trim());
    // } else if (expression.startsWith("<")) {
    // return parseFloat < parse(expression.substring(1)
    // .trim());
    // } else {
    // throw new EvaluationException(
    // "unparsable expression [" + expression
    // + "] in predicate condition ["
    // + predicateCondition.toString()
    // + "].");
    // }
    // }
    // } else if (condition.isSetTime()) {
    // // SEBASTIAN implement
    // }
    // return false;
    // }
    //
    // /**
    // * COMMENT.
    // *
    // * @param value
    // * @return
    // * @throws EvaluationException
    // */
    // private float parse(final String value) throws EvaluationException {
    // assert value != null : "value may not be null!";
    // float parseFloat = 0;
    // try {
    // parseFloat = Float.parseFloat(value);
    // } catch (final NumberFormatException nfe) {
    // try {
    // parseFloat = Integer.parseInt(value);
    // } catch (final NumberFormatException nfe2) {
    // throw new EvaluationException(
    // "Could not parse value [value: " + value + "]");
    // }
    // }
    // return parseFloat;
    // }
    // };
    // }
    //
    // private Evaluator<BlockConditionImpl> createBlockConditionEvaluator(
    // final DialogAgent agent) {
    // return new Evaluator<BlockConditionImpl>() {
    // public EvaluationResult evaluate(final BlockConditionImpl xml,
    // final String tagName, final Match match)
    // throws EvaluationException {
    // final Condition if1 = xml.getIf();
    // if (conditionIsFullfilled(if1, agent)) {
    // return evaluateMixedContent(if1, match);
    // }
    // // SEBASTIAN correct order?
    // final Condition[] elseIfArray = xml.getElseIfArray();
    // for (final Condition element : elseIfArray) {
    // if (conditionIsFullfilled(element, agent)) {
    // return evaluateMixedContent(element, match);
    // }
    // }
    // final MixedTemplateContentContainer else1 = xml.getElse();
    // if (else1 != null) {
    // return evaluateMixedContent(else1, match);
    // }
    // return Evaluators.EMPTY_EVALUATION_RESULT;
    // }
    // };
    // }

    private Evaluator<BotPredicateImpl> createBotPredicateEvaluator(
	    final DialogAgent agent) {
	return new Evaluator<BotPredicateImpl>() {
	    public EvaluationResult evaluate(final BotPredicateImpl xml,
		    final String tagName, final Match match)
		    throws EvaluationException {
		final String string = agent.getBotProperties().get(
			xml.getName());
		if (string == null) {
		    throw new EvaluationException("Bot predicate with name ["
			    + xml.getName() + "] is not set!");
		}
		return new SimpleEvaluationResult(string);
	    }
	};
    }

    private Evaluator<MixedTemplateContentContainerImpl.ConditionImpl> createAIMLConditionEvaluator(
	    final DialogAgent agent) {
	return new Evaluator<MixedTemplateContentContainerImpl.ConditionImpl>() {
	    public EvaluationResult evaluate(
		    final MixedTemplateContentContainerImpl.ConditionImpl xml,
		    final String tagName, final Match match)
		    throws EvaluationException {
		if (aimlConditionIsFullfilled(xml, agent)) {
		    return evaluateMixedContent(xml, match);
		}
		return Evaluators.EMPTY_EVALUATION_RESULT;
	    }

	};
    }

    // SCENEJO
    // private Evaluator<ConditionImpl> createConditionEvaluator(
    // final DialogAgent agent) {
    // return new Evaluator<ConditionImpl>() {
    // public EvaluationResult evaluate(final ConditionImpl xml,
    // final String tagName, final Match match)
    // throws EvaluationException {
    // if (conditionIsFullfilled(xml, agent)) {
    // return evaluateMixedContent(xml, match);
    // }
    // return Evaluators.EMPTY_EVALUATION_RESULT;
    // }
    //
    // };
    // }

    private Evaluator<GetImpl> createGetEvaluator(final DialogAgent agent) {
	return new Evaluator<GetImpl>() {
	    public EvaluationResult evaluate(final GetImpl xml,
		    final String tagName, final Match match)
		    throws EvaluationException {
		if (xml.isSetName()) {
		    final String string = agent
			    .getPredicateValue(xml.getName());
		    if (string == null) {
			throw new EvaluationException("Predicate with name ["
				+ xml.getName() + "] is not set!");
		    }
		    return new SimpleEvaluationResult(string);
		}
		return Evaluators.EMPTY_EVALUATION_RESULT;
	    }
	};
    }

    private Evaluator<IndexedElementImpl> createIndexedElementEvaluator() {
	return new Evaluator<IndexedElementImpl>() {
	    public EvaluationResult evaluate(final IndexedElementImpl xml,
		    final String tagName, final Match match)
		    throws EvaluationException {
		assert match != null : "match may not be null to be evaluated!";
		if (match.getCapturedWords().size() < 1) {
		    return Evaluators.EMPTY_EVALUATION_RESULT;
		}
		if (tagName != null) {
		    int index = 0;
		    if (xml.isSetIndex()) {
			if (xml.getIndex().intValue() < 1) {
			    throw new EvaluationException(
				    "Inxed in indexed element may not be smaller than 1!");
			}
			index = xml.getIndex().intValue() - 1;
		    }
		    if (index > match.getCapturedWords().size() - 1) {
			throw new EvaluationException("Index to great: "
				+ (index + 1));
		    }
		    if (tagName.equals("star")) {
			return new SimpleEvaluationResult(match
				.getCapturedWords().get(index));
		    }
		}
		return Evaluators.EMPTY_EVALUATION_RESULT;
	    }
	};
    }

    private Evaluator<LearnImpl> createLearnEvaluator(final DialogAgent agent) {
	return new Evaluator<LearnImpl>() {
	    public EvaluationResult evaluate(final LearnImpl xml,
		    final String tagName, final Match match)
		    throws EvaluationException {
		final String toBeLearned = evaluateMixedContent(xml, match)
			.getText().trim();
		try {
		    final URI uri = new URI(toBeLearned);
		    File file;
		    if (uri.isAbsolute()) {
			file = new File(uri);
		    } else {
			file = new File(toBeLearned);
		    }
		    agent.load(file, false);
		} catch (final URISyntaxException e) {
		    Evaluators.LOG.error(e.getLocalizedMessage(),
			    Evaluators.LOG.isDebugEnabled() ? e : null);
		}
		return Evaluators.EMPTY_EVALUATION_RESULT;
	    }
	};
    }

    private Evaluator<MixedTemplateContentContainerImpl> createMixedTemplateContentContainerEvaluator(
	    final DialogAgent agent) {
	return new Evaluator<MixedTemplateContentContainerImpl>() {
	    public EvaluationResult evaluate(
		    final MixedTemplateContentContainerImpl xml,
		    final String tagName, final Match match)
		    throws EvaluationException {
		final EvaluationResult evaluateMixedContent = evaluateMixedContent(
			xml, match);
		if (tagName != null) {
		    if (tagName.equals(AimlTags.SRAI)) {
			return agent.getResponseForRequest(evaluateMixedContent
				.getText());
		    } else if (tagName.equals(AimlTags.UPPERCASE)) {
			// SEBASTIAN better integrate results!
			return new SimpleEvaluationResult(evaluateMixedContent
				.getText().toUpperCase());
		    } else if (tagName.equals(AimlTags.LOWERCASE)) {
			// SEBASTIAN better integrate results!
			return new SimpleEvaluationResult(evaluateMixedContent
				.getText().toLowerCase());
		    } else if (tagName.equals(AimlTags.FORMAL)) {
			// SEBASTIAN implement according to
			// http://www.unicode.org/reports/tr21/tr21-5.html
			final String[] split = evaluateMixedContent.getText()
				.split("\\s+");
			final StringBuffer stringBuffer = new StringBuffer();
			for (final String element : split) {
			    if (!element.trim().equals("")) {
				stringBuffer
					.append(element.substring(0, 1)
						.toUpperCase()
						+ element.substring(1,
							element.length()));
				stringBuffer.append(" ");
			    }
			}
			return new SimpleEvaluationResult(stringBuffer
				.toString().trim());
		    } else if (tagName.equals(AimlTags.SENTENCE)) {
			// SEBASTIAN implement according to
			// http://www.unicode.org/reports/tr21/tr21-5.html
			final String[] sentences = evaluateMixedContent
				.getText().split("\\.");
			final StringBuffer stringBuffer = new StringBuffer();
			for (final String sentence : sentences) {
			    final String trim = sentence.trim();
			    if (!trim.equals("")) {
				stringBuffer.append(trim.substring(0, 1)
					.toUpperCase()
					+ trim.substring(1, trim.length()));
				stringBuffer.append(". ");
			    }
			}
			return new SimpleEvaluationResult(stringBuffer
				.toString().trim());
		    } else if (tagName.equals(AimlTags.THINK)) {
			final EvaluationResultImpl impl = EvaluationResultImpl
				.copy(evaluateMixedContent);
			// SEBASTIAN take care on actions. later on.
			impl.setText("");
			return impl;
		    } else if (tagName.equals(AimlTags.PERSON2)) {
			if (xml.xmlText().equals(
				Evaluators.EMPTY_ELEMENT_XML_TEXT)) {
			    // element is empty!
			    if (match.getCapturedWords().size() < 1) {
				throw new EvaluationException(
					"No captured words due to wildcards!");
			    }
			    return new SimpleEvaluationResult(doPerson2(match
				    .getCapturedWords().get(0)));
			}
			// SEBASTIAN better integrate results!
			return new SimpleEvaluationResult(
				doPerson2(evaluateMixedContent.getText()));
		    } else if (tagName.equals(AimlTags.PERSON)) {
			if (xml.xmlText().equals(
				Evaluators.EMPTY_ELEMENT_XML_TEXT)) {
			    // element is empty!
			    if (match.getCapturedWords().size() < 1) {
				throw new EvaluationException(
					"No captured words due to wildcards!");
			    }
			    return new SimpleEvaluationResult(doPerson(match
				    .getCapturedWords().get(0)));
			}
			// SEBASTIAN better integrate results!
			return new SimpleEvaluationResult(
				doPerson(evaluateMixedContent.getText()));
		    } else if (tagName.equals(AimlTags.GENDER)) {
			if (xml.xmlText().equals(
				Evaluators.EMPTY_ELEMENT_XML_TEXT)) {
			    // element is empty!
			    if (match.getCapturedWords().size() < 1) {
				throw new EvaluationException(
					"No captured words due to wildcards!");
			    }
			    return new SimpleEvaluationResult(doGender(match
				    .getCapturedWords().get(0)));
			}
			// SEBASTIAN better integrate results!
			return new SimpleEvaluationResult(
				doGender(evaluateMixedContent.getText()));
		    }
		}
		return evaluateMixedContent;
	    }
	};
    }

    private Evaluator<RandomImpl> createRandomEvaluator() {
	return new Evaluator<RandomImpl>() {

	    private final HashMap<RandomImpl, Vector<Integer>> evaluatedLIs = new HashMap<RandomImpl, Vector<Integer>>();

	    private final Random randomizer = new Random();

	    public EvaluationResult evaluate(final RandomImpl xml,
		    final String tagName, final Match match)
		    throws EvaluationException {
		final MixedTemplateContentContainer[] liArray = xml
			.getLiArray();
		Vector<Integer> knownLIIndeces = this.evaluatedLIs.get(xml);
		if (knownLIIndeces == null) {
		    knownLIIndeces = new Vector<Integer>();
		    this.evaluatedLIs.put(xml, knownLIIndeces);
		}
		if (knownLIIndeces.size() >= liArray.length) {
		    knownLIIndeces.clear();
		}
		final Vector<Integer> allIndeces = new Vector<Integer>();
		for (int i = 0; i < liArray.length; i++) {
		    allIndeces.add(i);
		}
		allIndeces.removeAll(knownLIIndeces);

		final Integer selectedIndex = allIndeces.get(this.randomizer
			.nextInt(allIndeces.size()));
		knownLIIndeces.add(selectedIndex);

		return evaluateMixedContent(liArray[selectedIndex], match);
	    }
	};
    }

    private Evaluator<SetImpl> createSetEvaluator() {
	return new Evaluator<SetImpl>() {
	    public EvaluationResult evaluate(final SetImpl xml,
		    final String tagName, final Match match)
		    throws EvaluationException {
		if (xml.isSetName()) {
		    final EvaluationResult result = evaluateMixedContent(xml,
			    match);

		    // SCENEJO
		    // SEBASTIAN in scenejo we would return the predicate
		    // operation and do not set the property directly

		    // final PredicateOperation op = PredicateOperation.Factory
		    // .newInstance();
		    // op.setPredicateName(xml.getName());
		    // op.setOperator(Operator.ASSIGNMENT
		    // .getStringRepresentation());
		    // op.setValue(result.getText());

		    final EvaluationResultImpl copy = EvaluationResultImpl
			    .copy(result);
		    // copy.getPredicateOperations().add(op);

		    // SEBASTIAN implement setting the predicate directly

		    return copy;
		}
		return Evaluators.EMPTY_EVALUATION_RESULT;
	    }
	};
    }

    public EvaluationResult evaluate(final Content content, final Match match)
	    throws EvaluationException {
	return content.evaluate(this, match);
    }

    /**
     * {@inheritDoc}
     * 
     * @throws EvaluationException
     * 
     * @see de.wsdevel.elsbeth.evaluators.Evaluator#evaluate(org.apache.xmlbeans.XmlObject)
     */
    @SuppressWarnings("unchecked")
    public final EvaluationResult evaluate(final XmlObject xml,
	    final String tagName, final Match match) throws EvaluationException {
	@SuppressWarnings("rawtypes")
	final Evaluator evaluator = this.evaluators.get(xml.getClass());
	if (evaluator != null) {
	    if (Evaluators.LOG.isDebugEnabled()) {
		Evaluators.LOG.debug("Found evaluator ["
			+ evaluator.getClass().getSimpleName()
			+ "] for class [" + xml.getClass().getSimpleName()
			+ "]");
	    }
	    return evaluator.evaluate(xml, tagName, match);
	}
	Evaluators.LOG.error("Could not find an appropriate evaluator for ["
		+ xml.getClass().getCanonicalName() + "]");
	return Evaluators.EMPTY_EVALUATION_RESULT;
    }

    /**
     * COMMENT.
     * 
     * @param xml
     *            {@link XmlObject}
     * @return {@link String}
     * @throws EvaluationException
     */
    public final EvaluationResult evaluateMixedContent(final XmlObject xml,
	    final Match match) throws EvaluationException {
	int depth = 0;
	final EvaluationResultImpl result = new EvaluationResultImpl();

	final XmlCursor cursor = xml.newCursor();
	// currently at start position should go deeper!
	cursor.toNextToken();

	do {
	    if (cursor.isStartdoc()) {
		if (Evaluators.LOG.isDebugEnabled()) {
		    Evaluators.LOG.debug("STARTDOC");
		}
	    } else if (cursor.isStart()) {
		if (depth == 0) {
		    final String tagName = cursor.getName().getLocalPart();
		    if (Evaluators.LOG.isDebugEnabled()) {
			Evaluators.LOG
				.debug("START: "
					+ tagName
					+ ", classname: "
					+ cursor.getObject().getClass()
						.getSimpleName());
		    }
		    result.integrate(this.evaluate(cursor.getObject(), tagName,
			    match));
		}
		depth++;
	    } else if (cursor.isAttr()) {
		if (depth == 0) {
		    if (Evaluators.LOG.isDebugEnabled()) {
			Evaluators.LOG.debug("ATTR: "
				+ cursor.getName().getLocalPart() + ", VAL: "
				+ cursor.getTextValue());
		    }
		}
	    } else if (cursor.isText()) {
		if (depth == 0) {
		    final String text = StringHelper
			    .removeUnecessaryWhitespaceFromString(cursor
				    .getTextValue());
		    if (Evaluators.LOG.isDebugEnabled()) {
			Evaluators.LOG.debug("TEXT: " + text);
		    }
		    result.integrateText(text);
		}
	    } else if (cursor.isEnd()) {
		if (depth == 0) {
		    if (Evaluators.LOG.isDebugEnabled()) {
			Evaluators.LOG.debug("END");
		    }
		    return result;
		}
		depth--;
	    } else if (cursor.isEnddoc()) {
		if (Evaluators.LOG.isDebugEnabled()) {
		    Evaluators.LOG.debug("ENDDOC");
		}
	    }

	    if (!cursor.hasNextToken()) {
		break;
	    }
	    cursor.toNextToken();
	} while (true);

	return result;
    }

}
//
// $Log: $
//

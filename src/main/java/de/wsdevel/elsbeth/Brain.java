package de.wsdevel.elsbeth;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.TreeSet;

import org.aitools.xaiml.AimlDocument;
import org.aitools.xaiml.AimlDocument.Aiml;
import org.aitools.xaiml.AimlDocument.Aiml.Topic;
import org.aitools.xaiml.Category;
import org.aitools.xaiml.MixedPatternExpression;
import org.aitools.xaiml.MixedTemplateContentContainer;
import org.aitools.xaiml.impl.MixedTemplateContentContainerImpl;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.index.CorruptIndexException;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.LockObtainFailedException;
import org.apache.lucene.store.RAMDirectory;
import org.apache.xmlbeans.XmlException;
import org.apache.xmlbeans.XmlOptions;

import de.wsdevel.elsbeth.content.Content;
import de.wsdevel.elsbeth.content.Source;
import de.wsdevel.elsbeth.content.TemplateContent;
import de.wsdevel.elsbeth.evaluators.EvaluationException;
import de.wsdevel.elsbeth.evaluators.EvaluationResult;
import de.wsdevel.elsbeth.evaluators.Evaluators;
import de.wsdevel.elsbeth.inputnormalization.InputNormalizationService;
import de.wsdevel.elsbeth.inputnormalization.patternfitting.InvalidCharacters;

/**
 * Created on 17.09.2008.
 * 
 * for project: Elsbeth <br>
 * (c) 2007, Weiss und Schmidt, Mediale Systeme GbR - All rights reserved.
 * 
 * @author <a href="mailto:sweiss@weissundschmidt.de">Sebastian A. Weiss - Weiss
 *         und Schmidt, Mediale Systeme GbR</a>
 * @version $Author: $ -- $Revision: $ -- $Date: $
 * 
 */
public class Brain {

    private static final MatchComparator MATCH_COMPARATOR = new MatchComparator();

    /**
     * {@link String} COMMENT.
     */
    private static final String AIML_FILE_SUFFIX = ".aiml";

    /**
     * {@link String} COMMENT.
     */
    private static final String AIML_XML_FILE_SUFFIX = ".aiml.xml";

    /**
     * {@link XmlOptions} COMMENT.
     */
    public static final XmlOptions DEFAULT_XML_OPTIONS = createDefaultXMLOptions();

    /**
     * {@link Log} the logger.
     */
    private static final Log LOG = LogFactory.getLog(Brain.class);

    /**
     * {@link String} COMMENT.
     */
    private static final String LUCENE_FIELD_NAME_PATTERN = "pattern";

    /**
     * {@link String} COMMENT.
     */
    private static final String LUCENE_FIELD_NAME_SOURCE_FILENAME = "source.filename";

    /**
     * {@link String} COMMENT.
     */
    public static final String LUCENE_FIELD_NAME_CONTENT_TEMPLATE = "template";

    /**
     * {@link String} COMMENT.
     */
    public static final String LUCENE_FIELD_NAME_CONTENT_ACTION = "action";

    // SCENEJO
    // /**
    // * {@link String} COMMENT.
    // */
    // private static final String SCAIML_FILE_SUFFIX = ".scaiml";
    //
    // /**
    // * {@link String} COMMENT.
    // */
    // private static final String SCAIML_XML_FILE_SUFFIX = ".scaiml.xml";

    /**
     * {@link String} COMMENT.
     */
    private static final String SOURCE_DIRECT = "DIRECT";

    // /**
    // * {@link String} '<that>'.
    // */
    // private static final String THAT = "<that>";
    //
    // /**
    // * {@link String} '<topic>'.
    // */
    // private static final String TOPIC = "<topic>";

    /**
     * {@link String} '*'.
     */
    public static final String STAR = "*";

    /**
     * {@link String} '_'.
     */
    private static final String UNDERSCORE = "_";

    // SCENEJO
    // /**
    // * {@link String} COMMENT.
    // */
    // public static final String LUCENE_FIELD_NAME_SOURCE_SRE_ID =
    // "source.sreid";

    /**
     * COMMENT.
     * 
     * @return {@link XmlOptions}
     */
    private static XmlOptions createDefaultXMLOptions() {
	final XmlOptions options = new XmlOptions();
	options.setCharacterEncoding("UTF-8");
	options.setSavePrettyPrint();
	options.setSavePrettyPrintIndent(4);
	options.setUseDefaultNamespace();
	options.setSaveAggressiveNamespaces();
	return options;
    }

    /**
     * <code>int</code> current number of categories loaded.
     */
    private int categoryCount = 0;

    /**
     * {@link Evaluators} COMMENT.
     */
    private final Evaluators evaluators;

    /**
     * {@link RAMDirectory} COMMENT.
     */
    private final RAMDirectory luceneIndex;

    /**
     * {@link QueryParser} COMMENT.
     */
    private final QueryParser queryParser;

    /**
     * {@link BrainNode} root node of brain.
     */
    private BrainNode root;

    /**
     * {@link String} the id of the next SRE element to be preferred while
     * matching.
     */
    private String nextPreferredSREId = null;

    // SCENEJO
    // /**
    // * {@link HashMap<String,AbstractOutput>} a hash map containing all global
    // * abstract outputs.
    // */
    // private final HashMap<String, AbstractOutput> abstractOutputRegister =
    // new HashMap<String, AbstractOutput>();

    /**
     * ${InputNormalization} inputNormalizationService
     */
    private InputNormalizationService inputNormalizationService = null;

    /**
     * Default constructor.
     */
    public Brain(final Evaluators evaluatorsRef, final Locale locale) {
	setRoot(new BrainNode(null, null));
	this.evaluators = evaluatorsRef;
	this.inputNormalizationService = new InputNormalizationService(locale);
	this.luceneIndex = new RAMDirectory();
	this.queryParser = new QueryParser(Brain.LUCENE_FIELD_NAME_PATTERN,
		new StandardAnalyzer());
    }

    /**
     * COMMENT.
     * 
     * @param aiml
     *            {@link ScenejoAIML}
     * @param node
     *            {@link BrainNode}
     */
    private void addCategroyForNode(final Aiml aiml, final BrainNode node) {
	for (final Content content : node.getContents()) {
	    if (content instanceof TemplateContent) {
		final Category cat = aiml.addNewCategory();
		final MixedPatternExpression pattern = cat.addNewPattern();

		final List<String> pfr = node.createPathFromRoot();
		final StringBuffer buffer = new StringBuffer();
		for (final String string : pfr) {
		    buffer.append(string + " ");
		}
		pattern.newCursor().setTextValue(buffer.toString().trim());
		cat.setTemplate(((TemplateContent) content).getTemplate());
	    }
	}
	// SEBASTIAN what about action contents? That does not work for SREs!

	if (node.hasChildren()) {
	    final List<BrainNode> children = node.getChildren();
	    for (final BrainNode brainNode : children) {
		addCategroyForNode(aiml, brainNode);
	    }
	}
    }

    /**
     * COMMENT.
     * 
     * @param input
     *            {@link String}
     * @param results
     *            {@link TreeSet}< {@link Match}>
     */
    private void addLuceneMatchesToResults(final String input,
	    final List<Match> results) {
	try {
	    if (this.luceneIndex.list().length < 1) {
		// there is nothing in the index until now! (sw 20090517)
		return;
	    }
	    final IndexReader reader = IndexReader.open(this.luceneIndex);
	    final IndexSearcher indexSearcher = new IndexSearcher(reader);
	    final String clearedInput = InvalidCharacters.clear(input);
	    if (clearedInput.trim().equals("")) {
		return;
	    }
	    final Query parse = this.queryParser.parse(clearedInput);
	    final TopDocs search = indexSearcher.search(parse, 50);
	    for (final ScoreDoc scoreDoc : search.scoreDocs) {
		final float score = scoreDoc.score;
		final Document document = reader.document(scoreDoc.doc);
		final Match m = new Match();
		m.setScore(score);
		try {
		    final Field template = document
			    .getField(Brain.LUCENE_FIELD_NAME_CONTENT_TEMPLATE);
		    // SCENEJO
		    // final Field action = document
		    // .getField(Brain.LUCENE_FIELD_NAME_CONTENT_ACTION);
		    final Field sourceFilename = document
			    .getField(Brain.LUCENE_FIELD_NAME_SOURCE_FILENAME);
		    assert sourceFilename != null : "source.filename has to be set";
		    // SCENEJO
		    // final Field sourceSREId = document
		    // .getField(Brain.LUCENE_FIELD_NAME_SOURCE_SRE_ID);
		    final Source sourceVal = new Source(
			    sourceFilename.stringValue()
		    // SCENEJO
		    // sourceSREId != null ? sourceSREId.stringValue()
		    // : null
		    );
		    if (template != null) {
			m.setContent(new TemplateContent(sourceVal,
				MixedTemplateContentContainer.Factory
					.parse(template.stringValue())));
			// SCENEJO
			// } else if (action != null) {
			// m.setContent(new ActionContent(sourceVal,
			// Action.Factory.parse(action.stringValue())));
		    }
		} catch (final XmlException e) {
		    Brain.LOG.error(e.getLocalizedMessage(), e);
		}
		results.add(m);
	    }
	    reader.close();
	} catch (final CorruptIndexException e) {
	    Brain.LOG.error(e.getLocalizedMessage(), e);
	} catch (final IOException e) {
	    Brain.LOG.error(e.getLocalizedMessage(), e);
	} catch (final ParseException e) {
	    Brain.LOG.error(e.getLocalizedMessage(), e);
	}
    }

    /**
     * @param currentTopic
     *            {@link String}
     * @param results
     *            {@link TreeSet}< {@link Match}>
     * @param capturedWords
     *            {@link LinkedList}< {@link String}>
     * @param node
     *            {@link BrainNode}
     */
    private void addMatchesConcerningTopicInformation(
	    final String currentTopic, final List<Match> results,
	    final LinkedList<String> capturedWords, final BrainNode node) {

	final List<String> pathFromRoot = node.createPathFromRoot();

	if (currentTopic != null && !currentTopic.trim().equals("")) {
	    for (final Content content : node.getContents()) {
		final LinkedList<String> topics = content.getTopics();
		final Match match = createMatchForContent(capturedWords,
			pathFromRoot, currentTopic, null, content);
		// SEBASTIAN how to score off topic match?
		match.setScore(0.5f);
		overTopics: for (final String topic : topics) {
		    if (topic.equals(currentTopic)) {
			match.setScore(1);
			break overTopics;
		    }
		}
		results.add(match);
	    }
	} else {
	    for (final Content content : node.getContents()) {
		final Match match = createMatchForContent(capturedWords,
			pathFromRoot, null, null, content);
		// if no current topic is set, every match is equally relevant
		// (sw)
		match.setScore(1);
		results.add(match);
	    }
	}
    }

    /**
     * @param rootRef
     *            {@link BrainNode} to start from
     * @param pattern
     *            {@link String} to be added as path of nodes
     * @return {@link BrainNode} the resulting leaf at the end of the created
     *         path.
     */
    private BrainNode addPathToNode(final BrainNode rootRef,
	    final String pattern, final String that) {
	BrainNode currentNode = rootRef;
	final LinkedList<String> wordsCombined = createFullPath(pattern, that);

	for (final String word : wordsCombined) {
	    currentNode = currentNode.getOrCreateNextNodeForWord(word);
	}

	return currentNode;
    }

    /**
     * @param pattern
     *            {@link String}
     * @param that
     *            {@link String}
     * @return {@link LinkedList}< {@link String}>
     */
    private LinkedList<String> createFullPath(final String pattern,
	    final String that) {
	final Collection<String> wordsPattern = this.inputNormalizationService
		.getWordsForPattern(pattern);
	final Collection<String> wordsThat = this.inputNormalizationService
		.getWordsForPattern(that);

	final LinkedList<String> wordsCombined = new LinkedList<String>();
	wordsCombined.addAll(wordsPattern);
	wordsCombined.add("<that>");
	wordsCombined.addAll(wordsThat);
	return wordsCombined;
    }

    /**
     * @param content
     *            {@link MixedTemplateContentContainerImpl}
     * @param pattern
     *            {@link String}
     * @return {@link Document}
     */
    private Document createLuceneDocumentForPattern(final Content content,
	    final String pattern) {
	if (content == null) {
	    throw new NullPointerException("template may not be null!");
	}
	final Document doc = new Document();
	doc.add(new Field(Brain.LUCENE_FIELD_NAME_PATTERN, pattern,
		Field.Store.YES, Field.Index.ANALYZED));
	doc.add(new Field(Brain.LUCENE_FIELD_NAME_SOURCE_FILENAME, content
		.getSource().getFileName(), Field.Store.YES,
		Field.Index.NOT_ANALYZED));
	// SCENEJO
	// if (content.getSource().getSreId() != null) {
	// doc.add(new Field(Brain.LUCENE_FIELD_NAME_SOURCE_SRE_ID, content
	// .getSource().getSreId(), Field.Store.YES,
	// Field.Index.NOT_ANALYZED));
	// }

	content.storeInLuceneDocument(doc);
	return doc;
    }

    /**
     * @param capturedWords
     *            {@link LinkedList}< {@link String}>
     * @param node
     *            {@link BrainNode}
     * @return {@link Match}
     */
    private Match createMatchForContent(final List<String> capturedWords,
	    final List<String> pathFromRoot, final String topic,
	    final MixedPatternExpression that, final Content content) {
	final Match match = new Match();
	match.setTopic(topic);
	match.setThat(that);
	match.setContent(content);
	match.setPath(pathFromRoot);
	match.setCapturedWords(capturedWords);
	return match;
    }

    /**
     * COMMENT.
     * 
     * @param dumpFile
     *            {@link File}
     */
    public final void dump(final File dumpFile) {
	final AimlDocument doc = AimlDocument.Factory.newInstance();
	final Aiml aiml = doc.addNewAiml();
	// SCENEJO
	// final ScenejoAIMLDocument doc = ScenejoAIMLDocument.Factory
	// .newInstance();
	// final ScenejoAIML scenejoAIML = doc.addNewScenejoAIML();
	addCategroyForNode(aiml, getRoot());
	try {
	    doc.save(dumpFile, Brain.DEFAULT_XML_OPTIONS);
	} catch (final IOException e) {
	    Brain.LOG.error(e.getLocalizedMessage(),
		    Brain.LOG.isDebugEnabled() ? e : null);
	}
    }

    // SCENEJO
    // /**
    // * @param id
    // * {@link String}
    // * @return {@link AbstractOutput} found in register for given id.
    // */
    // public AbstractOutput getAbstractOutputForId(final String id) {
    // return this.abstractOutputRegister.get(id);
    // }

    /**
     * @return <code>int</code> the categoryCount.
     */
    public final int getCategoryCount() {
	return this.categoryCount;
    }

    /**
     * @return {@link String} the nextPreferredSREId.
     */
    public final String getNextPreferredSREId() {
	return this.nextPreferredSREId;
    }

    /**
     * COMMENT.
     * 
     * @param rest
     *            {@link LinkedList}< {@link String}>
     * @return {@link String}
     */
    private String getPlainRest(final LinkedList<String> rest) {
	String plain = "";
	final Iterator<String> iterator = rest.iterator();
	while (iterator.hasNext()) {
	    plain += " " + iterator.next();
	}
	return plain;
    }

    /**
     * @return {@link BrainNode} the root.
     */
    public final BrainNode getRoot() {
	return this.root;
    }

    /**
     * COMMENT.
     * 
     * @param aiml
     *            {@link Aiml}
     */
    public void load(final Aiml aiml) {
	load(aiml, Brain.SOURCE_DIRECT, false);
    }

    /**
     * COMMENT.
     * 
     * @param aiml
     *            {@link Aiml}
     * @param source
     *            {@link String}
     */
    public boolean load(final Aiml aiml, final String source,
	    final boolean overwrite) {
	try {
	    final Topic[] topicArray = aiml.getTopicArray();
	    for (final Topic element : topicArray) {
		loadCategories(element.getCategoryArray(), element.getName(),
			source, overwrite);
	    }
	    // load categories without topic information
	    loadCategories(aiml.getCategoryArray(), Brain.STAR, source,
		    overwrite);
	} catch (final Throwable t) {
	    Brain.LOG.error(t.getLocalizedMessage(), t);
	    return false;
	}
	return true;
    }

    /**
     * COMMENT.
     * 
     * @param doc
     *            {@link File}
     */
    public boolean load(final File doc, final boolean overwrite) {
	if (doc == null || !doc.exists()) {
	    Brain.LOG.error("file is null or does not exist ["
		    + (doc != null ? doc.getAbsolutePath() : null) + "].");
	    return false;
	}
	try {
	    if (doc.getName().endsWith(Brain.AIML_FILE_SUFFIX)
		    || doc.getName().endsWith(Brain.AIML_XML_FILE_SUFFIX)) {
		return load(AimlDocument.Factory.parse(doc).getAiml(), doc
			.toURI().toString(), overwrite);
		// SCENEJO
		// } else if (doc.getName().endsWith(Brain.SCAIML_FILE_SUFFIX)
		// || doc.getName().endsWith(Brain.SCAIML_XML_FILE_SUFFIX)) {
		// return load(ScenejoAIMLDocument.Factory.parse(doc)
		// .getScenejoAIML(), doc.toURI().toString(), overwrite);
	    } else {
		Brain.LOG
			.error("Somebody tried to load file with unknown extension ["
				+ doc.getName() + "]");
	    }
	} catch (final Throwable e) {
	    Brain.LOG.error(e.getLocalizedMessage(),
		    Brain.LOG.isDebugEnabled() ? e : null);
	}
	return false;
    }

    // SCENEJO
    // /**
    // * COMMENT.
    // *
    // * @param scenejoAIML
    // * {@link ScenejoAIML}
    // */
    // public boolean load(final ScenejoAIML scenejoAIML) {
    // return load(scenejoAIML, Brain.SOURCE_DIRECT, false);
    // }

    // SCENEJO
    // /**
    // * COMMENT.
    // *
    // * @param aiml
    // * {@link Aiml}
    // */
    // public boolean load(final ScenejoAIML scenejoAIML, final String source,
    // final boolean overwrite) {
    // try {
    //
    // // SCENEJO
    // // store global abstract output definitions in register
    // // final AbstractOutputDefinitions abstractOutputDefinitons =
    // // scenejoAIML
    // // .getAbstractOutputDefinitions();
    // // if (abstractOutputDefinitons != null) {
    // // for (final AbstractOutput aos : abstractOutputDefinitons
    // // .getAbstractOutputArray()) {
    // // this.abstractOutputRegister.put(aos.getId2(), aos);
    // // // SEBASTIAN store abstract outputs with source information!
    // // }
    // // }
    //
    // for (final org.scenejo.scenejoAIML.v111.Topic topic : scenejoAIML
    // .getTopicArray()) {
    // loadCategories(topic.getCategoryArray(), topic.getName(),
    // source, overwrite);
    // // SEBASTIAN load SRE for topic!
    // loadSREs(topic.getSreArray(), source, topic.getName());
    // }
    // // load sres topic information
    // loadSREs(scenejoAIML.getSreArray(), source, null);
    // // load categories without topic information
    // loadCategories(scenejoAIML.getCategoryArray(), Brain.STAR, source,
    // overwrite);
    //
    // // for (final Action action : scenejoAIML.getActionArray()) {
    // // final PreCondition pre = action.getPre();
    // //
    // // final ExpressionTuple and = pre.getAnd();
    // // final ExpressionTuple or = pre.getOr();
    // // final Condition condition = pre.getCondition();
    // // final String linkToAbstractPattern =
    // // pre.getLinkToAbstractPattern();
    // // final MixedPatternExpression pattern = pre.getPattern();
    // // final TimeCondition time = pre.getTime();
    // //
    // // // and.get
    // //
    // // }
    // } catch (final Throwable t) {
    // Brain.LOG.error(t.getLocalizedMessage(), t);
    // unload(source);
    // return false;
    // }
    // return true;
    // }

    /**
     * COMMENT.
     * 
     * @param categoryArray
     *            {@link Category}[]
     * @param topicName
     *            {@link String}
     */
    private void loadCategories(final Category[] categoryArray,
	    final String topicName, final String source, final boolean overwrite) {
	for (final Category element : categoryArray) {
	    if (Brain.LOG.isDebugEnabled()) {
		Brain.LOG.debug("going to load category with pattern ["
			+ element.getPattern() + "] ...");
	    }
	    loadCategory(element, topicName, source);
	}
    }

    /**
     * COMMENT.
     * 
     * @param category
     *            {@link Category}
     * @param topic
     *            {@link String}
     */
    private void loadCategory(final Category category, final String topic,
	    final String source) {
	if (category.getTemplate() == null) {
	    Brain.LOG
		    .error("template for pattern [" + category.getPattern() != null ? category
			    .getPattern().xmlText() : "" + "] is null!");
	    return;
	}

	final TemplateContent content = new TemplateContent(new Source(source),
		category.getTemplate());
	if (topic != null && !topic.trim().equals("")) {
	    content.addTopic(topic);
	}
	final MixedPatternExpression that = category.getThat();
	if (that != null) {
	    content.addThat(that);
	}

	storeContentForPattern(category.getPattern(), that, content);
	this.categoryCount++;
    }

    // SCENEJO
    // /**
    // * COMMENT.
    // *
    // * @param sreArray
    // * {@link StimulusResponseElement}[]
    // * @param source
    // * {@link String}
    // */
    // private void loadSREs(final StimulusResponseElement[] sreArray,
    // final String source, final String topic) {
    // for (final StimulusResponseElement sre : sreArray) {
    // for (final AbstractAct act : sre.getInputOptions()
    // .getAbstractActArray()) {
    // for (final MixedPatternExpression pattern : act.getPatterns()
    // .getPatternArray()) {
    // final ActionContent actionContent = new ActionContent(
    // new Source(source, sre.getId()), act.getAction());
    // if (topic != null && !topic.trim().equals("")) {
    // actionContent.addTopic(topic);
    // }
    // storeContentForPattern(pattern, null, actionContent);
    // }
    // }
    // }
    // }

    /**
     * COMMENT.
     * 
     * @param inputList
     *            {@link LinkedList}
     * @param currentNode
     *            {@link BrainNode}
     * @param capturedWords
     * @return {@link BrainNode}
     */
    private BrainNode match(final LinkedList<String> inputList,
	    final BrainNode currentNode, final List<String> capturedWords) {
	if (inputList.size() > 0) {
	    final LinkedList<String> rest = new LinkedList<String>(inputList);
	    final String word = rest.removeFirst();

	    BrainNode result = tryUnderscore(currentNode, word, rest,
		    capturedWords);
	    if (result == null || result.getContents().isEmpty()) {
		result = tryWord(currentNode, word, rest, capturedWords);
		if (result == null || result.getContents().isEmpty()) {
		    result = tryStar(currentNode, word, rest, capturedWords);
		}
	    }
	    return result;
	}
	return currentNode;
    }

    /**
     * @param input
     *            {@link String}
     * @return {@link MixedTemplateContentContainer} template found or
     *         <code>null</code>.
     */
    public final List<Match> match(final String input,
	    final String currentThat, final String currentTopic) {
	final LinkedList<Match> results = new LinkedList<Match>();
	final LinkedList<String> inputList = createFullPath(input, currentThat);

	if (Brain.LOG.isDebugEnabled()) {
	    Brain.LOG.debug("input list to be matched: " + inputList);
	}
	final LinkedList<String> capturedWords = new LinkedList<String>();
	final BrainNode node = match(inputList, getRoot(), capturedWords);
	Collections.reverse(capturedWords);
	if (node != null) {
	    if (Brain.LOG.isDebugEnabled()) {
		Brain.LOG
			.debug("path for result: " + node.createPathFromRoot());
	    }
	    addMatchesConcerningTopicInformation(currentTopic, results,
		    capturedWords, node);
	}
	addLuceneMatchesToResults(input, results);
	// SCENEJO
	// updateMatchScoreConcerningNextPrefferedSRE(results);

	Collections.sort(results, Brain.MATCH_COMPARATOR);
	return results;
    }

    /**
     * @param nextPreferredSREIdVal
     *            {@link String} the nextPreferredSREId to set.
     */
    public final void setNextPreferredSREId(final String nextPreferredSREIdVal) {
	this.nextPreferredSREId = nextPreferredSREIdVal;
    }

    /**
     * @param rootRef
     *            {@link BrainNode} the root to set.
     */
    public final void setRoot(final BrainNode rootRef) {
	this.root = rootRef;
    }

    /**
     * COMMENT.
     * 
     * @param patternExpression
     *            {@link MixedPatternExpression}
     * @param content
     *            {@link MixedTemplateContentContainer}
     * @param source
     *            {@link String}
     */
    private void storeContentForPattern(
	    final MixedPatternExpression patternExpression,
	    final MixedPatternExpression thatExpression, final Content content) {
	if (content == null) {
	    throw new NullPointerException("template may not be null!");
	}
	EvaluationResult resultPattern;
	EvaluationResult resultThat = null;
	try {
	    // SEBASTIAN loads category for evaluated bot property!!!
	    // match can be null, want be needed.
	    resultPattern = this.evaluators.evaluateMixedContent(
		    patternExpression, null);
	    if (thatExpression != null) {
		resultThat = this.evaluators.evaluateMixedContent(
			thatExpression, null);
	    }
	} catch (final EvaluationException e) {
	    Brain.LOG.error(e.getLocalizedMessage(),
		    Brain.LOG.isDebugEnabled() ? e : null);
	    return;
	}

	final String pattern = resultPattern.getText().trim();
	final String that = resultThat != null ? resultThat.getText().trim()
		: "";

	if (pattern.equals(Brain.UNDERSCORE)) {
	    // SEBASTIAN ignore category
	    Brain.LOG.warn("Single underscore found as pattern!");
	    return;
	    // throw new IllegalArgumentException(
	    // "The pattern may not be a single underscore!!!");
	}

	storeInBrain(patternExpression, pattern, thatExpression, that, content);
	storeInLucene(content, pattern);
    }

    /**
     * COMMENT.
     * 
     * @param patternExpression
     *            {@link MixedPatternExpression}
     * @param pattern
     *            {@link String}
     * @param template
     *            {@link MixedTemplateContentContainerImpl}
     * @param source
     *            {@link String}
     */
    private void storeInBrain(final MixedPatternExpression patternExpression,
	    final String pattern, final MixedPatternExpression thatExpression,
	    final String that, final Content content) {

	BrainNode currentNode = getRoot();
	currentNode = addPathToNode(currentNode, pattern, that);

	// if (currentNode.getContent() != null && !overwrite) {
	// Brain.LOG.warn("Template for category pattern ["
	// + patternExpression + "] has allready been loaded from ["
	// + currentNode.getSource() + "]! New template from ["
	// + source + "] will be ignored!");
	// // SEBASTIAN maybe integrate templates?
	// return;
	// }
	currentNode.addContent(content);

	if (Brain.LOG.isDebugEnabled()) {
	    Brain.LOG.debug("Stored template at path: " + currentNode);
	}
    }

    /**
     * COMMENT.
     * 
     * @param content
     *            {@link MixedTemplateContentContainer}
     * @param pattern
     *            {@link String}
     */
    private void storeInLucene(final Content content, final String pattern) {
	if (content == null) {
	    throw new NullPointerException();
	}
	final Document doc = createLuceneDocumentForPattern(content, pattern);
	try {
	    final IndexWriter luceneIndexWriter = new IndexWriter(
		    this.luceneIndex, new StandardAnalyzer(), true,
		    IndexWriter.MaxFieldLength.UNLIMITED);
	    luceneIndexWriter.addDocument(doc);
	    luceneIndexWriter.optimize();
	    luceneIndexWriter.close();
	} catch (final CorruptIndexException e) {
	    Brain.LOG.error(e.getLocalizedMessage(),
		    Brain.LOG.isDebugEnabled() ? e : null);
	} catch (final LockObtainFailedException e) {
	    Brain.LOG.error(e.getLocalizedMessage(),
		    Brain.LOG.isDebugEnabled() ? e : null);
	} catch (final IOException e) {
	    Brain.LOG.error(e.getLocalizedMessage(),
		    Brain.LOG.isDebugEnabled() ? e : null);
	}

    }

    /**
     * COMMENT.
     * 
     * @param currentNode
     * @param word
     * @param rest
     * @param capturedWords
     * @return
     */
    private BrainNode tryStar(final BrainNode currentNode, final String word,
	    final LinkedList<String> rest, final List<String> capturedWords) {
	return tryWildcard(currentNode, word, rest, capturedWords, Brain.STAR);
    }

    /**
     * COMMENT.
     * 
     * @param currentNode
     * @param word
     * @param rest
     * @param capturedWords
     * @return
     */
    private BrainNode tryUnderscore(final BrainNode currentNode,
	    final String word, final LinkedList<String> rest,
	    final List<String> capturedWords) {
	return tryWildcard(currentNode, word, rest, capturedWords,
		Brain.UNDERSCORE);
    }

    /**
     * COMMENT.
     * 
     * @param currentNode
     *            {@link BrainNode}
     * @param rest
     *            {@link LinkedList}< {@link String}>
     * @param capturedWords
     * @return {@link BrainNode}
     */
    private BrainNode tryWildcard(final BrainNode currentNode,
	    final String word, final LinkedList<String> rest,
	    final List<String> capturedWords, final String wildcard) {
	final BrainNode nextNode = currentNode.getNextNodeForWord(wildcard);
	if (nextNode != null) {
	    if (nextNode.hasChildren()) {
		String captured = word;
		final LinkedList<String> restCopy = new LinkedList<String>(rest);
		while (restCopy.size() > 0) {
		    final BrainNode match = match(restCopy, nextNode,
			    capturedWords);
		    if (match != null) {
			capturedWords.add(captured);
			return match;
		    }
		    captured += " " + restCopy.removeFirst();
		}
		capturedWords.add(captured);
		return nextNode;
	    }
	    capturedWords.add(word + getPlainRest(rest));
	    return nextNode;
	}
	return null;
    }

    /**
     * COMMENT.
     * 
     * @param currentNode
     *            {@link BrainNode}
     * @param word
     *            {@link String}
     * @param rest
     *            {@link LinkedList}< {@link String}>
     * @param capturedWords
     *            {@link List}
     * @return {@link BrainNode}
     */
    private BrainNode tryWord(final BrainNode currentNode, final String word,
	    final LinkedList<String> rest, final List<String> capturedWords) {
	BrainNode nextNode = currentNode.getNextNodeForWord(word);
	if (nextNode != null) {
	    nextNode = match(rest, nextNode, capturedWords);
	}
	return nextNode;
    }

    /**
     * @param source
     *            {@link String}
     * @return <code>boolean true</code> if contents for source were
     *         successfully unloaded.
     */
    public boolean unload(final String source) {
	// SEBASTIAN to be implemented!
	return false;
    }

    // SCENEJO
    // /**
    // * COMMENT.
    // *
    // * @param results
    // * {@link List}< {@link Match}>
    // */
    // private void updateMatchScoreConcerningNextPrefferedSRE(
    // final LinkedList<Match> results) {
    // if (this.nextPreferredSREId != null
    // && !this.nextPreferredSREId.trim().equals("")) {
    // for (final Match match : results) {
    // if (this.nextPreferredSREId.equals(match.getContent()
    // .getSource().getSreId())) {
    // match.setScore(1.0f);
    // } else {
    // match.setScore(match.getScore() * 0.8f);
    // }
    // }
    // }
    // }

}
//
// $Log: $
//

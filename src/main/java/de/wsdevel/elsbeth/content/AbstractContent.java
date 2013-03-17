package de.wsdevel.elsbeth.content;

import java.util.LinkedList;

import org.aitools.xaiml.MixedPatternExpression;


/**
 * Created on 21.11.2010 for project: Elsbeth. (c) 2010, Sebastian A. Weiss -
 * All rights reserved.
 * 
 * @author <a href="mailto:sebastian@scenejo.org">Sebastian A. Weiss -
 *         scenejo.org</a>
 */
public abstract class AbstractContent implements Content {

    /**
     * {@link LinkedList<MixedPatternExpression>} thats this node is belonging
     * to.
     */
    private final LinkedList<MixedPatternExpression> thats = new LinkedList<MixedPatternExpression>();
    /**
     * {@link LinkedList<String>} topics this node is belonging to.
     */
    private final LinkedList<String> topics = new LinkedList<String>();

    /**
     * {@link Source} COMMENT.
     */
    private Source source = null;

    /**
     * Default constructor.
     */
    public AbstractContent() {
	this(null);
    }

    /**
     * @param sourceVal
     *            {@link Source}
     */
    public AbstractContent(final Source sourceVal) {
	setSource(sourceVal);
    }

    /**
     * @param thatVal
     *            {@link String} to be added.
     */
    public final void addThat(final MixedPatternExpression thatVal) {
	this.thats.add(thatVal);
    }

    /**
     * @param topicVal
     *            {@link String} topic to be added.
     */
    public final void addTopic(final String topicVal) {
	this.topics.add(topicVal);
    }

    /**
     * @return {@link Source} the source.
     */
    public final Source getSource() {
	return this.source;
    }

    /**
     * @return {@link LinkedList<MixedPatternExpression>} the thats.
     */
    public final LinkedList<MixedPatternExpression> getThats() {
	return this.thats;
    }

    /**
     * @return {@link LinkedList<String>} the topics.
     */
    public final LinkedList<String> getTopics() {
	return this.topics;
    }

    /**
     * @param thatVal
     *            {@link MixedPatternExpression} to be removed.
     */
    public final void removeThat(final MixedPatternExpression thatVal) {
	this.thats.remove(thatVal);
    }

    /**
     * @param topicVal
     *            {@link String} topic to be removed.
     */
    public final void removeTopic(final String topicVal) {
	this.topics.remove(topicVal);
    }

    /**
     * @param sourceVal
     *            {@link Source} the source to set.
     */
    public final void setSource(final Source sourceVal) {
	this.source = sourceVal;
    }

}
//
// $Log: $
//

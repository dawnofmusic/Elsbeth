package de.wsdevel.elsbeth;

import java.util.LinkedList;
import java.util.List;

import org.aitools.xaiml.MixedPatternExpression;

import de.wsdevel.elsbeth.content.Content;

/**
 * Created on 19.09.2008.
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
public class Match {

    /**
     * {@link List<String>} COMMENT.
     */
    private List<String> capturedWords = new LinkedList<String>();

    /**
     * {@link List}<String> COMMENT.
     */
    private List<String> path = new LinkedList<String>();

    /**
     * <code>float</code> COMMENT.
     */
    private float score = 0;

    /**
     * {@link Content} COMMENT.
     */
    private Content content;

    /**
     * {@link String} COMMENT.
     */
    private String topic = null;

    /**
     * {@link MixedPatternExpression} COMMENT.
     */
    private MixedPatternExpression that = null;

    /**
     * @param obj
     * @return
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(final Object obj) {
	if (this == obj) {
	    return true;
	}
	if (obj == null) {
	    return false;
	}
	if (getClass() != obj.getClass()) {
	    return false;
	}
	final Match other = (Match) obj;
	if (this.content == null) {
	    if (other.content != null) {
		return false;
	    }
	} else if (!this.content.equals(other.content)) {
	    return false;
	}
	if (this.path == null) {
	    if (other.path != null) {
		return false;
	    }
	} else if (!this.path.equals(other.path)) {
	    return false;
	}
	if (this.that == null) {
	    if (other.that != null) {
		return false;
	    }
	} else if (!this.that.equals(other.that)) {
	    return false;
	}
	if (this.topic == null) {
	    if (other.topic != null) {
		return false;
	    }
	} else if (!this.topic.equals(other.topic)) {
	    return false;
	}
	return true;
    }

    /**
     * @return {@link List<String>} the capturedWords.
     */
    public final List<String> getCapturedWords() {
	return this.capturedWords;
    }

    /**
     * @return {@link Content} the template.
     */
    public final Content getContent() {
	return this.content;
    }

    /**
     * @return {@link List<String>} the path.
     */
    public final List<String> getPath() {
	return this.path;
    }

    /**
     * @return {@link float} the score.
     */
    public final float getScore() {
	return this.score;
    }

    /**
     * @return {@link MixedPatternExpression} the that.
     */
    public final MixedPatternExpression getThat() {
	return this.that;
    }

    /**
     * @return {@link String} the topic. Can be <code>null</code>.
     */
    public final String getTopic() {
	return this.topic;
    }

    /**
     * @return
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result
		+ (this.content == null ? 0 : this.content.hashCode());
	result = prime * result
		+ (this.path == null ? 0 : this.path.hashCode());
	result = prime * result
		+ (this.that == null ? 0 : this.that.hashCode());
	result = prime * result
		+ (this.topic == null ? 0 : this.topic.hashCode());
	return result;
    }

    /**
     * @param capturedWordsRef
     *            {@link List<String>} the capturedWords to set.
     */
    public final void setCapturedWords(final List<String> capturedWordsRef) {
	this.capturedWords = capturedWordsRef;
    }

    /**
     * @param templateRef
     *            {@link Content} the template to set.
     */
    public final void setContent(final Content templateRef) {
	this.content = templateRef;
    }

    /**
     * @param pathRef
     *            {@link List<String>} the path to set.
     */
    public final void setPath(final List<String> pathRef) {
	this.path = pathRef;
    }

    /**
     * @param scoreVal
     *            {@link float} the score to set.
     */
    public final void setScore(final float scoreVal) {
	this.score = scoreVal;
    }

    /**
     * @param thatRef
     *            {@link MixedPatternExpression} the that to set.
     */
    public final void setThat(final MixedPatternExpression thatRef) {
	this.that = thatRef;
    }

    /**
     * @param topicVal
     *            {@link String} the topic to set.
     */
    public final void setTopic(final String topicVal) {
	this.topic = topicVal;
    }

    /**
     * @return {@link String}
     * @see java.lang.Object#toString()
     */
    @Override
    public final String toString() {
	return getClass().getSimpleName() + "[score: " + getScore()
		+ ", template: " + getContent().getStringRepresentation()
		+ ", topic: " + getTopic() + "]";
    }

}
//
// $Log: $
//

package de.wsdevel.elsbeth;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import de.wsdevel.elsbeth.content.Content;

/**
 * Created on 17.09.2008.
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
public class BrainNode {

    /**
     * {@link HashMap<String,BrainNode>} COMMENT.
     */
    private final HashMap<String, BrainNode> children = new HashMap<String, BrainNode>();

    /**
     * {@link BrainNode} COMMENT.
     */
    private BrainNode parentNode;

    /**
     * {@link LinkedList<Content>} COMMENT.
     */
    private LinkedList<Content> contents = new LinkedList<Content>();

    /**
     * {@link String} COMMENT.
     */
    private String word;

    /**
     * COMMENT.
     * 
     * @param parentRef
     *            {@link BrainNode}
     * @param wordVal
     *            {@link String}
     */
    public BrainNode(final BrainNode parentRef, final String wordVal) {
	setParentNode(parentRef);
	setWord(wordVal);
    }

    /**
     * @param contentRef
     *            {@link Content} the template to set.
     */
    public final void addContent(final Content contentRef) {
	this.contents.add(contentRef);
    }

    /**
     * COMMENT.
     * 
     * @return {@link List}< {@link String}>
     */
    public List<String> createPathFromRoot() {
	if (getParentNode() == null) {
	    return new LinkedList<String>();
	}
	final List<String> pathFromRoot = getParentNode().createPathFromRoot();
	pathFromRoot.add(getWord());
	return pathFromRoot;
    }

    /**
     * @return {@link List}< {@link BrainNode}>
     */
    public final List<BrainNode> getChildren() {
	return new LinkedList<BrainNode>(this.children.values());
    }

    /**
     * @return {@link LinkedList<Content>} the contents.
     */
    public final LinkedList<Content> getContents() {
	return this.contents;
    }

    /**
     * COMMENT.
     * 
     * @param wordVal
     *            {@link String}
     * @return {@link BrainNode}
     */
    public BrainNode getNextNodeForWord(final String wordVal) {
	return this.children.get(wordVal);
    }

    /**
     * COMMENT.
     * 
     * @param wordRef
     *            {@link String}
     * @return {@link BrainEdge}
     */
    public BrainNode getOrCreateNextNodeForWord(final String wordRef) {
	if (!this.children.containsKey(wordRef)) {
	    this.children.put(wordRef, new BrainNode(this, wordRef));
	}
	return this.children.get(wordRef);
    }

    /**
     * @return {@link BrainNode} the parentNode.
     */
    public final BrainNode getParentNode() {
	return this.parentNode;
    }

    /**
     * @return {@link String} the word.
     */
    public final String getWord() {
	return this.word;
    }

    /**
     * @return <code>boolean</code> <code>true</code> if some children exist.
     */
    public final boolean hasChildren() {
	return this.children.size() > 0;
    }

    /**
     * @param contentsRef
     *            {@link LinkedList<Content>} the contents to set.
     */
    public final void setContents(final LinkedList<Content> contentsRef) {
	this.contents = contentsRef;
    }

    /**
     * @param parentNodeRef
     *            {@link BrainNode} the parentNode to set.
     */
    public final void setParentNode(final BrainNode parentNodeRef) {
	this.parentNode = parentNodeRef;
    }

    /**
     * @param wordVal
     *            {@link String} the word to set.
     */
    public final void setWord(final String wordVal) {
	this.word = wordVal;
    }

    /**
     * {@inheritDoc} s
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public final String toString() {
	return getClass().getSimpleName() + " [path: " + createPathFromRoot()
		+ "]";
    }

}
//
// $Log: $
//

package de.wsdevel.elsbeth.evaluators;

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
public class EvaluationException extends Exception {

    /**
     * {@link long} COMMENT.
     */
    private static final long serialVersionUID = 1L;

    /**
     * COMMENT.
     * 
     */
    public EvaluationException() {
    }

    /**
     * COMMENT.
     * 
     * @param arg0
     *            {@link String}
     */
    public EvaluationException(final String arg0) {
	super(arg0);
    }

    /**
     * COMMENT.
     * 
     * @param arg0
     *            {@link String}
     * @param arg1
     *            {@link Throwable}
     */
    public EvaluationException(final String arg0, final Throwable arg1) {
	super(arg0, arg1);
    }

    /**
     * COMMENT.
     * 
     * @param arg0
     *            {@link Throwable}
     */
    public EvaluationException(final Throwable arg0) {
	super(arg0);
    }

}
//
// $Log: $
//

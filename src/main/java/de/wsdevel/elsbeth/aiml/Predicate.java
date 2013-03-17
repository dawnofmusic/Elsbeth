package de.wsdevel.elsbeth.aiml;

import java.io.Serializable;

/**
 * Created on 05.09.2007.
 * 
 * for project: scenejo
 * 
 * @author <a href="mailto:sweiss@weissundschmidt.de">Sebastian A. Weiss - Weiss
 *         und Schmidt, Mediale Systeme GbR</a>
 * @version $Author: weiss $ -- $Revision: 1.5 $ -- $Date: 2008-10-21 08:56:43 $
 * 
 * <br>
 *          (c) 2005, Weiss und Schmidt, Mediale Systeme GbR - All rights
 *          reserved.
 * 
 */
public class Predicate implements Serializable {

    /**
     * <code>long</code> COMMENT.
     */
    private static final long serialVersionUID = -5064303126622274110L;

    /**
     * {@link String} COMMENT.
     */
    private String defaultValue;

    /**
     * {@link String} COMMENT.
     */
    private String name;

    /**
     * {@link String} COMMENT.
     */
    private String setReturn;

    /**
     * COMMENT.
     */
    public Predicate() {
	// nothing to do
    }

    /**
     * COMMENT.
     * 
     * @param nameVal
     *            {@link String}
     * @param defaultValueVal
     *            {@link String}
     * @param setReturnVal
     *            {@link String}
     */
    public Predicate(final String nameVal, final String defaultValueVal,
	    final String setReturnVal) {
	setName(nameVal);
	setDefaultValue(defaultValueVal);
	setSetReturn(setReturnVal);
    }

    /**
     * @param obj
     *            {@link Object}
     * @return <code>boolean</code>
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public final boolean equals(final Object obj) {
	if (this == obj) {
	    return true;
	}
	if (obj == null) {
	    return false;
	}
	if (getClass() != obj.getClass()) {
	    return false;
	}
	final Predicate other = (Predicate) obj;
	if (this.name == null) {
	    if (other.name != null) {
		return false;
	    }
	} else if (!this.name.equals(other.name)) {
	    return false;
	}
	return true;
    }

    /**
     * @return {@link String} the defaultValue.
     */
    public final String getDefaultValue() {
	return this.defaultValue;
    }

    /**
     * @return {@link String} the name.
     */
    public final String getName() {
	return this.name;
    }

    /**
     * @return {@link String} the setReturn.
     */
    public final String getSetReturn() {
	return this.setReturn;
    }

    /**
     * @return <code>int</code>
     * @see java.lang.Object#hashCode()
     */
    @Override
    public final int hashCode() {
	final int prime = 31;
	int result = 1;
	result = (prime * result)
		+ ((this.name == null) ? 0 : this.name.hashCode());
	return result;
    }

    /**
     * @param defaultValueVal
     *            String the defaultValue to set.
     */
    public final void setDefaultValue(final String defaultValueVal) {
	this.defaultValue = defaultValueVal;
    }

    /**
     * @param nameVal
     *            String the name to set.
     */
    public final void setName(final String nameVal) {
	this.name = nameVal;
    }

    /**
     * @param setReturnVal
     *            String the setReturn to set.
     */
    public final void setSetReturn(final String setReturnVal) {
	this.setReturn = setReturnVal;
    }

    /**
     * @return {@link String}
     * @see java.lang.Object#toString()
     */
    @Override
    public final String toString() {
	return getClass().getName() + " [name: '" + getName() + "', default: '" //$NON-NLS-1$ //$NON-NLS-2$
		+ getDefaultValue() + "', set-return: '" + getSetReturn() //$NON-NLS-1$
		+ "']"; //$NON-NLS-1$
    }

}
//
// $Log: Predicate.java,v $
// Revision 1.5 2008-10-21 08:56:43 weiss
// changed name of jar file and cleanuo
//
// Revision 1.4 2008-10-16 15:17:31 weiss
// cleanup
//
// Revision 1.3 2008-10-01 13:40:18 weiss
// fixed hashcode and equals
//
// Revision 1.2 2008/01/16 14:24:38 weiss
// first steps dealing aiml with betwixt
//
// Revision 1.1 2007/12/29 18:10:55 weiss
// *** empty log message ***
//
// Revision 1.1 2007/09/05 11:08:17 weiss
// added test launches and predicates betwixt support
//
//

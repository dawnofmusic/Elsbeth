package de.wsdevel.elsbeth;

import java.io.Serializable;
import java.util.Comparator;


/**
 * Created on 23.11.2010 for project: Elsbeth. (c) 2010, Sebastian A. Weiss -
 * All rights reserved.
 * 
 * @author <a href="mailto:sebastian@scenejo.org">Sebastian A. Weiss -
 *         scenejo.org</a>
 */
public class MatchComparator implements Comparator<Match>, Serializable {

    /**
     * ${long} serialVersionUID.
     */
    private static final long serialVersionUID = 4970333172371930743L;

    /**
     * @param o1
     *            {@link Match}
     * @param o2
     *            {@link Match}
     * @return <code>int</code>
     * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
     */
    public int compare(final Match o1, final Match o2) {
	return -Float.valueOf(o1.getScore()).compareTo(
		Float.valueOf(o2.getScore()));
    }

}
//
// $Log: $
//

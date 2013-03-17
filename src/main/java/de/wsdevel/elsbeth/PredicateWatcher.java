package de.wsdevel.elsbeth;

import de.wsdevel.elsbeth.aiml.Predicate;

/**
 * Created on 02.11.2006.
 * 
 * for project: scenejo
 * 
 * @author <a href="mailto:sweiss@weissundschmidt.de">Sebastian A. Weiss - Weiss
 *         und Schmidt, Mediale Systeme GbR</a>
 * @version $Author: weiss $ -- $Revision: 1.1 $ -- $Date: 2006/11/12 18:52:26 $
 * 
 * <br>
 *          (c) 2005, Weiss und Schmidt, Mediale Systeme GbR - All rights
 *          reserved.
 * 
 */
public interface PredicateWatcher {

    /**
     * COMMENT.
     * 
     * @param predicate
     *            {@link Predicate}
     */
    void watch(Predicate predicate);
}
//
// $Log: PredicateWatcher.java,v $
// Revision 1.1 2008-07-17 18:05:55 weiss
// *** empty log message ***
//
// Revision 1.4 2008/03/19 12:53:01 weiss
// added support for PredicateWatchers to new version
//
// Revision 1.3 2006/11/12 18:52:26 sweissSCE
// added new currentpredicates for acors and gui
//
// Revision 1.2 2006/11/06 00:11:48 sweissSCE
// big refactoring still and some improvements concerning predicates watcher
//
// Revision 1.1 2006/11/04 10:59:26 sweissSCE
// first steps creating predicate watcher and big refacoring concerning ui
//
//

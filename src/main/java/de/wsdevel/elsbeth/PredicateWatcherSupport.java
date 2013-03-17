package de.wsdevel.elsbeth;

import de.wsdevel.elsbeth.aiml.Predicate;
import de.wsdevel.tools.awt.model.observer.ObserverList;

/**
 * PredicateWatcherSupport created on 10.06.2009. for project: Elsbeth
 * 
 * @author <a href="mailto:sweiss@scenejo.org">Sebastian A. Weiss -
 *         scenejo.org</a>
 * @version $Author: $ -- $Revision: $ -- $Date: $
 * 
 * <br>
 *          (c) 2008, scenejo.org - All rights reserved. Scenejo - An
 *          Interactive Storytelling Framework
 */
public class PredicateWatcherSupport {

    /**
     * {@link ObserverList<PredicateWatcher>} COMMENT.
     */
    private final ObserverList<PredicateWatcher> watchers = new ObserverList<PredicateWatcher>();

    /**
     * @param watcher
     *            {@link PredicateWatcher}
     */
    public final void addWatcher(final PredicateWatcher watcher) {
	this.watchers.addListener(watcher);
    }

    /**
     * @param predicateName
     *            {@link String}
     * @param value
     *            {@link String}
     */
    public final void fireWatchPredicate(final Predicate predicate) {
	this.watchers.observe(new ObserverList.Action<PredicateWatcher>() {
	    public void doit(final PredicateWatcher arg0) {
		arg0.watch(predicate);
	    }
	});
    }

    /**
     * @param watcher
     *            {@link PredicateWatcher}
     */
    public final void removeWatcher(final PredicateWatcher watcher) {
	this.watchers.removeListener(watcher);
    }

}
//
// $Log: $
//

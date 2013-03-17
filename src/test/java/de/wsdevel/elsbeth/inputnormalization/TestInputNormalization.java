package de.wsdevel.elsbeth.inputnormalization;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Locale;

import junit.framework.TestCase;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import de.wsdevel.elsbeth.inputnormalization.InputNormalizationService;

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
public class TestInputNormalization extends TestCase {

    /**
     * {@link String} COMMENT.
     */
    private static final String EIN_SATZ_MIT_UNNOETIGEN_LEHRZEICHEN = "_    Satz mit  unnoetigen    Lehrzeichen    *";

    /**
     * {@link String[]} COMMENT.
     */
    private static final String[] WORDS = new String[] { "_", "SATZ", "MIT",
	    "UNNOETIGEN", "LEHRZEICHEN", "*" };

    /**
     * COMMENT.
     * 
     * @param name
     */
    public TestInputNormalization(final String name) {
	super(name);
    }

    /**
     * {@inheritDoc}
     * 
     * @see junit.framework.TestCase#setUp()
     */
    @Override
    protected void setUp() throws Exception {
	BasicConfigurator.configure();
	Logger.getRootLogger().setLevel(Level.DEBUG);
    }

    /**
     * COMMENT.
     */
    public void testPatternSplitting() {
	compareResults(
		new InputNormalizationService(Locale.UK)
			.getWordsForPattern(TestInputNormalization.EIN_SATZ_MIT_UNNOETIGEN_LEHRZEICHEN),
		TestInputNormalization.WORDS);
    }

    /**
     * COMMENT.
     */
    public void testBasisUKNormalization() {
	compareResults(
		new InputNormalizationService(Locale.UK)
			.getWordsForPattern(" :-) I'm Mr Sebastian  and you can't find my website at http://www.sebastian-a-weiss.de:80!"),
		new String[] { "I", "AM", "MISTER", "SEBASTIAN", "AND", "YOU",
			"CANNOT", "FIND", "MY", "WEBSITE", "AT", "HTTP", "WWW",
			"DOT", "SEBASTIAN", "MINUS", "A", "MINUS", "WEISS",
			"DOT", "DE", "PORT", "80" });
    }

    /**
     * COMMENT.
     */
    public void testBasisUSNormalization() {
	compareResults(
		new InputNormalizationService(Locale.US)
			.getWordsForPattern(" :-) I'm Mr. Sebastian  and you can't find my website at http://www.sebastian-a-weiss.de:80!"),
		new String[] { "I", "AM", "MISTER", "SEBASTIAN", "AND", "YOU",
			"CANNOT", "FIND", "MY", "WEBSITE", "AT", "HTTP", "WWW",
			"DOT", "SEBASTIAN", "MINUS", "A", "MINUS", "WEISS",
			"DOT", "DE", "PORT", "80" });
    }

    /**
     * COMMENT.
     */
    public void testBasisDENormalization() {
	compareResults(
		new InputNormalizationService(Locale.GERMANY)
			.getWordsForPattern(" :-) Aufs dach und vorm zelt und meine webseite lautet http://www.sebastian-a-weiss.de:80!"),
		new String[] { "AUF", "DAS", "DACH", "UND", "VOR", "DEM",
			"ZELT", "UND", "MEINE", "WEBSEITE", "LAUTET", "HTTP",
			"WWW", "PUNKT", "SEBASTIAN", "MINUS", "A", "MINUS",
			"WEISS", "PUNKT", "DE", "PORT", "80" });
    }

    private void compareResults(Collection<String> wordsForPattern,
	    String[] expectedWords) {
	final String[] patternWordsForCategory = new ArrayList<String>(
		wordsForPattern).toArray(new String[] {});
	// assertEquals("not the correct size", expectedWords.length,
	// patternWordsForCategory.length);
	for (int i = 0; i < patternWordsForCategory.length; i++) {
	    assertEquals("not the expected word", expectedWords[i],
		    patternWordsForCategory[i]);
	}
    }

}
//
// $Log: $
//

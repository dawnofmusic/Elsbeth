package de.wsdevel.elsbeth.aiml;

import java.util.List;
import java.util.Vector;

/**
 * Created on 07.09.2007.
 * 
 * for project: scenejo
 * 
 * @author <a href="mailto:sweiss@weissundschmidt.de">Sebastian A. Weiss - Weiss
 *         und Schmidt, Mediale Systeme GbR</a>
 * @version $Author: weiss $ -- $Revision: 1.4 $ -- $Date: 2008-10-21 08:56:43 $
 * 
 * <br>
 *          (c) 2005, Weiss und Schmidt, Mediale Systeme GbR - All rights
 *          reserved.
 * 
 */
public final class AimlTags {

    /**
     * {@link String} 'aiml'.
     */
    public static final String AIML = "aiml"; //$NON-NLS-1$

    /**
     * {@link String} 'bot'.
     */
    public static final String BOT = "bot"; //$NON-NLS-1$

    /**
     * {@link String} 'category'.
     */
    public static final String CATEGORY = "category"; //$NON-NLS-1$

    /**
     * {@link String} 'condition'.
     */
    public static final String CONDITION = "condition"; //$NON-NLS-1$

    /**
     * {@link String} COMMENT.
     */
    public static final String DATE = "date"; //$NON-NLS-1$

    /**
     * {@link String} 'formal'.
     */
    public static final String FORMAL = "formal"; //$NON-NLS-1$

    /**
     * {@link String} COMMENT.
     */
    public static final String GENDER = "gender"; //$NON-NLS-1$

    /**
     * {@link String} 'get'.
     */
    public static final String GET = "get"; //$NON-NLS-1$

    /**
     * {@link String} COMMENT.
     */
    public static final String ID = "id"; //$NON-NLS-1$

    /**
     * {@link String} 'li'.
     */
    public static final String LI = "li"; //$NON-NLS-1$

    /**
     * {@link String} 'lowercase'.
     */
    public static final String LOWERCASE = "lowercase"; //$NON-NLS-1$

    /**
     * {@link String} 'name'.
     */
    public static final String NAME_ATTRIBUTE = "name"; //$NON-NLS-1$

    /**
     * {@link String} 'operator'.
     */
    public static final String OPERATOR_ATTRIBUTE = "operator"; //$NON-NLS-1$

    /**
     * {@link String} 'pattern'.
     */
    public static final String PATTERN = "pattern"; //$NON-NLS-1$

    /**
     * {@link String} COMMENT.
     */
    public static final String PERSON = "person"; //$NON-NLS-1$

    /**
     * {@link String} COMMENT.
     */
    public static final String PERSON2 = "person2"; //$NON-NLS-1$

    /**
     * {@link String} 'predicate'.
     */
    public static final String PREDICATE_ATTRIBUTE = "predicate"; //$NON-NLS-1$

    /**
     * {@link String} 'random'.
     */
    public static final String RANDOM = "random"; //$NON-NLS-1$

    /**
     * {@link String} 'sentence'.
     */
    public static final String SENTENCE = "sentence"; //$NON-NLS-1$

    /**
     * {@link String} 'set'.
     */
    public static final String SET = "set"; //$NON-NLS-1$

    /**
     * {@link String} COMMENT.
     */
    public static final String SIZE = "size"; //$NON-NLS-1$

    /**
     * {@link String} COMMENT.
     */
    public static final String SR = "sr"; //$NON-NLS-1$

    /**
     * {@link String} 'srai'.
     */
    public static final String SRAI = "srai"; //$NON-NLS-1$

    /**
     * {@link String} 'template'.
     */
    public static final String TEMPLATE = "template"; //$NON-NLS-1$

    /**
     * {@link List}< {@link String}> tags used in template.
     */
    public static final List<String> TEMPLATE_TAGS = new Vector<String>();

    /**
     * {@link String} 'think'.
     */
    public static final String THINK = "think"; //$NON-NLS-1$

    /**
     * {@link String} 'topic'.
     */
    public static final String TOPIC = "topic"; //$NON-NLS-1$

    /**
     * {@link String} 'topic'.
     */
    public static final String THAT = "that"; //$NON-NLS-1$

    /**
     * {@link String} 'uppercase'.
     */
    public static final String UPPERCASE = "uppercase"; //$NON-NLS-1$

    /**
     * {@link String} 'value'.
     */
    public static final String VALUE_ATTRIBUTE = "value"; //$NON-NLS-1$

    /**
     * {@link String} COMMENT.
     */
    public static final String VERSION = "version"; //$NON-NLS-1$

    static {
	AimlTags.TEMPLATE_TAGS.add(AimlTags.UPPERCASE);
	AimlTags.TEMPLATE_TAGS.add(AimlTags.LOWERCASE);
	AimlTags.TEMPLATE_TAGS.add(AimlTags.FORMAL);
	AimlTags.TEMPLATE_TAGS.add(AimlTags.SENTENCE);
	AimlTags.TEMPLATE_TAGS.add(AimlTags.SET);
	AimlTags.TEMPLATE_TAGS.add(AimlTags.GET);
	AimlTags.TEMPLATE_TAGS.add(AimlTags.BOT);
    }

    /**
     * Hidden constructor.
     */
    private AimlTags() {
    }
}
/*
 * $Log: AimlTags.java,v $ Revision 1.4 2008-10-21 08:56:43 weiss changed name
 * of jar file and cleanuo Revision 1.3 2008-10-16 15:17:31 weiss cleanup
 * Revision 1.2 2008-09-19 19:59:27 weiss added some tags Revision 1.1
 * 2007/12/29 18:10:54 weiss empty log message
 * 
 * Revision 1.1 2007/09/12 13:44:52 weiss clean up
 */

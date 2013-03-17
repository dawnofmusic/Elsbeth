package de.wsdevel.elsbeth;

import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.io.File;
import java.util.Locale;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import de.wsdevel.components.console.ConsoleModel;
import de.wsdevel.components.console.JConsolePane;
import de.wsdevel.tools.awt.GBC;
import de.wsdevel.tools.awt.GUIToolkit;

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
public class AppTestBrain {

    /**
     * COMMENT.
     * 
     * @param args
     */
    @SuppressWarnings("serial")
    public static void main(final String[] args) {
	BasicConfigurator.configure();
	Logger.getRootLogger().setLevel(Level.DEBUG);

	final DialogAgent da = new DialogAgent(Locale.US);
	da.load(new File("../Scenejo__AIML/data/aaa/green/Atomic.aiml"), false);

	ConsoleModel.initialize(true);

	final JPanel panel = new JPanel(new GridBagLayout());

	final JTextField textField = new JTextField();
	panel.add(textField,
		new GBC().pos(0, 0).fillHorizontal(1.0).insets(10, 10, 10, 0));
	panel.add(new JButton(new AbstractAction("Send") {
	    public void actionPerformed(final ActionEvent arg0) {
		final String request = textField.getText().trim();
		System.out.println("[REQUEST:] " + request);
		System.out.println("[RESPONSE:] "
			+ da.getResponseForRequest(request));// TODO
		// remove
		// sysout
		textField.setText("");
	    }
	}), new GBC().pos(1, 0).insets(10, 10, 10, 10));

	panel.add(new JScrollPane(new JConsolePane()), new GBC().pos(0, 1)
		.size(2, 1).insets(10, 10, 15, 10).fillBoth(1, 1));

	final JFrame frame = GUIToolkit.createMainFramOverPanel(panel,
		new Rectangle(new Dimension(400, 600)));
	GUIToolkit.center(frame);
	frame.setVisible(true);

    }

}
//
// $Log: $
//

package java_oo.swing;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;

public class Console {

	public static void main(String args[]) {
		Console c = new Console();
		while (true)
			c.log("ABC");
	}

	private JTextPane jTextPlane = null;

	private static int position = 0;

	public Console() {
		JFrame jFrame = new JFrame();

		jTextPlane = new JTextPane();
		SimpleAttributeSet set = new SimpleAttributeSet();
		StyleConstants.setBold(set, true);

		// Set the attributes before adding text
		jTextPlane.setCharacterAttributes(set, true);

		JScrollPane sp = new JScrollPane(jTextPlane);

		jFrame.setSize(100, 700);
		jFrame.setLocation(position = (position + 100), 0);
		jFrame.add(sp);
		jFrame.setVisible(true);
	}

	public void log(String message) {
		SimpleAttributeSet set = new SimpleAttributeSet();
		StyleConstants.setForeground(set, Color.black);

		Document doc = jTextPlane.getStyledDocument();
		try {
			doc.insertString(doc.getLength(), message + "\n", set);
		} catch (BadLocationException e) {
			throw new RuntimeException(e);
		}
	}

	public void log(String message, Color c) {
		SimpleAttributeSet set = new SimpleAttributeSet();
		StyleConstants.setForeground(set, c);

		Document doc = jTextPlane.getStyledDocument();
		try {
			doc.insertString(doc.getLength(), message + "\n", set);
		} catch (BadLocationException e) {
			throw new RuntimeException(e);
		}
	}
}

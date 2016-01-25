package graphics.login;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.Serializable;

import javax.swing.JPasswordField;
import javax.swing.SwingUtilities;

public class ShowHidePasswordListener extends MouseAdapter implements Serializable {
	@Override
	public void mouseReleased(MouseEvent e) {
		if (SwingUtilities.isRightMouseButton(e)) {
			if (e.getSource() instanceof JPasswordField) {
				((JPasswordField) e.getSource()).setEchoChar(this.defaultEchoChar);
			}
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if (SwingUtilities.isRightMouseButton(e)) {
			if (e.getSource() instanceof JPasswordField) {
				this.defaultEchoChar = ((JPasswordField) e.getSource()).getEchoChar();
				((JPasswordField) e.getSource()).setEchoChar((char) 0);
			}
		}
	}

	private static final long serialVersionUID = 4802014560730408040L;
	private char defaultEchoChar = '•';
}

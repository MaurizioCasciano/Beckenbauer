package graphics.login;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPasswordField;

public class ShowHidePasswordListener implements MouseListener {
	private char defaultEchoChar = '•';

	@Override
	public void mouseReleased(MouseEvent e) {
		if (e.getButton() == MouseEvent.BUTTON3) {
			if (e.getSource() instanceof JPasswordField) {
				((JPasswordField) e.getSource()).setEchoChar(this.defaultEchoChar);
			}
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if (e.getButton() == MouseEvent.BUTTON3) {
			if (e.getSource() instanceof JPasswordField) {
				this.defaultEchoChar = ((JPasswordField) e.getSource()).getEchoChar();
				((JPasswordField) e.getSource()).setEchoChar((char) 0);
			}
		}
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}

package graphics.login;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.Serializable;

import javax.swing.JPasswordField;
import javax.swing.SwingUtilities;

/**
 * Classe che estende {@link MouseAdapter}, usata per permettere la temporanea
 * visualizzazione della password digitata in una {@link JPasswordField} alla
 * pressione del tasto destro del mouse. La password verra' nuovamente nascosta
 * al rilascio del tasto desto del mouse.
 * 
 * @author Maurizio
 */
public class ShowHidePasswordListener extends MouseAdapter implements Serializable {
	@Override
	public void mouseReleased(MouseEvent e) {
		if (SwingUtilities.isRightMouseButton(e)) {
			if (e.getSource() instanceof JPasswordField) {
				/*
				 * Reimposta l'echoChar della JPasswordFiel a quello di default.
				 */
				((JPasswordField) e.getSource()).setEchoChar(this.defaultEchoChar);
			}
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if (SwingUtilities.isRightMouseButton(e)) {
			if (e.getSource() instanceof JPasswordField) {
				/*
				 * Aggiorna il carattere echoChar di default.
				 */
				this.defaultEchoChar = ((JPasswordField) e.getSource()).getEchoChar();
				/*
				 * Imposta l'echoChar a (char)0, ossia il carattere che permette
				 * di visualizzare in chiaro la password inserita.
				 */
				((JPasswordField) e.getSource()).setEchoChar((char) 0);
			}
		}
	}

	private static final long serialVersionUID = 4802014560730408040L;
	private char defaultEchoChar = '•';
}

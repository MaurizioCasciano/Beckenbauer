package testing;

import javax.swing.SwingUtilities;
import graphics.Window;

public class Testing {

	public static void main(String[] args) {

		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				new Window("Struttura Sportiva");
			}
		});
	}
}
/**
 * 
 */
package graphics.incasso;

import java.io.Serializable;

import javax.swing.JFrame;

import struttura.StrutturaSportiva;

/**
 * Classe che estende {@link JFrame} usata per la visualizzazione degli incassi
 * di una StrutturaSportiva.
 * 
 * @author Maurizio
 */
public class VisualizzaIncassoFrame extends JFrame implements Serializable {

	/**
	 * Crea un nuovo frame per la visualizzazione degli incassi di una
	 * StrutturaSportiva.
	 * 
	 * @param strutturaSportiva
	 *            La struttura di cui si vogliono conoscere gli incassi.
	 * @throws NullPointerException
	 *             Se la struttura è null.
	 * @throws IllegalArgumentException
	 *             Se la struttura non ha stadi.
	 * @author Maurizio
	 */
	public VisualizzaIncassoFrame(StrutturaSportiva strutturaSportiva)
			throws NullPointerException, IllegalArgumentException {
		super();
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		this.add(new VisualizzaIncassoPanel(strutturaSportiva));

		this.setSize(500, 300);
		this.setResizable(false);
	}

	private static final long serialVersionUID = 7909311407946985519L;
}

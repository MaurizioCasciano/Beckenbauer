package graphics;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.Serializable;
import javax.swing.JPanel;

/**
 * Classe che estende {@link JPanel} per permettere di impostare un'immagine
 * come background del pannello.
 * 
 * @author Maurizio
 */
public class BackgroundImagePanel extends JPanel implements Serializable {

	/**
	 * Crea un nuovo {@link BackgroundImagePanel} con un flow layout ed
	 * un'immagine come background.
	 * 
	 * @param bufferedImage
	 *            L'immagine da utilizzare come background.
	 */
	public BackgroundImagePanel(BufferedImage bufferedImage) {
		super();
		this.myBackGroundImage = bufferedImage;
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		g2.drawImage(myBackGroundImage, 0, 0, this.getWidth(), this.getHeight(), null);
	}

	private static final long serialVersionUID = 5940403846029260534L;
	private BufferedImage myBackGroundImage;
}

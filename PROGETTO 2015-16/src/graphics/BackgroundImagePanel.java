package graphics;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.Serializable;

import javax.swing.JPanel;

public class BackgroundImagePanel extends JPanel implements Serializable{

	/**
	 * Crea un nuovo LoginPanel2 con un double buffer ed un flow layout.
	 */
	public BackgroundImagePanel() {
		super();
	}

	/**
	 * Crea un nuovo LoginPanel2 con un double buffer, un flow layout ed un'immagine
	 * come background.
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

	@SuppressWarnings("unused")
	private BufferedImage getScaledImage(BufferedImage image, int width, int height) {
		BufferedImage resizedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		Graphics2D g2 = resizedImage.createGraphics();
		g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);

		g2.drawImage(image, 0, 0, width, height, null);
		g2.dispose();
		return resizedImage;
	}

	private static final long serialVersionUID = 5940403846029260534L;
	private BufferedImage myBackGroundImage;
}

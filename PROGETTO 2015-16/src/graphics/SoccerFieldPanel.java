package graphics;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.io.Serializable;
import javax.swing.JPanel;

/**
 * 
 * @author Maurizio
 */
public class SoccerFieldPanel extends JPanel implements Serializable {

	public SoccerFieldPanel() {
		super();
		this.setBackground(new Color(0, 150, 0));
	}

	@Override
	protected void paintComponent(Graphics g) {
		// disegna il background
		super.paintComponent(g);
		// la distanza da mantenere dai bordi di questo JPanel.
		double distance = 10;

		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(Color.WHITE);
		g2.setStroke(new BasicStroke(2));

		Rectangle2D.Double campo = new Rectangle2D.Double(distance, distance, getWidth() - 2 * distance,
				getHeight() - 2 * distance);

		Line2D.Double lineaCentroCampo = new Line2D.Double(campo.getWidth() / 2, campo.getY(), campo.getWidth() / 2,
				campo.getHeight() + distance);

		double diametroCerchioCentroCampo = campo.getHeight() / 6;
		double xCerchio = (campo.getWidth() - diametroCerchioCentroCampo) / 2;
		double yCerchio = (campo.getHeight() - diametroCerchioCentroCampo) / 2;

		Ellipse2D.Double cerchioCentroCampo = new Ellipse2D.Double(xCerchio, yCerchio, diametroCerchioCentroCampo,
				diametroCerchioCentroCampo);
		g2.draw(cerchioCentroCampo);

		double diametroCerchioPiccoloCentroCampo = campo.getHeight() / 100;
		double xCerchioPiccolo = (campo.getWidth() - diametroCerchioPiccoloCentroCampo) / 2;
		double yCerchioPiccolo = (campo.getHeight() - diametroCerchioPiccoloCentroCampo) / 2;

		Ellipse2D.Double cerchioPiccoloCentroCampo = new Ellipse2D.Double(xCerchioPiccolo, yCerchioPiccolo,
				diametroCerchioPiccoloCentroCampo, diametroCerchioPiccoloCentroCampo);

		g2.fill(cerchioPiccoloCentroCampo);

		double altezzaArea = campo.getHeight() / 3;
		double larghezzaArea = altezzaArea / 2;

		Rectangle2D.Double area1 = new Rectangle2D.Double(campo.getX(), (campo.getHeight() - altezzaArea) / 2,
				larghezzaArea, altezzaArea);

		double altezzaSmallArea = altezzaArea / 2;
		double larghezzaSmallArea = larghezzaArea / 2.5;

		Rectangle2D.Double smallArea1 = new Rectangle2D.Double(campo.getX(), (campo.getHeight() - altezzaSmallArea) / 2,
				larghezzaSmallArea, altezzaSmallArea);

		Rectangle2D.Double area2 = new Rectangle2D.Double(campo.getWidth() - larghezzaArea + distance,
				(campo.getHeight() - altezzaArea) / 2, larghezzaArea, altezzaArea);

		Rectangle2D.Double smallArea2 = new Rectangle2D.Double(campo.getWidth() - larghezzaSmallArea + distance,
				(campo.getHeight() - altezzaSmallArea) / 2, larghezzaSmallArea, altezzaSmallArea);

		g2.draw(campo);
		g2.draw(lineaCentroCampo);
		g2.draw(area1);
		g2.draw(smallArea1);
		g2.draw(area2);
		g2.draw(smallArea2);
	}

	private static final long serialVersionUID = -833316024558122877L;
}

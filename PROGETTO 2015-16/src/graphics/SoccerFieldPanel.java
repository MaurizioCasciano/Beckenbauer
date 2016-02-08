package graphics;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Arc2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.io.Serializable;
import javax.swing.JPanel;

/**
 * Classe che disegna il campo da calcio dello stadio.
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
		double distanza = 10;

		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(Color.WHITE);
		g2.setStroke(new BasicStroke(2));

		Rectangle2D.Double campo = new Rectangle2D.Double(distanza, distanza, getWidth() - 2 * distanza,
				getHeight() - 2 * distanza);

		Line2D.Double lineaCentroCampo = new Line2D.Double((campo.getWidth() + 2 * distanza) / 2, campo.getY(),
				(campo.getWidth() + 2 * distanza) / 2, campo.getHeight() + distanza);

		/*
		 * Line2D.Double lineaCentroOrizzontale = new
		 * Line2D.Double(campo.getX(), (campo.getHeight() + 2 * distanza) / 2,
		 * campo.getWidth() + distanza, (campo.getHeight() + 2 * distanza) / 2);
		 * g2.draw(lineaCentroOrizzontale);
		 */

		double diametroCerchioCentroCampo = campo.getHeight() / 6;
		double xCerchio = (campo.getWidth() - diametroCerchioCentroCampo + 2 * distanza) / 2;
		double yCerchio = (campo.getHeight() - diametroCerchioCentroCampo + 2 * distanza) / 2;

		Ellipse2D.Double cerchioCentroCampo = new Ellipse2D.Double(xCerchio, yCerchio, diametroCerchioCentroCampo,
				diametroCerchioCentroCampo);
		g2.draw(cerchioCentroCampo);

		double diametroCerchioPiccoloCentroCampo = campo.getHeight() / 100;
		double xCerchioPiccolo = (campo.getWidth() - diametroCerchioPiccoloCentroCampo + 2 * distanza) / 2;
		double yCerchioPiccolo = (campo.getHeight() - diametroCerchioPiccoloCentroCampo + 2 * distanza) / 2;

		Ellipse2D.Double cerchioPiccoloCentroCampo = new Ellipse2D.Double(xCerchioPiccolo, yCerchioPiccolo,
				diametroCerchioPiccoloCentroCampo, diametroCerchioPiccoloCentroCampo);

		g2.fill(cerchioPiccoloCentroCampo);

		double altezzaArea = campo.getHeight() / 3;
		double larghezzaArea = altezzaArea / 2;

		Rectangle2D.Double area1 = new Rectangle2D.Double(campo.getX(),
				(campo.getHeight() - altezzaArea + 2 * distanza) / 2, larghezzaArea, altezzaArea);

		double altezzaSmallArea = altezzaArea / 2;
		double larghezzaSmallArea = larghezzaArea / 2.5;

		Rectangle2D.Double smallArea1 = new Rectangle2D.Double(campo.getX(),
				(campo.getHeight() - altezzaSmallArea + 2 * distanza) / 2, larghezzaSmallArea, altezzaSmallArea);

		Rectangle2D.Double area2 = new Rectangle2D.Double(campo.getWidth() - larghezzaArea + distanza,
				(campo.getHeight() - altezzaArea + 2 * distanza) / 2, larghezzaArea, altezzaArea);

		Rectangle2D.Double smallArea2 = new Rectangle2D.Double(campo.getWidth() - larghezzaSmallArea + distanza,
				(campo.getHeight() - altezzaSmallArea + 2 * distanza) / 2, larghezzaSmallArea, altezzaSmallArea);

		double xRigore1 = area1.getX() + area1.getWidth() - larghezzaSmallArea / 1.5
				- diametroCerchioPiccoloCentroCampo / 2;
		double yRigore1 = (campo.getHeight() + 2 * distanza - diametroCerchioPiccoloCentroCampo) / 2;
		Ellipse2D.Double rigor1 = new Ellipse2D.Double(xRigore1, yRigore1, diametroCerchioPiccoloCentroCampo,
				diametroCerchioPiccoloCentroCampo);

		double xRigore2 = area2.getX() + larghezzaSmallArea / 1.5 - diametroCerchioPiccoloCentroCampo / 2;
		double yRigore2 = yRigore1;
		Ellipse2D.Double rigore2 = new Ellipse2D.Double(xRigore2, yRigore2, diametroCerchioPiccoloCentroCampo,
				diametroCerchioPiccoloCentroCampo);

		double lunetta1Width = smallArea1.getWidth();
		double lunetta1Height = smallArea1.getHeight() - smallArea1.getHeight() / 3;
		double xLunetta1 = area1.getWidth() + distanza - lunetta1Width / 2;
		double yLunetta1 = smallArea1.getY() + smallArea1.getHeight() / 6;

		Arc2D.Double lunetta1 = new Arc2D.Double(xLunetta1, yLunetta1, lunetta1Width, lunetta1Height, -90, 180,
				Arc2D.OPEN);

		double xLunetta2 = campo.getWidth() + distanza - area2.getWidth() - lunetta1Width / 2;
		Arc2D.Double lunetta2 = new Arc2D.Double(xLunetta2, yLunetta1, lunetta1Width, lunetta1Height, 90, 180,
				Arc2D.OPEN);

		g2.draw(campo);
		g2.draw(lineaCentroCampo);
		g2.draw(area1);
		g2.draw(smallArea1);
		g2.draw(lunetta1);
		g2.fill(rigor1);
		g2.draw(area2);
		g2.draw(smallArea2);
		g2.draw(lunetta2);
		g2.fill(rigore2);
	}

	private static final long serialVersionUID = -833316024558122877L;
}

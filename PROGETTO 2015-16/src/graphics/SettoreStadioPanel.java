package graphics;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class SettoreStadioPanel extends JPanel {

	public SettoreStadioPanel(int posti) {
		super(new CardLayout());

		this.posti = posti;
		this.labelPanelCard = new JPanel(new BorderLayout());

		this.nameLabel = new JLabel("Settore", JLabel.CENTER) {

			@Override
			protected void paintComponent(Graphics g) {

				if (this.getComponentListeners().length == 0) {
					this.addComponentListener(componentListener);
				}

				super.paintComponent(g);
				this.g = g;
			}

			private ComponentListener componentListener = new ComponentAdapter() {

				@Override
				public void componentResized(ComponentEvent e) {
					super.componentResized(e);
					adaptLabelFont(nameLabel);
				}

			};

			protected void adaptLabelFont(JLabel label) {
				if (g == null) {
					return;
				}
				Rectangle r = label.getBounds();
				int MIN_FONT_SIZE = 10;
				int fontSize = MIN_FONT_SIZE;
				Font oldFont = label.getFont();

				Rectangle r1 = new Rectangle();
				Rectangle r2 = new Rectangle();
				int MAX_FONT_SIZE = 1000;
				while (fontSize < MAX_FONT_SIZE) {
					r1.setSize(getTextSize(label, oldFont.deriveFont(oldFont.getStyle(), fontSize)));
					r2.setSize(getTextSize(label, oldFont.deriveFont(oldFont.getStyle(), fontSize + 1)));
					if (r.contains(r1) && !r.contains(r2)) {
						break;
					}
					fontSize++;
				}

				label.setFont(oldFont.deriveFont(oldFont.getStyle(), fontSize));
				label.repaint();
			}

			private Dimension getTextSize(JLabel l, Font f) {
				Dimension size = new Dimension();
				g.setFont(f);
				FontMetrics fm = g.getFontMetrics(f);
				size.width = fm.stringWidth(l.getText());
				size.height = fm.getHeight();

				return size;
			}

			private static final long serialVersionUID = -6207535289854212799L;
			private Graphics g;
		};

		this.labelPanelCard.add(this.nameLabel, BorderLayout.CENTER);

		int sqrtPosti = (int) Math.floor(Math.sqrt(posti));

		this.seatsPanelCard = new JPanel(new GridLayout(sqrtPosti, sqrtPosti, 3, 3));

		for (int i = 0; i < posti; i++) {
			this.seatsPanelCard.add(new StadiumSeatButton(i, Color.GREEN));
		}

		this.add(this.labelPanelCard);
		this.add(this.seatsPanelCard);
	}

	public void showNextCard() {
		((CardLayout) getLayout()).next(this);
	}

	public int getSeats() {
		return posti;
	}

	private static final long serialVersionUID = 4920370237665489993L;
	private int posti;
	private JPanel labelPanelCard, seatsPanelCard;
	private JLabel nameLabel;
}

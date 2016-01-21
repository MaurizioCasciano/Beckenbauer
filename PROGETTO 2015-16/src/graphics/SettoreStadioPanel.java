package graphics;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.io.Serializable;
import javax.swing.JLabel;
import javax.swing.JPanel;
import struttura.Partita;
import struttura.Posto;
import struttura.Settore;
import struttura.StrutturaSportiva;
import user.Cliente;

public class SettoreStadioPanel extends JPanel implements Serializable {

	public SettoreStadioPanel(StrutturaSportiva strutturaSportiva, Cliente cliente, Partita partita, Settore settore,
			StadiumMode stadiumMode) {
		super(new CardLayout());

		this.strutturaSportiva = strutturaSportiva;
		this.cliente = cliente;
		this.partita = partita;

		this.stadiumMode = stadiumMode;
		this.settore = settore;
		this.nomeSettore = this.settore.getNomeSettore();

		// this.postiPerSettore = this.partita.getStadio().getPostiPerSettore();
		this.numeroFilePerSettore = this.partita.getStadio().getNumeroFilePerSettore();
		// this.postiPerFila = this.partita.getStadio().getPostiPerFila();

		this.labelPanelCard = new JPanel(new BorderLayout());

		this.nameLabel = new JLabel(this.nomeSettore, JLabel.CENTER) {

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

		// Pannello dei posti
		this.seatsPanelCard = new JPanel(new GridLayout(this.numeroFilePerSettore, 0, 3, 3));

		/*
		 * int numeroFila = 0;
		 * 
		 * for (int numeroPosto = 1; numeroPosto <= this.postiPerSettore;
		 * numeroPosto++) {
		 * 
		 * if (numeroPosto % this.postiPerFila == 1) { numeroFila++; }
		 * 
		 * this.seatsPanelCard.add(new StadiumSeatButton(this.strutturaSportiva,
		 * this.cliente, this.partita, numeroFila, numeroPosto, Color.GREEN,
		 * StadiumMode.PRENOTAZIONE)); }
		 */

		for (Posto p : this.settore.getPosti()) {
			this.seatsPanelCard.add(
					new StadiumSeatButton(this.strutturaSportiva, this.cliente, this.partita, p, this.stadiumMode));
		}

		this.add(this.labelPanelCard);
		this.add(this.seatsPanelCard);
	}

	/**
	 * Mostra alternativamente la label con il nome del settore oppure il
	 * pannello con i posti.
	 */
	public void showNextCard() {
		((CardLayout) getLayout()).next(this);
	}

	public Settore getSettore() {
		return this.settore;
	}

	public int getSeats() {
		return postiPerSettore;
	}

	private static final long serialVersionUID = 4920370237665489993L;
	private StrutturaSportiva strutturaSportiva;
	private Cliente cliente;
	private Partita partita;
	private String nomeSettore;
	private Settore settore;
	private StadiumMode stadiumMode;
	private int postiPerSettore, numeroFilePerSettore;
	private JPanel labelPanelCard, seatsPanelCard;
	private JLabel nameLabel;
}

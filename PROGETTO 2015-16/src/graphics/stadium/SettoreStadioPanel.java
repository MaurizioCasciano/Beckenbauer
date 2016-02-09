package graphics.stadium;

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
import struttura.Stadio;
import struttura.StrutturaSportiva;
import user.Cliente;

/**
 * Classe che estende {@link JPanel} per permettere la rappresentazione di un
 * {@link Settore} di uno {@link Stadio}.
 * 
 * @author Maurizio
 */
public class SettoreStadioPanel extends JPanel implements Serializable {

	/**
	 * Crea un nuovo pannello rappresentante un settore di uno {@link Stadio}.
	 * 
	 * @param strutturaSportiva
	 *            La StrutturaSportiva di appartenenza dello Stadio.
	 * @param cliente
	 *            Il cliente che accede alla visualizzazione Stadio.
	 * @param partita
	 *            La partita che si svolge nello Stadio.
	 * @param settore
	 *            Il Settore da rappresentare.
	 * @param stadiumMode
	 *            La modalità con cui si vuole accedere allo Stadio.
	 * @author Maurizio
	 */
	public SettoreStadioPanel(StrutturaSportiva strutturaSportiva, Cliente cliente, Partita partita, Settore settore,
			StadiumMode stadiumMode) {
		super(new CardLayout());

		this.strutturaSportiva = strutturaSportiva;
		this.cliente = cliente;
		this.partita = partita;

		this.stadiumMode = stadiumMode;
		this.settore = settore;
		this.nomeSettore = this.settore.getNomeSettore();

		this.numeroFilePerSettore = this.partita.getStadio().getNumeroFilePerSettore();

		this.labelPanelCard = new JPanel(new BorderLayout());

		this.nameLabel = new JLabel(this.nomeSettore, JLabel.CENTER) {

			@Override
			protected void paintComponent(Graphics g) {

				if (this.getComponentListeners().length == 0) {
					this.addComponentListener(componentListener);
				}

				super.paintComponent(g);
				this.labelGraphics = g;
			}

			private ComponentListener componentListener = new ComponentAdapter() {

				@Override
				public void componentResized(ComponentEvent e) {
					super.componentResized(e);
					adaptLabelFont(nameLabel);
				}

			};

			/**
			 * Adatta il font massimo della label affinchè il testo riempia la
			 * larghezza della label.
			 * 
			 * @param label
			 *            La label a cui si vuole adattare la dimensione del
			 *            font.
			 * @author Maurizio
			 */
			protected void adaptLabelFont(JLabel label) {
				if (labelGraphics == null) {
					return;
				}

				/*
				 * Le dimensioni della label.
				 */
				Rectangle labelRect = label.getBounds();
				int MIN_FONT_SIZE = 10;
				int fontSize = MIN_FONT_SIZE;

				/*
				 * Il font della label.
				 */
				Font oldFont = label.getFont();

				Rectangle rect1 = new Rectangle();
				Rectangle rect2 = new Rectangle();
				int MAX_FONT_SIZE = 1000;

				while (fontSize < MAX_FONT_SIZE) {
					rect1.setSize(getTextSize(label, oldFont.deriveFont(oldFont.getStyle(), fontSize)));
					rect2.setSize(getTextSize(label, oldFont.deriveFont(oldFont.getStyle(), fontSize + 1)));

					/*
					 * Quando si verifica questa condizione significa che
					 * abbiamo trovato la dimensione massima del font affinchè
					 * il testo riempia la larghezza della label.
					 */
					if (labelRect.contains(rect1) && !labelRect.contains(rect2)) {
						break;
					}

					/*
					 * Ad ogni ciclo incrementa la grandezza del font.
					 */
					fontSize++;
				}

				/*
				 * Imposta il font della label con il vecchio font a cui si
				 * applica la dimensione ottenuta.
				 */
				label.setFont(oldFont.deriveFont(oldFont.getStyle(), fontSize));
				label.repaint();
			}

			/**
			 * Restituisce le dimensioni del testo della label passata in input
			 * con il font passato in input.
			 * 
			 * @param label
			 *            La label di cui si vogliono conoscere le dimensioni
			 *            del testo.
			 * @param font
			 *            Il font utilizzato dalla label.
			 * @return Le dimensioni del testo della label utilizzando il font
			 *         passato in input.
			 * @author Maurizio
			 */
			private Dimension getTextSize(JLabel label, Font font) {
				Dimension size = new Dimension();
				labelGraphics.setFont(font);
				FontMetrics fontMetrics = labelGraphics.getFontMetrics(font);
				size.width = fontMetrics.stringWidth(label.getText());
				size.height = fontMetrics.getHeight();

				return size;
			}

			private static final long serialVersionUID = -6207535289854212799L;
			private Graphics labelGraphics;
		};

		this.labelPanelCard.add(this.nameLabel, BorderLayout.CENTER);

		// Pannello dei posti
		this.seatsPanelCard = new JPanel(new GridLayout(this.numeroFilePerSettore, 0, 3, 3));

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

	/**
	 * Restituisce l'oggetto {@link Settore} collegato a questa rappresentazione
	 * grafica.
	 * 
	 * @return Il Settore collegato a questa rappresentazione grafica.
	 * @author Maurizio
	 */
	public Settore getSettore() {
		return this.settore;
	}

	/**
	 * Restituisce il numero di posti di questo Settore.
	 * 
	 * @return Il numero di posti di questo Settore.
	 * @author Maurizio
	 */
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

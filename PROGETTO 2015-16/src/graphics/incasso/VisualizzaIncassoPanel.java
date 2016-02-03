package graphics.incasso;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.Serializable;
import java.text.DecimalFormat;
import javax.swing.ButtonGroup;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.EtchedBorder;
import struttura.Stadio;
import struttura.StrutturaSportiva;
import struttura.filters.PurchaseByStadiumFilter;

/**
 * Classe che modella un JPanel per la visualizzazione dell'incasso della struttura
 * 
 * @author Gaetano Antonucci
 */
public class VisualizzaIncassoPanel extends JPanel implements Serializable {

	/**
	 * Crea un nuovo pannello per la visualizzazione degli incassi della
	 * struttura sportiva
	 * 
	 * @param struct
	 *            La struttura sportiva di cui si vogliono conoscere i totali.
	 * @throws NullPointerException
	 *             Se la struttura sportiva è null.
	 * @throws IllegalArgumentException
	 *             Se la struttura sportiva non contiene stadi.
	 */
	public VisualizzaIncassoPanel(StrutturaSportiva struct) throws NullPointerException, IllegalArgumentException {
		this.struct = struct;

		if (this.struct == null) {
			throw new NullPointerException("La struttura sportiva non e' presente !!!");
		}

		if (this.struct.getStadi() == null) {
			throw new IllegalArgumentException("Attenzione, non sono presenti stadi !!!");
		}

		if (struct.getStadi().size() == 0) {
			throw new IllegalArgumentException("Attenzione, non sono presenti stadi !!!");
		}

		this.radioPanel = new JPanel();
		this.radioPanel.setBackground(Color.GRAY);
		this.comboButtonPanel = new JPanel();
		this.comboButtonPanel.setBackground(Color.GRAY);

		// Metodi di supporto
		this.init();

		this.setSize(300, 200);

	}

	/**
	 * Inizializza le varie componenti del Panel
	 */
	public void init() {
		this.impostaTitolo();
		this.impostaLineaIncasso();
		this.createElements();

		this.setLayout(new GridLayout(3, 1, 0, 0));

		JPanel titoloPanel = new JPanel(new GridLayout(1, 1, 3, 3));
		titoloPanel.setBorder(new EtchedBorder());
		titoloPanel.setBackground(Color.DARK_GRAY);
		titoloPanel.add(this.titolo);

		JPanel operazioniPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		operazioniPanel.setBorder(new EtchedBorder());
		operazioniPanel.setBackground(Color.GRAY);
		operazioniPanel.add(radioPanel);
		operazioniPanel.add(comboButtonPanel);

		JPanel incassoPanel = new JPanel(new GridLayout(1, 1, 3, 3));
		incassoPanel.add(this.labelIncasso);

		this.add(titoloPanel);
		this.add(operazioniPanel);
		this.add(incassoPanel);
	}

	/**
	 * Imposta il titolo del Panel, ossia inizializza la JLabel con il titolo
	 */
	public void impostaTitolo() {
		this.titolo = new JLabel("Visualizza Incasso:");
		this.titolo.setHorizontalAlignment(JLabel.CENTER);
		this.titolo.setFont(new Font(titolo.getFont().getName(), Font.BOLD, 18));
		this.titolo.setForeground(Color.WHITE);
	}

	/**
	 * Imposta la linea che visualizzera' l'incasso.
	 */
	public void impostaLineaIncasso() {
		this.labelIncasso = new JLabel();
		this.labelIncasso.setHorizontalAlignment(JLabel.CENTER);
		this.labelIncasso.setFont(new Font(labelIncasso.getFont().getName(), Font.BOLD, 20));
		this.labelIncasso.setForeground(Color.BLACK);
	}

	/**
	 * Crea la ComboBox che conterra' gli Stadi
	 */
	private void createComboStadi() {
		this.comboStadi = new JComboBox<Stadio>(
				this.struct.getStadi().toArray(new Stadio[this.struct.getStadi().size()]));
		this.comboStadi.setSelectedIndex(0);

		this.comboStadi.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {

				if (e.getStateChange() == ItemEvent.SELECTED) {
					String totale = CURRENCY_FORMATTER.format(struct.calcolaIncasso(
							struct.getAcquistiFiltrati(new PurchaseByStadiumFilter((Stadio) comboStadi.getSelectedItem()))));
					labelIncasso.setText("Incasso: " + totale);
				}

			}

		});
	}

	/**
	 * Crea i RadioButton per la scelta di visualizzazione dell'incasso
	 * 
	 * @throws IllegalArgumentException
	 */
	private void createElements() throws IllegalArgumentException {
		ButtonGroup gruppoRadio = new ButtonGroup();
		this.radioTotale = new JRadioButton("Totale", true);
		this.radioTotale.setFont(new Font(radioTotale.getFont().getName(), Font.PLAIN, 16));
		this.radioTotale.setForeground(Color.BLUE);
		this.radioTotale.setOpaque(true);
		this.radioTotale.setBackground(Color.GRAY);
		this.radioTotale.setBorder(null);

		this.radioTotale.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				comboButtonPanel.removeAll(); // bisogna eseguire il removeAll()
												// e il revalidate() per
												// rimuovere la combo
				comboButtonPanel.revalidate();
				String totale = CURRENCY_FORMATTER.format(struct.calcolaIncasso(struct.getAcquisti()));
				labelIncasso.setText("Incasso: " + totale);
			}
		});

		this.radioPerStadio = new JRadioButton("per Stadio");
		this.radioPerStadio.setFont(new Font(radioPerStadio.getFont().getName(), Font.PLAIN, 16));
		this.radioPerStadio.setForeground(Color.BLUE);
		this.radioPerStadio.setOpaque(true);
		this.radioPerStadio.setBackground(Color.GRAY);
		this.radioPerStadio.setBorder(null);

		this.radioPerStadio.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				createComboStadi();
				comboButtonPanel.removeAll();
				comboButtonPanel.add(comboStadi);
				comboButtonPanel.revalidate();
				String totale = CURRENCY_FORMATTER.format(struct.calcolaIncasso(
						struct.getAcquistiFiltrati(new PurchaseByStadiumFilter((Stadio) comboStadi.getSelectedItem()))));
				labelIncasso.setText("Incasso: " + totale);
			}
		});

		gruppoRadio.add(radioTotale);
		gruppoRadio.add(radioPerStadio);

		this.radioPanel.add(radioTotale);
		this.radioPanel.add(radioPerStadio);

		this.radioTotale.doClick();

	}

	private JPanel radioPanel;
	private JPanel comboButtonPanel;
	private JRadioButton radioTotale;
	private JRadioButton radioPerStadio;
	private JComboBox<Stadio> comboStadi;
	private JLabel titolo;
	private JLabel labelIncasso;
	private StrutturaSportiva struct;

	private static final String CURRENCY_SYMBOL = DecimalFormat.getInstance().getCurrency().getSymbol();
	private static final DecimalFormat CURRENCY_FORMATTER = new DecimalFormat(CURRENCY_SYMBOL + " " + "###,###,##0.00");

	private static final long serialVersionUID = -4738376842736705159L;

	public static void main(String[] args) {
		JFrame frame = new JFrame("Test");

		StrutturaSportiva prova = new StrutturaSportiva("Prova");

		Stadio olimpico = new Stadio("Olimpico", 50000, 20.00);
		prova.addStadio(olimpico);
		Stadio sanSiro = new Stadio("San Siro", 30000, 30.00);
		prova.addStadio(sanSiro);
		frame.add(new VisualizzaIncassoPanel(prova));

		frame.setSize(500, 200);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

}

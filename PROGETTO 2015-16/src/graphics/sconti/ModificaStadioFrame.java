package graphics.sconti;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.Serializable;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JSpinner.NumberEditor;
import javax.swing.SpinnerNumberModel;
import struttura.Stadio;

public class ModificaStadioFrame extends JFrame implements Serializable {

	/**
	 * Crea un nuovo frame che permette di aggiungere sconti agli stadi passati
	 * in input.
	 * 
	 * @param stadi
	 * @throws IllegalArgumentException
	 *             Se la dimensione degli stadi è uguale a 0.
	 * @throws NullPointerException
	 *             Se l'ArrayList stadi sono null.
	 * @author Maurizio
	 * @author Gaetano
	 */
	public ModificaStadioFrame(ArrayList<Stadio> stadi) throws IllegalArgumentException, NullPointerException {
		super("Sconto Stadio");
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setLayout(new GridLayout(4, 1, 10, 10));

		if (stadi == null) {
			throw new NullPointerException("stadi non può essere null");
		}

		if (stadi.size() == 0) {
			throw new IllegalArgumentException("E' richiesta la presenza di almeno uno stadio.");
		}

		this.stadi = stadi;

		this.init();
		this.pack();
		this.setResizable(false);
	}

	/**
	 * Inizializza i vari componenti di questo frame.
	 * 
	 * @author Maurizio
	 */
	private void init() {
		this.initStadiComboPanel();
		this.initCapienzaPanel();
		this.initPrezzoPanel();
		this.initButtonPanel();
	}

	/**
	 * Inizializza il pannello per selezionare lo stadio.
	 * 
	 * @author Maurizio
	 */
	private void initStadiComboPanel() {
		this.stadioLabel = new JLabel("Stadio: ");
		this.stadiCombo = new JComboBox<>(this.stadi.toArray(new Stadio[0]));
		/*
		 * Sicuro, perchè nel caso non vi siano stadi viene lanciata l'eccezione
		 * illegalargumentexception.
		 */
		this.stadiCombo.setSelectedIndex(0);

		this.stadiCombo.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {

				if (e.getStateChange() == ItemEvent.SELECTED) {
					System.out.println(e.getStateChange() == ItemEvent.SELECTED);
					Stadio stadio = (Stadio) stadiCombo.getSelectedItem();

					capienzaSpinner.setValue(stadio.getCapienzaEffettiva());
					prezzoSpinner.setValue(stadio.getPrezzoPerPartita());

				}

			}
		});

		this.stadiComboPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		this.stadiComboPanel.add(this.stadioLabel);
		this.stadiComboPanel.add(this.stadiCombo);
		this.add(this.stadiComboPanel);
	}

	/**
	 * Inizializza il pannello per selezionare la data di inizio validita.
	 * 
	 * @author Maurizio
	 */
	private void initCapienzaPanel() {
		this.capienzaLabel = new JLabel("Capienza: ");
		this.capienzaSpinner = new JSpinner(
				new SpinnerNumberModel(((Stadio) stadiCombo.getSelectedItem()).getCapienzaEffettiva(),
						Stadio.CAPIENZA_MINIMA, Stadio.CAPIENZA_MASSIMA, 500));
		JSpinner.NumberEditor editor = (NumberEditor) capienzaSpinner.getEditor();
		editor.getTextField().setEditable(false);
		this.capienzaPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		this.capienzaPanel.add(this.capienzaLabel);
		this.capienzaPanel.add(this.capienzaSpinner);
		this.add(this.capienzaPanel);
	}

	/**
	 * Inizializza il pannello per selezionare la percentuale di sconto.
	 * 
	 * @author Maurizio
	 */
	private void initPrezzoPanel() {
		this.prezzoLabel = new JLabel("Prezzo per Partita: ");
		/*
		 * I valori vengono impostati in questo modo poichè, con l'editor per la
		 * percentuale saranno moltiplicati per 100.
		 */
		this.prezzoSpinner = new JSpinner(
				new SpinnerNumberModel(((Stadio) stadiCombo.getSelectedItem()).getPrezzoPerPartita(),
						Stadio.PREZZO_MINIMO, Stadio.PREZZO_MASSIMO, 0.5));
		String currencySymbol = DecimalFormatSymbols.getInstance().getCurrencySymbol();
		String currencyPattern = currencySymbol + " ###" + '.' + "00";

		JSpinner.NumberEditor editor = new JSpinner.NumberEditor(prezzoSpinner, currencyPattern);
		editor.getTextField().setEditable(false);
		this.prezzoSpinner.setEditor(editor);
		this.percentualeScontoPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		this.percentualeScontoPanel.add(this.prezzoLabel);
		this.percentualeScontoPanel.add(this.prezzoSpinner);
		this.add(this.percentualeScontoPanel);
	}

	/**
	 * Inizializza il pannello contenente i pulsanti per applicare le modifiche
	 * o per annullare l'operazione.
	 * 
	 * @author Maurizio
	 */
	private void initButtonPanel() {
		this.applicaModificheButton = new JButton("Applica Modifiche");
		this.applicaModificheButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				Stadio stadio = (Stadio) stadiCombo.getSelectedItem();
				int capienza = (int) capienzaSpinner.getValue();
				double prezzo = (double) prezzoSpinner.getValue();

				stadio.setCapienzaStadio(capienza);
				stadio.setPrezzoPerPartita(prezzo);

				dispose();
			}
		});

		this.annullaButton = new JButton("Annulla");
		this.annullaButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent paramActionEvent) {
				dispose();
			}
		});

		this.buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		this.buttonPanel.add(this.applicaModificheButton);
		this.buttonPanel.add(this.annullaButton);
		this.add(this.buttonPanel);
	}

	/***************************************************************************************/
	private static final long serialVersionUID = 260663801632901839L;
	private ArrayList<Stadio> stadi;
	private JLabel stadioLabel;
	private JComboBox<Stadio> stadiCombo;
	private JPanel stadiComboPanel;
	/***************************************/
	private JLabel capienzaLabel;
	private JSpinner capienzaSpinner;
	private JPanel capienzaPanel;
	/*************************************/
	private JLabel prezzoLabel;
	private JSpinner prezzoSpinner;
	private JPanel percentualeScontoPanel;
	/*************************************/
	private JButton applicaModificheButton, annullaButton;
	private JPanel buttonPanel;

	/*************************************/

	public static void main(String[] args) {

		Stadio s = new Stadio("Meazza", 100000, 10);
		ArrayList<Stadio> stadi = new ArrayList<>();
		stadi.add(s);
		stadi.add(new Stadio("Olimpico", 60000, 30));

		ModificaStadioFrame scontoStadioFrame = new ModificaStadioFrame(stadi);
		scontoStadioFrame.setLocationRelativeTo(null);
		scontoStadioFrame.setVisible(true);
	}
}

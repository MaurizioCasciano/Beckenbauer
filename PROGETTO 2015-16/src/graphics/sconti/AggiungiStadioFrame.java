package graphics.sconti;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;
import java.text.DecimalFormatSymbols;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JSpinner.NumberEditor;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;

import struttura.AlreadyExistsObjectException;
import struttura.Stadio;
import struttura.StrutturaSportiva;

public class AggiungiStadioFrame extends JFrame implements Serializable {

	/**
	 * Crea un nuovo frame che permette di aggiungere sconti agli stadi passati
	 * in input.
	 * 
	 * @param strutturaSportiva
	 * @author Maurizio
	 * @author Gaetano
	 */
	public AggiungiStadioFrame(StrutturaSportiva strutturaSportiva) throws NullPointerException {
		super("Stadio");
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setLayout(new GridLayout(4, 1, 10, 10));

		this.strutturaSportiva = strutturaSportiva;
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
		this.initNomeStadioPanel();
		this.initCapienzaPanel();
		this.initPrezzoPanel();
		this.initButtonPanel();
	}

	/**
	 * Inizializza il pannello per selezionare lo stadio.
	 * 
	 * @author Maurizio
	 */
	private void initNomeStadioPanel() {
		this.nomeStadioLabel = new JLabel("Nome Stadio: ");
		this.nomeStadioTextField = new JTextField(10);
		this.nomeStadioPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		this.nomeStadioPanel.add(this.nomeStadioLabel);
		this.nomeStadioPanel.add(this.nomeStadioTextField);
		this.add(this.nomeStadioPanel);
	}

	/**
	 * Inizializza il pannello per selezionare la data di inizio validita.
	 * 
	 * @author Maurizio
	 */
	private void initCapienzaPanel() {
		this.capienzaLabel = new JLabel("Capienza: ");
		this.capienzaSpinner = new JSpinner(
				new SpinnerNumberModel(Stadio.CAPIENZA_MINIMA, Stadio.CAPIENZA_MINIMA, Stadio.CAPIENZA_MASSIMA, 500));
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
		this.prezzoSpinner = new JSpinner(new SpinnerNumberModel(5, 5, 500, 0.5));
		String currencySymbol = DecimalFormatSymbols.getInstance().getCurrencySymbol();
		String currencyPattern = "###" + '.' + "#" + currencySymbol;

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
		this.aggiungiStadioButton = new JButton("Aggiungi Stadio");
		this.aggiungiStadioButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				String nome = nomeStadioTextField.getText();
				int capienza = (int) capienzaSpinner.getValue();
				double prezzo = (double) prezzoSpinner.getValue();
				Stadio newStadio = new Stadio(nome, capienza, prezzo);

				try {
					strutturaSportiva.addStadio(newStadio);
				} catch (AlreadyExistsObjectException e2) {
					JOptionPane.showMessageDialog(AggiungiStadioFrame.this, e2.getMessage(), "Stadio gia' presente.",
							JOptionPane.WARNING_MESSAGE);
				}

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
		this.buttonPanel.add(this.aggiungiStadioButton);
		this.buttonPanel.add(this.annullaButton);
		this.add(this.buttonPanel);
	}

	/***************************************************************************************/
	private static final long serialVersionUID = 260663801632901839L;
	private JLabel nomeStadioLabel;
	private JTextField nomeStadioTextField;
	private JPanel nomeStadioPanel;
	/***************************************/
	private JLabel capienzaLabel;
	private JSpinner capienzaSpinner;
	private JPanel capienzaPanel;
	/*************************************/
	private JLabel prezzoLabel;
	private JSpinner prezzoSpinner;
	private JPanel percentualeScontoPanel;
	/*************************************/
	private JButton aggiungiStadioButton, annullaButton;
	private JPanel buttonPanel;
	/*************************************/
	private StrutturaSportiva strutturaSportiva;

	/*************************************/

	public static void main(String[] args) {
		AggiungiStadioFrame scontoStadioFrame = new AggiungiStadioFrame(new StrutturaSportiva(""));
		scontoStadioFrame.setLocationRelativeTo(null);
		scontoStadioFrame.setVisible(true);
	}
}

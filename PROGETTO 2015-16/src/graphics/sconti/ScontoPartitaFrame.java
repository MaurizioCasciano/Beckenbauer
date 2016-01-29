package graphics.sconti;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import calendar.DateTimePicker;
import struttura.Partita;
import struttura.Sconti;
import struttura.Squadra;
import struttura.Stadio;
import struttura.StrutturaSportiva;
import struttura.TipoSconto;

/**
 * Classe che modella un frame per l'inserimento di Sconti su una determinata partita
 * 
 * @author Gaetano Antonucci
 * @author Maurizio Casciano
 */
public class ScontoPartitaFrame extends JFrame implements Serializable {

	/**
	 * Crea un nuovo frame che permette di aggiungere sconti alle partite passate
	 * in input.
	 * 
	 * @param strutturaSportiva
	 * @param partite
	 *            L'ArrayList delle partite da mostrare per la scelta.
	 * @throws IllegalArgumentException
	 *             Se la dimensione delle partite &egrave uguale a 0.
	 * @throws NullPointerException
	 *             Se la strutturaSportiva o l'ArrayList partite sono null.
	 * @author Maurizio Casciano
	 * @author Gaetano Antonucci
	 */
	public ScontoPartitaFrame(StrutturaSportiva strutturaSportiva, ArrayList<Partita> partite)
			throws IllegalArgumentException, NullPointerException {
		super("Sconto Partite");
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setLayout(new GridLayout(5, 1, 10, 10));

		if (strutturaSportiva == null) {
			throw new NullPointerException("strutturaSportiva non puo' essere null");
		}

		if (partite == null) {
			throw new NullPointerException("partite non puo' essere null");
		}

		if (partite.size() == 0) {
			throw new IllegalArgumentException("E' richiesta la presenza di almeno una partita.");
		}

		this.strutturaSportiva = strutturaSportiva;
		this.partite = partite;

		this.init();
		this.pack();
		this.setResizable(false);
	}

	/**
	 * Inizializza i vari componenti di questo frame.
	 * 
	 * @author Maurizio Casciano
	 */
	private void init() {
		this.initPartiteComboPanel();
		this.initDataInizioPanel();
		this.initDataFinePanel();
		this.initPercentualeScontoPanel();
		this.initButtonPanel();
	}

	/**
	 * Inizializza il pannello per selezionare la partita
	 * 
	 * @author Maurizio Casciano
	 */
	private void initPartiteComboPanel() {
		this.stadioLabel = new JLabel("Partita: ");
		this.partiteCombo = new JComboBox<>(this.partite.toArray(new Partita[0]));
		/*
		 * Sicuro, perche' nel caso non vi siano partite viene lanciata l'eccezione
		 * illegalargumentexception.
		 */
		this.partiteCombo.setSelectedIndex(0);
		this.partiteComboPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		this.partiteComboPanel.add(this.stadioLabel);
		this.partiteComboPanel.add(this.partiteCombo);
		this.add(this.partiteComboPanel);
	}

	/**
	 * Inizializza il pannello per selezionare la data di inizio validita.
	 * 
	 * @author Maurizio
	 */
	private void initDataInizioPanel() {
		this.inizioLabel = new JLabel("Data inizio validita': ");
		this.inizioPicker = new DateTimePicker();
		this.inizioPicker.getMonthView().setLowerBound(new Date());
		/*
		 * Reimposto la data affinche' sia maggiore del LowerBound, altrimenti
		 * alla chiamata del metodo getDate() restituirebe null in quanto la
		 * data impostata risulta antecedente al lowerBound e quindi non
		 * ammessa.
		 */
		this.inizioPicker.setDate(new Date());
		this.dataInizioPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		this.dataInizioPanel.add(this.inizioLabel);
		this.dataInizioPanel.add(this.inizioPicker);
		this.add(this.dataInizioPanel);
	}

	/**
	 * Inizializza il pannello per selezionare la data di fine validita.
	 * 
	 * @author Maurizio Casciano
	 */
	private void initDataFinePanel() {
		this.fineLabel = new JLabel("Data fine validita': ");
		this.finePicker = new DateTimePicker();
		this.finePicker.getMonthView().setLowerBound(new Date());
		/*
		 * Reimposto la data affinche' sia maggiore del LowerBound, altrimenti
		 * alla chiamata del metodo getDate() restituirebe null in quanto la
		 * data impostata risulta antecedente al lowerBound e quindi non
		 * ammessa.
		 */
		this.finePicker.setDate(new Date());
		this.dataFinePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		this.dataFinePanel.add(this.fineLabel);
		this.dataFinePanel.add(this.finePicker);
		this.add(this.dataFinePanel);
	}

	/**
	 * Inizializza il pannello per selezionare la percentuale di sconto.
	 * 
	 * @author Maurizio Casciano
	 */
	private void initPercentualeScontoPanel() {
		this.percentualeScontoLabel = new JLabel("Percentuale sconto: ");
		/*
		 * I valori vengono impostati in questo modo poich�, con l'editor per la
		 * percentuale saranno moltiplicati per 100.
		 */
		this.percetualeScontoSpinner = new JSpinner(new SpinnerNumberModel(0.01, 0.01, 1, 0.01));
		char digit = DecimalFormatSymbols.getInstance().getDigit();
		char percentSign = DecimalFormatSymbols.getInstance().getPercent();
		String percentPattern = digit + "" + percentSign;
		JSpinner.NumberEditor editor = new JSpinner.NumberEditor(percetualeScontoSpinner, percentPattern);
		editor.getTextField().setEditable(false);
		this.percetualeScontoSpinner.setEditor(editor);
		this.percentualeScontoPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		this.percentualeScontoPanel.add(this.percentualeScontoLabel);
		this.percentualeScontoPanel.add(this.percetualeScontoSpinner);
		this.add(this.percentualeScontoPanel);
	}

	/**
	 * Inizializza il pannello contenente i pulsanti per applicare lo sconto o
	 * per annullare l'operazione.
	 * 
	 * @author Gaetano Antonucci
	 * @author Maurizio Casciano
	 */
	private void initButtonPanel() {
		this.applicaScontoButton = new JButton("Applica Sconto");
		this.applicaScontoButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				GregorianCalendar dataInizio = new GregorianCalendar();
				dataInizio.setTime(inizioPicker.getDate());

				GregorianCalendar dataFine = new GregorianCalendar();
				dataFine.setTime(finePicker.getDate());

				Partita part = (Partita) partiteCombo.getSelectedItem();

				/*
				 * Moltiplico il valore per 100 poiche' il valore restituito e'
				 * compreso tra 0.01 e 1
				 */
				double sconto = (double) (percetualeScontoSpinner.getValue()) * 100;

				Sconti s = new Sconti(TipoSconto.PartitaCorrente, sconto, dataInizio, dataFine, part);

				strutturaSportiva.addSconto(s);
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
		this.buttonPanel.add(this.applicaScontoButton);
		this.buttonPanel.add(this.annullaButton);
		this.add(this.buttonPanel);
	}

	/***************************************************************************************/
	private static final long serialVersionUID = 260663801632901839L;
	private ArrayList<Partita> partite;
	private JLabel stadioLabel;
	private JComboBox<Partita> partiteCombo;
	private JPanel partiteComboPanel;
	/***************************************/
	private JLabel inizioLabel;
	private DateTimePicker inizioPicker;
	private JPanel dataInizioPanel;
	/**************************************/
	private JLabel fineLabel;
	private DateTimePicker finePicker;
	private JPanel dataFinePanel;
	/*************************************/
	private JLabel percentualeScontoLabel;
	private JSpinner percetualeScontoSpinner;
	private JPanel percentualeScontoPanel;
	/*************************************/
	private JButton applicaScontoButton, annullaButton;
	private JPanel buttonPanel;
	/*************************************/
	private StrutturaSportiva strutturaSportiva;

	/*************************************/

	public static void main(String[] args) {

		Stadio s = new Stadio("Meazza", 10, 10);
		ArrayList<Partita> partite = new ArrayList<>();
		partite.add(new Partita(new Squadra("Inter"), new Squadra("Milan"), s, new GregorianCalendar()));

		ScontoPartitaFrame scontoStadioFrame = new ScontoPartitaFrame(new StrutturaSportiva(""), partite);
		scontoStadioFrame.setLocationRelativeTo(null);
		scontoStadioFrame.setVisible(true);
	}
}

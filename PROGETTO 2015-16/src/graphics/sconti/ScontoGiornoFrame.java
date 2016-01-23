package graphics.sconti;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;
import java.text.DecimalFormatSymbols;
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
import struttura.DAYS_OF_WEEK;
import struttura.Sconti;
import struttura.StrutturaSportiva;
import struttura.TIPO_SCONTO;

public class ScontoGiornoFrame extends JFrame implements Serializable {

	/**
	 * Crea un nuovo frame che permette di aggiungere sconti agli stadi passati
	 * in input.
	 * 
	 * @param strutturaSportiva
	 * @param stadi
	 * @throws IllegalArgumentException
	 *             Se la dimensione degli stadi è uguale a 0.
	 * @throws NullPointerException
	 *             Se la strutturaSportiva o l'ArrayList stadi sono null.
	 * @author Maurizio
	 * @author Gaetano
	 */
	public ScontoGiornoFrame(StrutturaSportiva strutturaSportiva)
			throws IllegalArgumentException, NullPointerException {
		super("Sconto Stadio");
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setLayout(new GridLayout(5, 1, 10, 10));

		if (strutturaSportiva == null) {
			throw new NullPointerException("strutturaSportiva non può essere null");
		}

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
		this.initGiorniComboPanel();
		this.initDataInizioPanel();
		this.initDataFinePanel();
		this.initPercentualeScontoPanel();
		this.initButtonPanel();
	}

	/**
	 * Inizializza il pannello per selezionare lo stadio.
	 * 
	 * @author Maurizio
	 */
	private void initGiorniComboPanel() {
		this.giornoLabel = new JLabel("Giorno: ");
		DAYS_OF_WEEK[] giorniDaInserire = new DAYS_OF_WEEK[7];
		giorniDaInserire[0] = DAYS_OF_WEEK.Lunedi;
		giorniDaInserire[1] = DAYS_OF_WEEK.Martedi;
		giorniDaInserire[2] = DAYS_OF_WEEK.Mercoledi;
		giorniDaInserire[3] = DAYS_OF_WEEK.Giovedi;
		giorniDaInserire[4] = DAYS_OF_WEEK.Venerdi;
		giorniDaInserire[5] = DAYS_OF_WEEK.Sabato;
		giorniDaInserire[6] = DAYS_OF_WEEK.Domenica;

		this.giorniCombo = new JComboBox<>(giorniDaInserire);
		this.giorniCombo.setSelectedItem(DAYS_OF_WEEK.Lunedi);

		this.stadiComboPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		this.stadiComboPanel.add(this.giornoLabel);
		this.stadiComboPanel.add(this.giorniCombo);
		this.add(this.stadiComboPanel);
	}

	/**
	 * Inizializza il pannello per selezionare la data di inizio validita.
	 * 
	 * @author Maurizio
	 */
	private void initDataInizioPanel() {
		this.inizioLabel = new JLabel("Data inizio validità: ");
		this.inizioPicker = new DateTimePicker();
		this.inizioPicker.getMonthView().setLowerBound(new Date());
		/*
		 * Reimposto la data affinchè sia maggiore del LowerBound, altrimenti
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
	 * @author Maurizio
	 */
	private void initDataFinePanel() {
		this.fineLabel = new JLabel("Data fine validità: ");
		this.finePicker = new DateTimePicker();
		this.finePicker.getMonthView().setLowerBound(new Date());
		/*
		 * Reimposto la data affinchè sia maggiore del LowerBound, altrimenti
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
	 * @author Maurizio
	 */
	private void initPercentualeScontoPanel() {
		this.percentualeScontoLabel = new JLabel("Percentuale sconto: ");
		/*
		 * I valori vengono impostati in questo modo poichè, con l'editor per la
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
	 * @author Maurizio
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

				DAYS_OF_WEEK day = (DAYS_OF_WEEK) giorniCombo.getSelectedItem();

				/*
				 * Moltiplico il valore per 100 poichè il valore restituito è
				 * compreso tra 0.01 e 1
				 */
				double sconto = (double) (percetualeScontoSpinner.getValue()) * 100;

				Sconti s = new Sconti(TIPO_SCONTO.GiornoPrestabilito, sconto, dataInizio, dataFine, day);

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
	private JLabel giornoLabel;
	private JComboBox<DAYS_OF_WEEK> giorniCombo;
	private JPanel stadiComboPanel;
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
		ScontoGiornoFrame scontoStadioFrame = new ScontoGiornoFrame(new StrutturaSportiva(""));
		scontoStadioFrame.setLocationRelativeTo(null);
		scontoStadioFrame.setVisible(true);
	}
}

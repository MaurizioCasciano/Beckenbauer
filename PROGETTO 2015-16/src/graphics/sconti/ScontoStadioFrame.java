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
import struttura.Sconto;
import struttura.Stadio;
import struttura.StrutturaSportiva;
import struttura.TipoSconto;

/**
 * Classe che modella un frame per l'inserimento degli sconti in base allo stadio
 * 
 * @author Gaetano Antonucci
 * @author Gaetano Antonucci
 */
public class ScontoStadioFrame extends JFrame implements Serializable {

	/**
	 * Crea un nuovo frame che permette di aggiungere sconti agli stadi passati
	 * in input.
	 * 
	 * @param strutturaSportiva
	 *            La StrutturaSportiva che gestisce gli sconti.
	 * @param stadi
	 *            Gli stadi della StrutturaSportiva.
	 * @throws IllegalArgumentException
	 *             Se la dimensione degli stadi e' uguale a 0.
	 * @throws NullPointerException
	 *             Se la strutturaSportiva o l'ArrayList stadi sono null.
	 * @author Maurizio Casciano 
	 * @author Gaetano Antonucci
	 */
	public ScontoStadioFrame(StrutturaSportiva strutturaSportiva, ArrayList<Stadio> stadi)
			throws IllegalArgumentException, NullPointerException {
		super("Sconto Stadio");
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setLayout(new GridLayout(5, 1, 10, 10));

		if (strutturaSportiva == null) {
			throw new NullPointerException("strutturaSportiva non puo' essere null");
		}

		if (stadi == null) {
			throw new NullPointerException("stadi non puo' essere null");
		}

		if (stadi.size() == 0) {
			throw new IllegalArgumentException("E' richiesta la presenza di almeno uno stadio.");
		}

		this.strutturaSportiva = strutturaSportiva;
		this.stadi = stadi;

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
		this.initStadiComboPanel();
		this.initDataInizioPanel();
		this.initDataFinePanel();
		this.initPercentualeScontoPanel();
		this.initButtonPanel();
	}

	/**
	 * Inizializza il pannello per selezionare lo stadio.
	 * 
	 * @author Maurizio Casciano
	 */
	private void initStadiComboPanel() {
		this.stadioLabel = new JLabel("Stadio: ");
		this.stadiCombo = new JComboBox<>(this.stadi.toArray(new Stadio[0]));
		/*
		 * Sicuro, perche' nel caso non vi siano stadi viene lanciata l'eccezione
		 * illegalargumentexception.
		 */
		this.stadiCombo.setSelectedIndex(0);
		this.stadiComboPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		this.stadiComboPanel.add(this.stadioLabel);
		this.stadiComboPanel.add(this.stadiCombo);
		this.add(this.stadiComboPanel);
	}

	/**
	 * Inizializza il pannello per selezionare la data di inizio validita'.
	 * 
	 * @author Maurizio Casciano
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
	 * Inizializza il pannello per selezionare la data di fine validita'.
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
		 * I valori vengono impostati in questo modo poiche', con l'editor per la
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

				Stadio std = (Stadio) stadiCombo.getSelectedItem();

				/*
				 * Moltiplico il valore per 100 poiche' il valore restituito e'
				 * compreso tra 0.01 e 1
				 */
				double sconto = (double) (percetualeScontoSpinner.getValue()) * 100;

				Sconto s = new Sconto(TipoSconto.TUTTE_LE_PARTITE_DELLO_STADIO, sconto, dataInizio, dataFine, std);

				strutturaSportiva.addSconto(s);

				System.out.println("SCONTI DOPO = " + strutturaSportiva.getSconti().size());
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

	private static final long serialVersionUID = 260663801632901839L;
	private ArrayList<Stadio> stadi;
	private JLabel stadioLabel;
	private JComboBox<Stadio> stadiCombo;
	private JPanel stadiComboPanel;
	private JLabel inizioLabel;
	private DateTimePicker inizioPicker;
	private JPanel dataInizioPanel;
	private JLabel fineLabel;
	private DateTimePicker finePicker;
	private JPanel dataFinePanel;
	private JLabel percentualeScontoLabel;
	private JSpinner percetualeScontoSpinner;
	private JPanel percentualeScontoPanel;
	private JButton applicaScontoButton, annullaButton;
	private JPanel buttonPanel;
	private StrutturaSportiva strutturaSportiva;

	public static void main(String[] args) {

		Stadio s = new Stadio("Meazza", 20000, 10);
		ArrayList<Stadio> stadi = new ArrayList<>();
		stadi.add(s);
		stadi.add(new Stadio("Olimpico", 60000, 30));

		ScontoStadioFrame scontoStadioFrame = new ScontoStadioFrame(new StrutturaSportiva(""), stadi);
		scontoStadioFrame.setLocationRelativeTo(null);
		scontoStadioFrame.setVisible(true);
	}
}

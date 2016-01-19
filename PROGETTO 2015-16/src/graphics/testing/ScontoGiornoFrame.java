package graphics.testing;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;
import java.util.GregorianCalendar;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;

import calendar.DateTimePicker;
import struttura.*;

public class ScontoGiornoFrame extends JFrame implements Serializable{

	public ScontoGiornoFrame(StrutturaSportiva s){
		
		this.struct = s;
		
		//Metodi di supporto
		this.createLabels();
		this.createComboPartite();
		this.createFields();
		this.createButtons();
		this.createMainPanel();
		
		this.add(this.mainPanel);
		
		this.setTitle("Sconto su Giorno della Settimana");
		this.setBounds(100, 200, 600, 200);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setVisible(true);
	}
	
	public void createComboPartite(){
		DAYS_OF_WEEK[] giorniDaInserire = new DAYS_OF_WEEK[7];
		giorniDaInserire[0] = DAYS_OF_WEEK.Lunedi;
		giorniDaInserire[1] = DAYS_OF_WEEK.Martedi;
		giorniDaInserire[2] = DAYS_OF_WEEK.Mercoledi;
		giorniDaInserire[3] = DAYS_OF_WEEK.Giovedi;
		giorniDaInserire[4] = DAYS_OF_WEEK.Venerdi;
		giorniDaInserire[5] = DAYS_OF_WEEK.Sabato;
		giorniDaInserire[6] = DAYS_OF_WEEK.Domenica;
		
		this.scegliGiorno = new JComboBox<DAYS_OF_WEEK>(giorniDaInserire);
		this.scegliGiorno.setSelectedItem(DAYS_OF_WEEK.Lunedi);
	
	}

	public void createLabels(){
		this.labelScegliPartita = new JLabel("Scegli Giorno: ");
		this.labeleDataInizioValidità = new JLabel("Data Inizio Validita': ");
		this.labelDataFineValidita = new JLabel("Data Fine Validita': ");
		this.labelPercentualeSconto = new JLabel("Percentuale Sconto: ");
	}
	
	public void createFields(){
		this.dataInizioValidita = new DateTimePicker();
		this.dataFineValidita = new DateTimePicker();
		
		SpinnerModel spinnerSconto = new SpinnerNumberModel(1.0, 1.0, 100.0, 0.5);
		this.percentualeSconto = new JSpinner(spinnerSconto);
	}
	
	public void createButtons(){
		this.applicaSconto = new JButton("Applica Sconto");
		
		class InserisciScontoListener implements ActionListener {

			@Override
			public void actionPerformed(ActionEvent e) {
				GregorianCalendar dataInizio = new GregorianCalendar();
				dataInizio.setTime(dataInizioValidita.getDate());
				
				GregorianCalendar dataFine = new GregorianCalendar();
				dataFine.setTime(dataFineValidita.getDate());
				
				DAYS_OF_WEEK day = (DAYS_OF_WEEK) scegliGiorno.getSelectedItem();
				
				double sconto = (double) (percentualeSconto.getValue());
				
				Sconti s = new Sconti(TIPO_SCONTO.GiornoPrestabilito, sconto, dataInizio, dataFine, day);
				
				struct.addSconto(s);
				dispose();
			}
		}
		
		ActionListener listener = new InserisciScontoListener();
		this.applicaSconto.addActionListener(listener);
		
		this.annulla = new JButton("Annulla");
		this.annulla.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event){
				dispose();
			}
		});
	}
	
	public void createMainPanel(){
		this.mainPanel = new JPanel();
		this.mainPanel.setLayout(new BorderLayout());
		
		JPanel sceltaPanel = new JPanel();
		sceltaPanel.add(this.labelScegliPartita);
		sceltaPanel.add(this.scegliGiorno);
		
		JPanel infoPanel = new JPanel();
		infoPanel.setLayout(new GridLayout(3, 1, 3, 3));
		
		JPanel div = new JPanel();
		div.add(this.labeleDataInizioValidità);
		div.add(this.dataInizioValidita);
		infoPanel.add(div);
		
		JPanel dfv = new JPanel();
		dfv.add(this.labelDataFineValidita);
		dfv.add(this.dataFineValidita);
		infoPanel.add(dfv);
		
		JPanel ps = new JPanel();
		ps.add(this.labelPercentualeSconto);
		ps.add(this.percentualeSconto);
		infoPanel.add(ps);
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.add(this.applicaSconto);
		buttonPanel.add(this.annulla);
		
		mainPanel.add(sceltaPanel, BorderLayout.NORTH);
		mainPanel.add(infoPanel, BorderLayout.CENTER);
		mainPanel.add(buttonPanel, BorderLayout.SOUTH);
	}
	
	private JPanel mainPanel;
	private JLabel labelScegliPartita;
	private JComboBox<DAYS_OF_WEEK> scegliGiorno;
	private JLabel labeleDataInizioValidità;
	private DateTimePicker dataInizioValidita;
	private JLabel labelDataFineValidita;
	private DateTimePicker dataFineValidita;
	private JLabel labelPercentualeSconto;
	private JSpinner percentualeSconto;
	private JButton applicaSconto;
	private JButton annulla;
	
	private StrutturaSportiva struct;
	
	private static final long serialVersionUID = -3236878839345603195L;

}

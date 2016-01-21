package graphics.testing;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;
import java.util.ArrayList;
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
import struttura.comparators.StadiumNameComparator;

public class ScontoStadioFrame extends JFrame implements Serializable{

	public ScontoStadioFrame(StrutturaSportiva s){
		
		this.struct = s;
		
		//Metodi di supporto
		this.createLabels();
		this.createComboPartite();
		this.createFields();
		this.createButtons();
		this.createMainPanel();
		
		this.add(this.mainPanel);
		
		this.setTitle("Sconto su Stadio");
		this.setBounds(100, 200, 600, 200);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setVisible(true);
	}
	
	public void createComboPartite(){
		ArrayList<Stadio> stadiDaInserire = struct.getStadi();
		stadiDaInserire.sort(new StadiumNameComparator());
		this.scegliStadio = new JComboBox<Stadio>(stadiDaInserire.toArray(new Stadio[stadiDaInserire.size()]));
		this.scegliStadio.setSelectedIndex(0);
	
	}

	public void createLabels(){
		this.labelScegliStadio = new JLabel("Scegli Stadio: ");
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
				
				Stadio std = (Stadio) scegliStadio.getSelectedItem();
				
				double sconto = (double) (percentualeSconto.getValue());
				
				Sconti s = new Sconti(TIPO_SCONTO.TutteLePartiteDelloStadio, sconto, dataInizio, dataFine, std);
				
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
		sceltaPanel.add(this.labelScegliStadio);
		sceltaPanel.add(this.scegliStadio);
		
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
	private JLabel labelScegliStadio;
	private JComboBox<Stadio> scegliStadio;
	private JLabel labeleDataInizioValidità;
	private DateTimePicker dataInizioValidita;
	private JLabel labelDataFineValidita;
	private DateTimePicker dataFineValidita;
	private JLabel labelPercentualeSconto;
	private JSpinner percentualeSconto;
	private JButton applicaSconto;
	private JButton annulla;
	
	private StrutturaSportiva struct;
	
	private static final long serialVersionUID = 533648855513519472L;

}

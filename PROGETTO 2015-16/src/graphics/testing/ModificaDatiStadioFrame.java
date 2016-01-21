package graphics.testing;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.Serializable;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;

import struttura.*;

public class ModificaDatiStadioFrame extends JFrame implements Serializable{

	public ModificaDatiStadioFrame(StrutturaSportiva s){
		
		this.struct = s;
		
		//Metodi di supporto
		this.createLabels();
		this.createComboPartite();
		this.createFields();
		this.createButtons();
		this.createMainPanel();
		
		this.add(this.mainPanel);
		
		this.setTitle("Modifica Stadio");
		this.setBounds(100, 200, 600, 200);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setVisible(true);
	}
	
	public void createComboPartite(){
		ArrayList<Stadio> stadiDaInserire = struct.getStadi();
		this.scegliStadio = new JComboBox<Stadio>(stadiDaInserire.toArray(new Stadio[stadiDaInserire.size()]));
		this.scegliStadio.setSelectedIndex(0);
		
		class StadioListener implements ItemListener {

			@Override
			public void itemStateChanged(ItemEvent e) {
				impostaValoriIniziali();
			}
		}
		
		ItemListener listener = new StadioListener();
		this.scegliStadio.addItemListener(listener);
	}

	public void createLabels(){
		this.labelScegliStadio = new JLabel("Scegli Stadio: ");
		this.labelCapienzaStadio = new JLabel("Capienza: ");
		this.labelPrezzoPerPartita = new JLabel("Prezzo per Partita: ");
	}
	
	public void createFields(){
		
		SpinnerModel spinnerCapienza = new SpinnerNumberModel(30000, 30000, 1000000, 1);
		this.capienzaStadio = new JSpinner(spinnerCapienza);
		
		SpinnerModel spinnerPrezzo = new SpinnerNumberModel(10.00, 10.00, 100.00, 0.5);
		this.prezzoPerPartita = new JSpinner(spinnerPrezzo);
		
		this.impostaValoriIniziali(); //chiamato per impostare i valori del primo stadio visualizzato
	}
	
	public void createButtons(){
		this.applicaModifiche = new JButton("Applica Modifiche");
		
		class InserisciScontoListener implements ActionListener {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				Stadio std = (Stadio) scegliStadio.getSelectedItem();
				int capienza = (int) capienzaStadio.getValue();
				double prezzo = (double) prezzoPerPartita.getValue();
				
				std.setCapienzaStadio(capienza);
				std.setPrezzoPerPartita(prezzo);
				
				dispose();
			}
		}
		
		ActionListener listener = new InserisciScontoListener();
		this.applicaModifiche.addActionListener(listener);
		
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
		infoPanel.setLayout(new GridLayout(2, 1, 3, 3));
		
		JPanel capienzaStd = new JPanel();
		capienzaStd.add(this.labelCapienzaStadio);
		capienzaStd.add(this.capienzaStadio);
		infoPanel.add(capienzaStd);
		
		JPanel prezzoPartitaStd = new JPanel();
		prezzoPartitaStd.add(this.labelPrezzoPerPartita);
		prezzoPartitaStd.add(this.prezzoPerPartita);
		infoPanel.add(prezzoPartitaStd);
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.add(this.applicaModifiche);
		buttonPanel.add(this.annulla);
		
		mainPanel.add(sceltaPanel, BorderLayout.NORTH);
		mainPanel.add(infoPanel, BorderLayout.CENTER);
		mainPanel.add(buttonPanel, BorderLayout.SOUTH);
	}
	
	public void impostaValoriIniziali(){
		this.stadioPerValoriIniziali = (Stadio) this.scegliStadio.getSelectedItem();
		
		this.capienzaStadio.setValue(this.stadioPerValoriIniziali.getCapienzaStadio());
		this.prezzoPerPartita.setValue(this.stadioPerValoriIniziali.getPrezzoPerPartita());
	}
	
	private JPanel mainPanel;
	private JLabel labelScegliStadio;
	private JComboBox<Stadio> scegliStadio;
	private JLabel labelCapienzaStadio;
	private JSpinner capienzaStadio;
	private JLabel labelPrezzoPerPartita;
	private JSpinner prezzoPerPartita;
	private JButton applicaModifiche;
	private JButton annulla;
	
	private StrutturaSportiva struct;
	private Stadio stadioPerValoriIniziali;
	
	private static final long serialVersionUID = -8753199235589760656L;
	

}

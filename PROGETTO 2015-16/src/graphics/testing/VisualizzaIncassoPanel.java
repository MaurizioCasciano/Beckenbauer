package graphics.testing;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import struttura.Stadio;
import struttura.StrutturaSportiva;
import struttura.filters.PurchasesByStadium;

public class VisualizzaIncassoPanel extends JPanel implements Serializable{

	public VisualizzaIncassoPanel(StrutturaSportiva struct){
		this.struct = struct;
		
		//Metodi di supporto
		this.createComboStadi();
		this.createElements();
		this.createButton();
		
		this.setLayout(new BorderLayout());
		this.add(sceltaPanel, BorderLayout.NORTH);
		this.add(labelIncasso, BorderLayout.CENTER);
		
		
	}
	
	public void createComboStadi(){
		// da modificare
		this.selezionaStadio = new JComboBox<Stadio>(this.struct.getStadi().toArray(new Stadio[this.struct.getStadi().size()]));
		
	}
	
	public void createElements(){
		ButtonGroup gruppoRadio = new ButtonGroup();
		this.radioTotale = new JRadioButton("Totale", true);
		
		this.radioTotale.addActionListener(new ActionListener() {
		
			@Override
			public void actionPerformed(ActionEvent e){
				selezionaStadio.setEnabled(false);
			}
		});
		
		this.radioPerStadio = new JRadioButton("per Stadio");
		
		this.radioPerStadio.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				selezionaStadio.setEnabled(true);
			}
			
			
		});
		
		gruppoRadio.add(radioTotale);
		gruppoRadio.add(radioPerStadio);
		
		this.sceltaPanel = new JPanel();
		this.sceltaPanel.add(radioTotale);
		this.sceltaPanel.add(radioPerStadio);
		this.sceltaPanel.add(selezionaStadio);

		this.labelIncasso = new JLabel();
	}
	
	public void createButton(){
		this.visualizzaButton = new JButton("Visualizza");
		
		this.visualizzaButton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				if(radioTotale.isSelected()){
					labelIncasso.setText("" + struct.calcolaIncasso(struct.getAcquisti()));
				} else {
					labelIncasso.setText("" + struct.calcolaIncasso(struct.getAcquistiFiltrati(new PurchasesByStadium((Stadio) selezionaStadio.getSelectedItem()))));
				}
				
			}
			
			
		});
		
		this.sceltaPanel.add(visualizzaButton);
	}
	
	
	private JPanel sceltaPanel;
	private JRadioButton radioTotale;
	private JRadioButton radioPerStadio;
	private JComboBox<Stadio> selezionaStadio;
	private JLabel labelIncasso;
	private JButton visualizzaButton;
	private StrutturaSportiva struct;
	private static final long serialVersionUID = -4738376842736705159L;

}

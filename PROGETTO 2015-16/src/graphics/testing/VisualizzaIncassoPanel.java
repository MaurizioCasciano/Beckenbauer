package graphics.testing;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.Serializable;

import javax.swing.ButtonGroup;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import struttura.Stadio;
import struttura.StrutturaSportiva;
import struttura.filters.PurchasesByStadium;

public class VisualizzaIncassoPanel extends JPanel implements Serializable{

	public VisualizzaIncassoPanel(StrutturaSportiva struct){
		this.struct = struct;
		
		
		if(this.struct == null){
			throw new NullPointerException("La struttura sportiva non è presente !!!");
		}
		
		this.radioPanel = new JPanel();
		this.comboButtonPanel = new JPanel();
		
		//Metodi di supporto
		this.createElements();
		
		this.setLayout(new GridLayout(3, 1, 3, 3));
		JPanel topPanel = new JPanel();
		topPanel.add(radioPanel);
		topPanel.add(comboButtonPanel);
		
		this.titolo = new JLabel("Visualizza Incasso");
		this.titolo.setHorizontalAlignment(JLabel.CENTER);
		this.titolo.setFont(new Font(titolo.getFont().getName(), Font.BOLD, 18));
		
		this.add(titolo);
		this.add(topPanel);
		this.add(labelIncasso);
		
		this.setSize(300, 200);
		
		
	}
	
	private void createComboStadi(){
		this.comboStadi = new JComboBox<Stadio>(this.struct.getStadi().toArray(new Stadio[this.struct.getStadi().size()]));
		this.comboStadi.setSelectedIndex(0);
		
		this.comboStadi.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange() == ItemEvent.SELECTED){
					labelIncasso.setText("Incasso: " + struct.calcolaIncasso(struct.getAcquistiFiltrati(new PurchasesByStadium((Stadio) comboStadi.getSelectedItem()))));
				}
				
			}
			
		});
	}
	
	private void createElements() throws IllegalArgumentException{
		ButtonGroup gruppoRadio = new ButtonGroup();
		this.radioTotale = new JRadioButton("Totale", true);
		this.radioTotale.setFont(new Font(radioTotale.getFont().getName(), Font.PLAIN, 16));;
		
		this.radioTotale.addActionListener(new ActionListener() {
		
			@Override
			public void actionPerformed(ActionEvent e){
				comboButtonPanel.removeAll();  // bisogna eseguire il removeAll() e il revalidate() per rimuovere la combo
				comboButtonPanel.revalidate();
				labelIncasso.setText("Incasso: " + struct.calcolaIncasso(struct.getAcquisti()));
			}
		});
		
		this.radioPerStadio = new JRadioButton("per Stadio");
		this.radioPerStadio.setFont(new Font(radioPerStadio.getFont().getName(), Font.PLAIN, 16));
		
		this.radioPerStadio.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				
				if(struct.getStadi().size() == 0){
					throw new IllegalArgumentException("Attenzione, non sono presenti stadi !!!");
				}
				createComboStadi();
				comboButtonPanel.removeAll();
				comboButtonPanel.add(comboStadi);
				comboButtonPanel.revalidate();
				labelIncasso.setText("Incasso: " + struct.calcolaIncasso(struct.getAcquistiFiltrati(new PurchasesByStadium((Stadio) comboStadi.getSelectedItem()))));
			}
			
			
		});
		
		gruppoRadio.add(radioTotale);
		gruppoRadio.add(radioPerStadio);
		
		
		this.radioPanel.add(radioTotale);
		this.radioPanel.add(radioPerStadio);

		this.labelIncasso = new JLabel();
		this.labelIncasso.setHorizontalAlignment(JLabel.CENTER);
		this.labelIncasso.setFont(new Font(labelIncasso.getFont().getName(), Font.BOLD, 20));
		
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
	
	private static final long serialVersionUID = -4738376842736705159L;
	
	public static void main(String[] args) {
		JFrame frame = new JFrame("Test");
		
		StrutturaSportiva prova = new StrutturaSportiva("Prova");
		prova.addStadio(new Stadio("Olimpico", 50000, 20.00));
		prova.addStadio(new Stadio("San Siro", 30000, 30.00));
		frame.add(new VisualizzaIncassoPanel(prova));
		
		frame.setSize(500, 200);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}

}

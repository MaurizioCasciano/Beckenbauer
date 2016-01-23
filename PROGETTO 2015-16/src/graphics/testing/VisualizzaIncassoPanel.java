package graphics.testing;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.SwingConstants;

import struttura.Stadio;
import struttura.StrutturaSportiva;
import struttura.filters.PurchasesByStadium;

public class VisualizzaIncassoPanel extends JPanel implements Serializable{

	public VisualizzaIncassoPanel(StrutturaSportiva struct){
		this.struct = struct;
		
		
		if(this.struct == null){
			throw new NullPointerException("La struttura sportiva non è presente !!!");
		}
		
		//Metodi di supporto
		this.createButton();
		this.createElements();
		
		this.setLayout(new BorderLayout());
		this.add(sceltaPanel, BorderLayout.NORTH);
		this.add(labelIncasso, BorderLayout.CENTER);
		
		this.setSize(300, 200);
		
		
	}
	
	public void createComboStadi(){
		this.selezionaStadio = new JComboBox<Stadio>(this.struct.getStadi().toArray(new Stadio[this.struct.getStadi().size()]));
		this.selezionaStadio.setSelectedIndex(0);
	}
	
	public void createElements(){
		ButtonGroup gruppoRadio = new ButtonGroup();
		this.radioTotale = new JRadioButton("Totale", true);
		
		/*this.radioTotale.addActionListener(new ActionListener() {
		
			@Override
			public void actionPerformed(ActionEvent e){
				selezionaStadio.setEnabled(false);
			}
		});*/
		
		this.radioPerStadio = new JRadioButton("per Stadio");
		
		this.radioPerStadio.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				if(struct.getStadi().size() == 0){
					throw new IllegalArgumentException("Attenzione, non sono presenti stadi !!!");
				}
				createComboStadi();
				sceltaPanel.add(selezionaStadio);
				sceltaPanel.add(visualizzaButton);
				revalidate();
			}
			
			
		});
		
		gruppoRadio.add(radioTotale);
		gruppoRadio.add(radioPerStadio);
		
		this.sceltaPanel = new JPanel();
		this.sceltaPanel.add(radioTotale);
		this.sceltaPanel.add(radioPerStadio);
		this.sceltaPanel.add(visualizzaButton);

		this.labelIncasso = new JLabel();
		this.labelIncasso.setHorizontalAlignment(SwingConstants.CENTER);
		this.labelIncasso.setFont(new Font(labelIncasso.getFont().getName(), Font.BOLD, 20));

	}
	
	public void createButton(){
		this.visualizzaButton = new JButton("Visualizza");
		
		this.visualizzaButton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				if(radioTotale.isSelected()){
					labelIncasso.setText("" + struct.calcolaIncasso(struct.getAcquisti()));
				} else if(radioPerStadio.isSelected()){
					labelIncasso.setText("" + struct.calcolaIncasso(struct.getAcquistiFiltrati(new PurchasesByStadium((Stadio) selezionaStadio.getSelectedItem()))));
				}
				
			}
			
			
		});
	}
	
	
	private JPanel sceltaPanel;
	private JRadioButton radioTotale;
	private JRadioButton radioPerStadio;
	private JComboBox<Stadio> selezionaStadio;
	private JLabel labelIncasso;
	private JButton visualizzaButton;
	private StrutturaSportiva struct;
	
	private static final long serialVersionUID = -4738376842736705159L;
	
	public static void main(String[] args) {
		JFrame frame = new JFrame("Test");
		
		StrutturaSportiva prova = new StrutturaSportiva("Prova");
		prova.addStadio(new Stadio("Olimpico", 50000, 20.00));
		frame.add(new VisualizzaIncassoPanel(prova));
		
		frame.setSize(500, 200);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}

}

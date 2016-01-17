package graphics;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;

import javax.swing.JButton;

import struttura.Partita;
import struttura.StrutturaSportiva;
import user.Cliente;

public class StadiumSeatButton extends JButton implements Serializable {

	public StadiumSeatButton(StrutturaSportiva strutturaSportiva, Cliente cliente, Partita partita, int numeroPosto, Color backgroundColor) {
		super();
		this.strutturaSportiva = strutturaSportiva;
		this.cliente = cliente;
		this.partita = partita;
		this.setBorder(null);
		this.setToolTipText(numeroPosto + "");
		this.setOpaque(true);
		this.setBackground(backgroundColor);
		this.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				System.out.println("Struttura: " + strutturaSportiva.getNome());
				System.out.println("Cliente: " + cliente.getNome());
				System.out.println("Partita: " + partita);
				System.out.println(cliente.getNome() + " CLICKED SEAT n° " + getToolTipText());
			}
		});
	}
	
	public Cliente getCliente() {
		return this.cliente;
	}
	
	public Partita getPartita() {
		return this.partita;
	}

	public StrutturaSportiva getStrutturaSportiva() {
		return this.strutturaSportiva;
	}

	private static final long serialVersionUID = 5537467864342318585L;
	private StrutturaSportiva strutturaSportiva;
	private Cliente cliente;
	private Partita partita;
}

package graphics;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;

import javax.swing.JButton;

import user.Cliente;

public class StadiumSeatButton extends JButton implements Serializable {

	public StadiumSeatButton(Cliente cliente, int numeroPosto, Color backgroundColor) {
		super();
		this.cliente = cliente;
		this.setBorder(null);
		this.setToolTipText(numeroPosto + "");
		this.setOpaque(true);
		this.setBackground(backgroundColor);
		this.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println(cliente.getNome() + " CLICKED SEAT n° " + getToolTipText());
			}
		});
	}
	
	public Cliente getCliente() {
		return cliente;
	}

	private static final long serialVersionUID = 5537467864342318585L;
	private Cliente cliente;
}

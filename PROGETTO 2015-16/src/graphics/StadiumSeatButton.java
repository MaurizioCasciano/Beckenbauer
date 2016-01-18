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

	public StadiumSeatButton(StrutturaSportiva strutturaSportiva, Cliente cliente, Partita partita, int numeroFila,
			int numeroPosto, Color backgroundColor) {
		super();
		this.strutturaSportiva = strutturaSportiva;
		this.cliente = cliente;
		this.partita = partita;
		this.numeroFila = numeroFila;
		this.numeroPosto = numeroPosto;

		this.setToolTipText("Fila: " + numeroFila + "  " + "Posto: " + numeroPosto);
		this.setBorder(null);
		this.setOpaque(true);
		this.setBackground(backgroundColor);
		this.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				System.out.println("Struttura: " + strutturaSportiva.getNome());
				System.out.println("Cliente: " + cliente.getNome());
				System.out.println("Partita: " + partita);
				System.out.println("NumeroFila: " + numeroFila);
				System.out.println("NumeroPosto: " + numeroPosto);
				
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
	
	/**
	 * Restituisce il numero di fila di questo Posto.
	 * @return il numero di fila di questo Posto.
	 */
	public int getNumeroFila() {
		return numeroFila;
	}

	/**
	 * Il numero di questo Posto.
	 * @return Il numero di questo Posto.
	 */
	public int getNumeroPosto() {
		return numeroPosto;
	}

	public StrutturaSportiva getStrutturaSportiva() {
		return this.strutturaSportiva;
	}

	private static final long serialVersionUID = 5537467864342318585L;
	private StrutturaSportiva strutturaSportiva;
	private Cliente cliente;
	private Partita partita;
	private int numeroFila, numeroPosto;
}

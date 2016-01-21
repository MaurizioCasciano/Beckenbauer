package graphics;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;
import java.util.GregorianCalendar;
import javax.swing.JButton;
import struttura.Acquisto;
import struttura.Biglietto;
import struttura.Partita;
import struttura.Posto;
import struttura.Prenotazione;
import struttura.SeatStatus;
import struttura.Settore;
import struttura.StrutturaSportiva;
import user.Cliente;

public class StadiumSeatButton extends JButton implements Serializable {

	/**
	 * 
	 * @param strutturaSportiva
	 * @param cliente
	 * @param partita
	 * @param posto
	 * @author Maurizio
	 */
	public StadiumSeatButton(StrutturaSportiva strutturaSportiva, Cliente cliente, Partita partita, Posto posto,
			StadiumMode stadiumMode) {
		super();
		this.strutturaSportiva = strutturaSportiva;
		this.cliente = cliente;
		this.partita = partita;
		this.posto = posto;
		this.settore = this.posto.getSettore();
		this.numeroFila = this.posto.getNumeroFila();
		this.numeroPosto = this.posto.getNumeroPosto();
		this.stadiumMode = stadiumMode;

		this.setToolTipText("Fila: " + numeroFila + "  " + "Posto: " + numeroPosto);
		this.setBorder(null);
		this.setOpaque(true);
		this.setBackground(this.posto.getStato().getColor());
		this.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				switch (stadiumMode) {
				case PRENOTAZIONE:
					System.out.println("Prenotazione");

					strutturaSportiva.addPrenotazione(new Prenotazione(new GregorianCalendar(),
							new Biglietto(strutturaSportiva, cliente, partita, settore, numeroFila, numeroPosto)));

					posto.setStato(SeatStatus.PRENOTATO);
					setBackground(posto.getStato().getColor());
					repaint();

					System.out.println(strutturaSportiva.getPrenotazioni().size());

					break;

				case ACQUISTO:
					System.out.println("Acquisto");

					strutturaSportiva.addAcquisto(
							new Acquisto(cliente, partita, settore, numeroFila, numeroPosto, strutturaSportiva));

					posto.setStato(SeatStatus.VENDUTO);
					setBackground(posto.getStato().getColor());
					repaint();

					System.out.println(strutturaSportiva.getAcquisti().size());

					break;
				default:
					break;
				}

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
	 * 
	 * @return il numero di fila di questo Posto.
	 */
	public int getNumeroFila() {
		return numeroFila;
	}

	/**
	 * Il numero di questo Posto.
	 * 
	 * @return Il numero di questo Posto.
	 */
	public int getNumeroPosto() {
		return numeroPosto;
	}

	public StrutturaSportiva getStrutturaSportiva() {
		return this.strutturaSportiva;
	}

	public StadiumMode getStadiumMode() {
		return stadiumMode;
	}

	private static final long serialVersionUID = 5537467864342318585L;
	private StrutturaSportiva strutturaSportiva;
	private Cliente cliente;
	private Partita partita;
	private Posto posto;
	private Settore settore;
	private int numeroFila, numeroPosto;
	private StadiumMode stadiumMode;
}

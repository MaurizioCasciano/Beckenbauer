package struttura;

import java.io.Serializable;

import user.Cliente;

public class Prenotazione implements Serializable {

	public Prenotazione(Cliente cliente, Partita partita) {
		this.cliente = cliente;
		this.partita = partita;
		this.stadio = partita.getStadio();
	}

	public Cliente getCliente() {
		return cliente;
	}

	public Partita getPartita() {
		return partita;
	}

	public Stadio getStadio() {
		return stadio;
	}

	private static final long serialVersionUID = 6528189114014215222L;
	private Cliente cliente;
	private Partita partita;
	private Stadio stadio;
}

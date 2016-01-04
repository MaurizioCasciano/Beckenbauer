package struttura;

import user.Cliente;

public class Prenotazione {

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

	private Cliente cliente;
	private Partita partita;
	private Stadio stadio;
}

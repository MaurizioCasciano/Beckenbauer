package struttura;

import user.Cliente;

public class Prenotazione {

	public Prenotazione(Cliente cliente, PartitaDiCalcio partita) {
		this.cliente = cliente;
		this.partita = partita;
		this.stadio = partita.getStadio();
	}
	
	
	public Cliente getCliente() {
		return cliente;
	}
	public PartitaDiCalcio getPartita() {
		return partita;
	}
	public Stadio getStadio() {
		return stadio;
	}

	private Cliente cliente;
	private PartitaDiCalcio partita;
	private Stadio stadio;
}

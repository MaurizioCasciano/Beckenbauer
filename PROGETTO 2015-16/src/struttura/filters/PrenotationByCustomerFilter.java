package struttura.filters;

import java.io.Serializable;

import struttura.Prenotazione;
import user.Cliente;

/**
 * Classe di call-back per l'interfaccia PrenotationFilter per le prenotazioni in base al
 * {@link Cliente}
 * 
 * @author Gaetano Antonucci
 */
public class PrenotationByCustomerFilter implements PrenotationFilter, Serializable {
	
	/**
	 * Costruisce un filtro sulle Prenotazioni in base al cliente passato come parametro.
	 * 
	 * @param clt - il {@link Cliente} in base al quale si vogliono filtrare i dati.
	 */
	public PrenotationByCustomerFilter(Cliente clt){
		this.cliente = clt;
	}

	@Override
	public boolean accept(Prenotazione prenotazione) {
		boolean result = false;
		
		if(prenotazione.getBigliettoPrenotato().getCliente().equals(this.cliente)){
			result = true;
		}
		return result;
	}
	
	private Cliente cliente;
	
	private static final long serialVersionUID = 5824418745962263670L;
}

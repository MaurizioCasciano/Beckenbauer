package struttura.filters;

import java.io.Serializable;

import struttura.Partita;
import struttura.Prenotazione;

/**
 * Classe di call-back per l'interfaccia PrenotationFilter per le prenotazioni in base alla {@link Partita}
 * 
 * @author Gaetano Antonucci
 */
public class PrenotationByMatchFilter implements PrenotationFilter, Serializable{
	
	/**
	 * Costruisce un filtro in base alla partita passata come parametro
	 * 
	 * @param partita - la {@link Partita} in base alla quale si vogliono filtrare i dati
	 * @author Gaetano Antonucci
	 */
	public PrenotationByMatchFilter(Partita partita){
		this.partita = partita;
	}
	
	@Override
	public boolean accept(Prenotazione prenotazione) {
		boolean result = false;
		
		if(prenotazione.getBigliettoPrenotato().getPartita().equals(this.partita)){
			result = true;
		}
		
		return result;
	}
	
	private Partita partita;
	
	private static final long serialVersionUID = 2154947496110081855L;
	
}

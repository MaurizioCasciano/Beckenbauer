package struttura.filters;

import java.io.Serializable;

import struttura.Prenotazione;
import struttura.Stadio;

/**
 * Classe di call-back per l'interfaccia PrenotationFilter per le prenotazioni
 * in base allo {@link Stadio}
 * 
 * @author Gaetano Antonucci
 */
public class PrenotationByStadiumFilter implements PrenotationFilter, Serializable{
	
	/**
	 * Costruisce un filtro sulle prenotazioni in base allo Stadio passato come parametro
	 * 
	 * @param stadio - lo {@link Stadio} in base al quale si vogliono filtrare i dati
	 */
	public PrenotationByStadiumFilter(Stadio stadio){
		this.stadio = stadio;
	}
	
	@Override
	public boolean accept(Prenotazione prenotazione) {
		boolean result = false;
		
		if(prenotazione.getBigliettoPrenotato().getPartita().getStadio().equals(this.stadio)){
			result = true;
		}
		
		return result;
	}
	
	
	private Stadio stadio;

	private static final long serialVersionUID = -3926849092030927287L;

}

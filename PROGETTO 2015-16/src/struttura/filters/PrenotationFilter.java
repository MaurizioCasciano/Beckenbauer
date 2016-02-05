package struttura.filters;

import struttura.Prenotazione;

/**
 * Interfaccia che modella un filtro su una {@link Prenotazione}.
 * 
 * @author Gaetano Antonucci
 *
 */
public interface PrenotationFilter {
	
	/**
	 * Metodo che verifica se una prenotazione soddisfa o meno il filtro.
	 * 
	 * @param prenotazione - la {@link Prenotazione} su cui s'imposta il filtro
	 * @return {@code true} se la prenotazione soddisfa il filtro, {@code false} altrimenti
	 */
	boolean accept(Prenotazione prenotazione);
}
